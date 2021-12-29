/**
 * NewLineServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public class NewLineServiceServiceLocator extends org.apache.axis.client.Service implements UserServicesCentralApp.NewLineServiceService {

    public NewLineServiceServiceLocator() {
    }


    public NewLineServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public NewLineServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for NewLineService
    private java.lang.String NewLineService_address = "http://localhost:8080/AppSoap/services/NewLineService";

    public java.lang.String getNewLineServiceAddress() {
        return NewLineService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String NewLineServiceWSDDServiceName = "NewLineService";

    public java.lang.String getNewLineServiceWSDDServiceName() {
        return NewLineServiceWSDDServiceName;
    }

    public void setNewLineServiceWSDDServiceName(java.lang.String name) {
        NewLineServiceWSDDServiceName = name;
    }

    public UserServicesCentralApp.NewLineService getNewLineService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(NewLineService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getNewLineService(endpoint);
    }

    public UserServicesCentralApp.NewLineService getNewLineService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UserServicesCentralApp.NewLineServiceSoapBindingStub _stub = new UserServicesCentralApp.NewLineServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getNewLineServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setNewLineServiceEndpointAddress(java.lang.String address) {
        NewLineService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (UserServicesCentralApp.NewLineService.class.isAssignableFrom(serviceEndpointInterface)) {
                UserServicesCentralApp.NewLineServiceSoapBindingStub _stub = new UserServicesCentralApp.NewLineServiceSoapBindingStub(new java.net.URL(NewLineService_address), this);
                _stub.setPortName(getNewLineServiceWSDDServiceName());
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
        if ("NewLineService".equals(inputPortName)) {
            return getNewLineService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://UserServicesCentralApp", "NewLineServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://UserServicesCentralApp", "NewLineService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("NewLineService".equals(portName)) {
            setNewLineServiceEndpointAddress(address);
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
