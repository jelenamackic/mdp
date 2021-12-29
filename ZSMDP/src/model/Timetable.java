/**
 * Timetable.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package model;

public class Timetable  implements java.io.Serializable {
    private java.lang.String estimatedTime;

    private boolean passed;

    private java.lang.String realTime;

    private java.lang.String stationId;

    public Timetable() {
    }

    public Timetable(
           java.lang.String estimatedTime,
           boolean passed,
           java.lang.String realTime,
           java.lang.String stationId) {
           this.estimatedTime = estimatedTime;
           this.passed = passed;
           this.realTime = realTime;
           this.stationId = stationId;
    }


    /**
     * Gets the estimatedTime value for this Timetable.
     * 
     * @return estimatedTime
     */
    public java.lang.String getEstimatedTime() {
        return estimatedTime;
    }


    /**
     * Sets the estimatedTime value for this Timetable.
     * 
     * @param estimatedTime
     */
    public void setEstimatedTime(java.lang.String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }


    /**
     * Gets the passed value for this Timetable.
     * 
     * @return passed
     */
    public boolean isPassed() {
        return passed;
    }


    /**
     * Sets the passed value for this Timetable.
     * 
     * @param passed
     */
    public void setPassed(boolean passed) {
        this.passed = passed;
    }


    /**
     * Gets the realTime value for this Timetable.
     * 
     * @return realTime
     */
    public java.lang.String getRealTime() {
        return realTime;
    }


    /**
     * Sets the realTime value for this Timetable.
     * 
     * @param realTime
     */
    public void setRealTime(java.lang.String realTime) {
        this.realTime = realTime;
    }


    /**
     * Gets the stationId value for this Timetable.
     * 
     * @return stationId
     */
    public java.lang.String getStationId() {
        return stationId;
    }


    /**
     * Sets the stationId value for this Timetable.
     * 
     * @param stationId
     */
    public void setStationId(java.lang.String stationId) {
        this.stationId = stationId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Timetable)) return false;
        Timetable other = (Timetable) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estimatedTime==null && other.getEstimatedTime()==null) || 
             (this.estimatedTime!=null &&
              this.estimatedTime.equals(other.getEstimatedTime()))) &&
            this.passed == other.isPassed() &&
            ((this.realTime==null && other.getRealTime()==null) || 
             (this.realTime!=null &&
              this.realTime.equals(other.getRealTime()))) &&
            ((this.stationId==null && other.getStationId()==null) || 
             (this.stationId!=null &&
              this.stationId.equals(other.getStationId())));
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
        if (getEstimatedTime() != null) {
            _hashCode += getEstimatedTime().hashCode();
        }
        _hashCode += (isPassed() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getRealTime() != null) {
            _hashCode += getRealTime().hashCode();
        }
        if (getStationId() != null) {
            _hashCode += getStationId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Timetable.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://model", "Timetable"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estimatedTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "estimatedTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("passed");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "passed"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("realTime");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "realTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stationId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://model", "stationId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
