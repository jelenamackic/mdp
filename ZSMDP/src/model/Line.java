/**
 * Line.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model;

public class Line  implements java.io.Serializable {
    private int lineID;

    private model.Timetable[] stations;

    public Line() {
    }

    public Line(
           int lineID,
           model.Timetable[] stations) {
           this.lineID = lineID;
           this.stations = stations;
    }


    /**
     * Gets the lineID value for this Line.
     * 
     * @return lineID
     */
    public int getLineID() {
        return lineID;
    }


    /**
     * Sets the lineID value for this Line.
     * 
     * @param lineID
     */
    public void setLineID(int lineID) {
        this.lineID = lineID;
    }


    /**
     * Gets the stations value for this Line.
     * 
     * @return stations
     */
    public model.Timetable[] getStations() {
        return stations;
    }


    /**
     * Sets the stations value for this Line.
     * 
     * @param stations
     */
    public void setStations(model.Timetable[] stations) {
        this.stations = stations;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Line)) return false;
        Line other = (Line) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.lineID == other.getLineID() &&
            ((this.stations==null && other.getStations()==null) || 
             (this.stations!=null &&
              java.util.Arrays.equals(this.stations, other.getStations())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getLineID();
        if (getStations() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getStations());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getStations(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Line.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model", "Line"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lineID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "lineID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stations");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "stations"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://model", "Timetable"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://UserServicesCentralApp", "item"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
