package gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex;

/***************************************************************************/
/* THIS IS AUTOGENERATED CODE - DO NOT MODIFY OR YOUR CHANGES WILL BE LOST */
/* THIS CODE CAN BE REGENERATED USING 'xsd-codegen'                        */
/***************************************************************************/
public class NamedAttributeGroup extends gw.internal.schema.gw.xsd.w3c.xmlschema.types.complex.AttributeGroup implements gw.internal.xml.IXmlGeneratedClass {

  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Id = new javax.xml.namespace.QName( "", "id", "" );
  public static final javax.xml.namespace.QName $ATTRIBUTE_QNAME_Name = new javax.xml.namespace.QName( "", "name", "" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Annotation = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "annotation", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_AnyAttribute = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "anyAttribute", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_Attribute = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "attribute", "xs" );
  public static final javax.xml.namespace.QName $ELEMENT_QNAME_AttributeGroup = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "attributeGroup", "xs" );
  public static final javax.xml.namespace.QName $QNAME = new javax.xml.namespace.QName( "http://www.w3.org/2001/XMLSchema", "namedAttributeGroup", "xs" );
  public static final gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType> TYPE = new gw.util.concurrent.LockingLazyVar<gw.lang.reflect.IType>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected gw.lang.reflect.IType init() {
            return gw.lang.reflect.TypeSystem.getByFullName( "gw.xsd.w3c.xmlschema.types.complex.NamedAttributeGroup" );
          }
        };
  private static final gw.util.concurrent.LockingLazyVar<java.lang.Object> SCHEMAINFO = new gw.util.concurrent.LockingLazyVar<java.lang.Object>( gw.lang.reflect.TypeSystem.getGlobalLock() ) {
          @Override
          protected java.lang.Object init() {
            gw.lang.reflect.IType type = TYPE.get();
            return getSchemaInfoByType( type );
          }
        };

  public NamedAttributeGroup() {
    super( TYPE.get(), SCHEMAINFO.get() );
  }

  protected NamedAttributeGroup( gw.lang.reflect.IType type, java.lang.Object schemaInfo ) {
    super( type, schemaInfo );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation Annotation() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation) TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().getValue( this );
  }

  public void setAnnotation$( gw.internal.schema.gw.xsd.w3c.xmlschema.Annotation param ) {
    TYPE.get().getTypeInfo().getProperty( "Annotation" ).getAccessor().setValue( this, param );
  }


  public gw.internal.schema.gw.xsd.w3c.xmlschema.AnyAttribute AnyAttribute() {
    return (gw.internal.schema.gw.xsd.w3c.xmlschema.AnyAttribute) TYPE.get().getTypeInfo().getProperty( "AnyAttribute" ).getAccessor().getValue( this );
  }

  public void setAnyAttribute$( gw.internal.schema.gw.xsd.w3c.xmlschema.AnyAttribute param ) {
    TYPE.get().getTypeInfo().getProperty( "AnyAttribute" ).getAccessor().setValue( this, param );
  }


  @Deprecated
  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.AttributeGroup_Attribute> Attribute() {
    return super.Attribute();
  }

  @Deprecated
  public void setAttribute$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.AttributeGroup_Attribute> param ) {
    super.setAttribute$( param );
  }

  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_Attribute> Attribute$$gw_xsd_w3c_xmlschema_types_complex_NamedAttributeGroup() {
    //noinspection unchecked
    return (java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_Attribute>) TYPE.get().getTypeInfo().getProperty( "Attribute" ).getAccessor().getValue( this );
  }

  public void setAttribute$$gw_xsd_w3c_xmlschema_types_complex_NamedAttributeGroup$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_Attribute> param ) {
    TYPE.get().getTypeInfo().getProperty( "Attribute" ).getAccessor().setValue( this, param );
  }


  @Deprecated
  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.AttributeGroup_AttributeGroup> AttributeGroup() {
    return super.AttributeGroup();
  }

  @Deprecated
  public void setAttributeGroup$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.AttributeGroup_AttributeGroup> param ) {
    super.setAttributeGroup$( param );
  }

  public java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_AttributeGroup> AttributeGroup$$gw_xsd_w3c_xmlschema_types_complex_NamedAttributeGroup() {
    //noinspection unchecked
    return (java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_AttributeGroup>) TYPE.get().getTypeInfo().getProperty( "AttributeGroup" ).getAccessor().getValue( this );
  }

  public void setAttributeGroup$$gw_xsd_w3c_xmlschema_types_complex_NamedAttributeGroup$( java.util.List<gw.internal.schema.gw.xsd.w3c.xmlschema.anonymous.elements.NamedAttributeGroup_AttributeGroup> param ) {
    TYPE.get().getTypeInfo().getProperty( "AttributeGroup" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Id() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().getValue( this );
  }

  public void setId$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Id" ).getAccessor().setValue( this, param );
  }


  public java.lang.String Name() {
    return (java.lang.String) TYPE.get().getTypeInfo().getProperty( "Name" ).getAccessor().getValue( this );
  }

  public void setName$( java.lang.String param ) {
    TYPE.get().getTypeInfo().getProperty( "Name" ).getAccessor().setValue( this, param );
  }

  @SuppressWarnings( {"UnusedDeclaration"} )
  private static final long FINGERPRINT = -3788403261967307401L;

}