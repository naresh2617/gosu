/*
 * Copyright 2013 Guidewire Software, Inc.
 */

package gw.internal.gosu.parser;

import gw.lang.reflect.*;
import gw.lang.reflect.java.IJavaType;
import gw.lang.reflect.java.JavaTypes;
import gw.util.concurrent.LockingLazyVar;
import gw.util.concurrent.LocklessLazyVar;

import java.io.ObjectStreamException;
import java.lang.reflect.Array;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 */
public class MetaType extends AbstractType implements IMetaType
{
  static class RootType{}
  static class DefaultType{}

  /**
   * These fields need to be lazu vars to avoid bombarding the typesystem with calls
   */
  private static final LocklessLazyVar<IJavaType> ROOT_TYPE =
    new LocklessLazyVar<IJavaType>()
    {
      public IJavaType init()
      {
        IJavaType type = (IJavaType) TypeSystem.getByFullNameIfValid( RootType.class.getName().replace('$', '.'), TypeSystem.getGlobalModule() );
        if( type == null )
        {
          throw new RuntimeException( "Cannot load gw.internal.gosu.parser.MetaType.RootType. The TypeSystem is not setup properly. It's highly likely Gosu is not in your classpath (perhaps via the project SDK)." );
        }
        return type;
      }
    };
  static final LocklessLazyVar<IJavaType> DEFAULT_TYPE =
    new LocklessLazyVar<IJavaType>()
    {
      public IJavaType init()
      {
        return (IJavaType) TypeSystem.getByFullNameIfValid( DefaultType.class.getName().replace('$', '.'), TypeSystem.getGlobalModule() );
      }
    };

  public static LocklessLazyVar<MetaType> ROOT_TYPE_TYPE =
    new LocklessLazyVar<MetaType>()
    {
      public MetaType init()
      {
        return MetaType.get( ROOT_TYPE.get() );
      }
    };
  public static LocklessLazyVar<IType> DEFAULT_TYPE_TYPE =
    new LocklessLazyVar<IType>()
    {
      public IType init()
      {
        return MetaType.get( DEFAULT_TYPE.get() );
      }
    };

  static {
    TypeSystem.addShutdownListener(new TypeSystemShutdownListener() {
      public void shutdown() {
        clearCaches();
      }
    });
  }

  public static void clearCaches()
  {
    ROOT_TYPE.clear();
    ROOT_TYPE_TYPE.clear();
    DEFAULT_TYPE.clear();
    DEFAULT_TYPE_TYPE.clear();
  }

  private IType _type;
  transient private Map<IRelativeTypeInfo.Accessibility, ITypeInfo> _typeInfoByAccessibility;
  transient private Set<IJavaType> _allTypesInHierarchy;
  transient private LockingLazyVar<IType> _arrayType;
  transient private GenericTypeVariable[] _typeVars;
  transient private boolean _bLiteral;
  transient private IType[] _typeParams;
  transient private String _strName;
  transient private String _strRelativeName;

  public static MetaType get( IType type )
  {
    return (MetaType) type.getMetaType();
  }

  public static MetaType getLiteral( IType type )
  {
    return (MetaType) type.getLiteralMetaType();
  }

  MetaType( IType type, boolean bLiteral )
  {
    _type = type;
    _bLiteral = bLiteral;
    _typeInfoByAccessibility = new HashMap<IRelativeTypeInfo.Accessibility, ITypeInfo>( 2 );

    if( type == ROOT_TYPE.get() )
    {
      _typeVars = new GenericTypeVariable[]{new GenericTypeVariable("T", JavaTypes.OBJECT())};
      _typeParams = IType.EMPTY_ARRAY;
    }
    else
    {
      _typeVars = GenericTypeVariable.EMPTY_TYPEVARS;
      _typeParams = new IType[]{type};
    }

    _arrayType =
      new LockingLazyVar<IType>()
      {
        @Override
        protected IType init()
        {
          return new DefaultNonLoadableArrayType( MetaType.this, null, getTypeLoader() );
        }
      };
  }

  @Override
  public IType getType()
  {
    return _type;
  }

  @Override
  public boolean isLiteral()
  {
    return _bLiteral;
  }

  @Override
  public ITypeLoader getTypeLoader()
  {
    return _type.getTypeLoader();
  }

  @Override
  public String getName()
  {
    if( _strName == null )
    {
      _strName = "Type" + (isGenericType() ? "" : "<" + _type.getName() + ">");
    }
    return _strName;
  }

  @Override
  public String getDisplayName()
  {
    return getName();
  }

  @Override
  public String getRelativeName()
  {
    if( _strRelativeName == null )
    {
      _strRelativeName =
        isGenericType() || isDefault()
        ? "Type"
        : "Type<" + _type.getRelativeName() + ">";
    }
    return _strRelativeName;
  }

  private boolean isDefault()
  {
     return
       _type instanceof MetaType
       ? ((MetaType)_type).isDefault()
       : _type == DEFAULT_TYPE.get();
  }

  @Override
  public String getNamespace()
  {
    return isGenericType() ? "" : _type.getNamespace();
  }

  @Override
  public boolean isArray()
  {
    return false;
  }

  @Override
  public boolean isPrimitive()
  {
    return false;
  }

  @Override
  public Object makeArrayInstance( int iLength )
  {
    return JavaTypes.ITYPE().makeArrayInstance( iLength );
  }

  /**
   * MetaTypes are assignable if:
   * <ul>
   * <li> they are both arrays and their component types are assignable
   * <li> the are not arrays and:
   * <li> - one of them is not a MetaType and instead implements IType
   * <li> - or - one of them is the DEFAULT_TYPE
   * <li> - or - one of them is the ROOT_TYPE
   * <li> - or - their core types are assignable
   * </ul>
   */
  @Override
  public boolean isAssignableFrom( IType type )
  {
    if( isArray() && type.isArray() )
    {
      return getComponentType().isAssignableFrom( type.getComponentType() );
    }

    return !(isArray() || type.isArray()) && type.getAllTypesInHierarchy().contains( JavaTypes.ITYPE() ) &&
           (!(type instanceof IMetaType) ||
            getType().equals( DEFAULT_TYPE.get() ) ||
            ((IMetaType)type).getType().equals( DEFAULT_TYPE.get() ) ||
            getType().equals( ROOT_TYPE.get() ) ||
            ((IMetaType)type).getType().equals( ROOT_TYPE.get() ) ||
            getType().isAssignableFrom( ((IMetaType)type).getType() ));
  }

  /**
   * Instance of this type (e.g., other types) are immutable.
   */
  @Override
  public boolean isMutable()
  {
    return false;
  }

  @Override
  public ITypeInfo getTypeInfo()
  {
    return loadTypeInfo();
  }

  /**
   * @return One of four possible typeinfos (public, protected, package, private),
   *         depending on the accessibility of the enclosing type of the call site.
   */
  private ITypeInfo loadTypeInfo()
  {
    IRelativeTypeInfo.Accessibility context =
      _type.getTypeInfo() instanceof IRelativeTypeInfo
      ? ((IRelativeTypeInfo)_type.getTypeInfo()).getAccessibilityForType( null )
      : null;
    ITypeInfo typeInfo = _typeInfoByAccessibility.get( context );
    if( typeInfo == null )
    {
      typeInfo = new MetaTypeTypeInfo( this );
      _typeInfoByAccessibility.put( context, typeInfo );
    }

    return typeInfo;
  }

  @Override
  public void unloadTypeInfo()
  {
    _typeInfoByAccessibility.clear();
  }

  @Override
  public boolean isInterface()
  {
    return false;
  }

  @Override
  public boolean isEnum()
  {
    return false;
  }

  @Override
  public IType[] getInterfaces()
  {
    return EMPTY_TYPE_ARRAY;
  }

  @Override
  public IType getSupertype()
  {
    return null;
  }


  @Override
  public IType getEnclosingType()
  {
    return null;
  }

  @Override
  public IType getGenericType()
  {
    return ROOT_TYPE_TYPE.get();
  }

  @Override
  public boolean isFinal()
  {
    return true;
  }

  @Override
  public boolean isParameterizedType()
  {
    return _type != ROOT_TYPE.get();
  }

  @Override
  public boolean isGenericType()
  {
    return _type == ROOT_TYPE.get();
  }

  @Override
  public GenericTypeVariable[] getGenericTypeVariables()
  {
    return _typeVars;
  }

  @Override
  public IType[] getTypeParameters()
  {
    return _typeParams;
  }

  @Override
  public IType getParameterizedType( IType... ofType )
  {
    return get( ofType[0] );
  }

  @Override
  public Set<? extends IType> getAllTypesInHierarchy()
  {
    if( _allTypesInHierarchy == null )
    {
      //noinspection unchecked,RedundantCast
      IType type = TypeSystem.get(getType().getClass(), TypeSystem.getGlobalModule());
      _allTypesInHierarchy = (Set)getTypeInterfaces(type, new HashSet<IType>() );
    }
    return _allTypesInHierarchy;
  }

  private Set<IType> getTypeInterfaces( IType type, Set<IType> set )
  {
    if( getType() == DEFAULT_TYPE.get() )
    {
      return Collections.singleton( (IType)JavaTypes.ITYPE() );
    }
    for( IType iface : type.getInterfaces() )
    {
      iface = TypeLord.getDefaultParameterizedType( iface );
      if( !iface.getNamespace().startsWith( "gw.internal" ) )
      {
        set.add( iface );
      }
      getTypeInterfaces( iface, set );
    }
    return set;
  }

  @Override
  public IType getArrayType()
  {
    return _arrayType.get();
  }

  @Override
  public Object getArrayComponent( Object array, int iIndex ) throws IllegalArgumentException, ArrayIndexOutOfBoundsException
  {
    return Array.get( array, iIndex );
  }

  @Override
  public void setArrayComponent( Object array, int iIndex, Object value ) throws IllegalArgumentException, ArrayIndexOutOfBoundsException
  {
    Array.set( array, iIndex, value );
  }

  @Override
  public int getArrayLength( Object array ) throws IllegalArgumentException
  {
    return Array.getLength( array );
  }

  @Override
  public IType getComponentType()
  {
    return null;
  }

  @Override
  public Object readResolve() throws ObjectStreamException
  {
    return get( _type );
  }

  @Override
  public boolean isValid()
  {
    return true;
  }

  @Override
  public int getModifiers()
  {
    return Modifier.PUBLIC;
  }

  @Override
  public boolean isAbstract()
  {
    return false;
  }

  @Override
  public boolean equals( Object o )
  {
    return o instanceof MetaType && this._type.equals( ((MetaType)o)._type );
  }

  @Override
  public int hashCode()
  {
    return _type.hashCode();
  }

  @Override
  public String toString()
  {
    return getName();
  }

  @Override
  public boolean isDiscarded()
  {
    return false;
  }

  @Override
  public void setDiscarded( boolean bDiscarded )
  {
  }

  @Override
  public boolean isCompoundType()
  {
    return false;
  }

  @Override
  public Set<IType> getCompoundTypeComponents()
  {
    return null;
  }

}
