/**
 * LineServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public class LineServiceServiceLocator extends org.apache.axis.client.Service implements UserServicesCentralApp.LineServiceService {

    public LineServiceServiceLocator() {
    }


    public LineServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public LineServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for LineService
    private java.lang.String LineService_address = "http://localhost:8080/AppSoap/services/LineService";

    public java.lang.String getLineServiceAddress() {
        return LineService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String LineServiceWSDDServiceName = "LineService";

    public java.lang.String getLineServiceWSDDServiceName() {
        return LineServiceWSDDServiceName;
    }

    public void setLineServiceWSDDServiceName(java.lang.String name) {
        LineServiceWSDDServiceName = name;
    }

    public UserServicesCentralApp.LineService getLineService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(LineService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getLineService(endpoint);
    }

    public UserServicesCentralApp.LineService getLineService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UserServicesCentralApp.LineServiceSoapBindingStub _stub = new UserServicesCentralApp.LineServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getLineServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setLineServiceEndpointAddress(java.lang.String address) {
        LineService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (UserServicesCentralApp.LineService.class.isAssignableFrom(serviceEndpointInterface)) {
                UserServicesCentralApp.LineServiceSoapBindingStub _stub = new UserServicesCentralApp.LineServiceSoapBindingStub(new java.net.URL(LineService_address), this);
                _stub.setPortName(getLineServiceWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("LineService".equals(inputPortName)) {
            return getLineService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://UserServicesCentralApp", "LineServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://UserServicesCentralApp", "LineService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("LineService".equals(portName)) {
            setLineServiceEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
