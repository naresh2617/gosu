package gw.internal.schema.gw.xsd.gw.gw_schema_additions.types.simple;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class Viewas extends gw.internal.schema.gw.xsd.w3c.xmlschema.types.simple.String implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://guidewire.com/xsd", "viewas", "gw" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.gw.gw_schema_additions.types.simple.Viewas" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<java.lang.Object> SCHEMAINFO = new gw.util.concurrent.LockingLazyVar<java.lang.Object>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected java.lang.Object init() {
            gw.lang.reflect.IType type = TYPE.get();
            return getSchemaInfoByType( type );
          }
        };

  public Viewas() {
    super( TYPE.get(), SCHEMAINFO.get() );
  }

  protected Viewas( gw.lang.reflect.IType type, java.lang.Object schemaInfo ) {
    super( type, schemaInfo );
  }

  public Viewas( gw.internal.schema.gw.xsd.gw.gw_schema_additions.enums.Viewas value ) {
    this();
    TYPE.get().getTypeInfo().getProperty( "$Value" ).getAccessor().setValue( this, value );
  }


  @Deprecated
  public java.lang.String getValue() {
    return super.getValue();
  }

  @Deprecated
  public void setValue( java.lang.String param ) {
    super.setValue( param );
  }

  public gw.internal.schema.gw.xsd.gw.gw_schema_additions.enums.Viewas getValue$$gw_xsd_gw_gw_schema_additions_types_simple_Viewas() {
    return (gw.internal.schema.gw.xsd.gw.gw_schema_additions.enums.Viewas) TYPE.get().getTypeInfo().getProperty( "$Value" ).getAccessor().getValue( this );
  }

  public void setValue$$gw_xsd_gw_gw_schema_additions_types_simple_Viewas( gw.internal.schema.gw.xsd.gw.gw_schema_additions.enums.Viewas param ) {
    TYPE.get().getTypeInfo().getProperty( "$Value" ).getAccessor().setValue( this, param );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = 8741355091072102811L;

}
