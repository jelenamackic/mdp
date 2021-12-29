/**
 * ZSMDPUserServiceServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public class ZSMDPUserServiceServiceLocator extends org.apache.axis.client.Service implements UserServicesCentralApp.ZSMDPUserServiceService {

    public ZSMDPUserServiceServiceLocator() {
    }


    public ZSMDPUserServiceServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ZSMDPUserServiceServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ZSMDPUserService
    private java.lang.String ZSMDPUserService_address = "http://localhost:8080/AppSoap/services/ZSMDPUserService";

    public java.lang.String getZSMDPUserServiceAddress() {
        return ZSMDPUserService_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ZSMDPUserServiceWSDDServiceName = "ZSMDPUserService";

    public java.lang.String getZSMDPUserServiceWSDDServiceName() {
        return ZSMDPUserServiceWSDDServiceName;
    }

    public void setZSMDPUserServiceWSDDServiceName(java.lang.String name) {
        ZSMDPUserServiceWSDDServiceName = name;
    }

    public UserServicesCentralApp.ZSMDPUserService getZSMDPUserService() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ZSMDPUserService_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getZSMDPUserService(endpoint);
    }

    public UserServicesCentralApp.ZSMDPUserService getZSMDPUserService(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            UserServicesCentralApp.ZSMDPUserServiceSoapBindingStub _stub = new UserServicesCentralApp.ZSMDPUserServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getZSMDPUserServiceWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setZSMDPUserServiceEndpointAddress(java.lang.String address) {
        ZSMDPUserService_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (UserServicesCentralApp.ZSMDPUserService.class.isAssignableFrom(serviceEndpointInterface)) {
                UserServicesCentralApp.ZSMDPUserServiceSoapBindingStub _stub = new UserServicesCentralApp.ZSMDPUserServiceSoapBindingStub(new java.net.URL(ZSMDPUserService_address), this);
                _stub.setPortName(getZSMDPUserServiceWSDDServiceName());
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
        if ("ZSMDPUserService".equals(inputPortName)) {
            return getZSMDPUserService();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://UserServicesCentralApp", "ZSMDPUserServiceService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://UserServicesCentralApp", "ZSMDPUserService"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ZSMDPUserService".equals(portName)) {
            setZSMDPUserServiceEndpointAddress(address);
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
