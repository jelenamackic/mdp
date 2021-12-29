package UserServicesCentralApp;

public class ZSMDPServiceProxy implements UserServicesCentralApp.ZSMDPService {
  private String _endpoint = null;
  private UserServicesCentralApp.ZSMDPService zSMDPService = null;
  
  public ZSMDPServiceProxy() {
    _initZSMDPServiceProxy();
  }
  
  public ZSMDPServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initZSMDPServiceProxy();
  }
  
  private void _initZSMDPServiceProxy() {
    try {
      zSMDPService = (new UserServicesCentralApp.ZSMDPServiceServiceLocator()).getZSMDPService();
      if (zSMDPService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zSMDPService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zSMDPService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zSMDPService != null)
      ((javax.xml.rpc.Stub)zSMDPService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public UserServicesCentralApp.ZSMDPService getZSMDPService() {
    if (zSMDPService == null)
      _initZSMDPServiceProxy();
    return zSMDPService;
  }
  
  public boolean isZSMDP(java.lang.String id) throws java.rmi.RemoteException{
    if (zSMDPService == null)
      _initZSMDPServiceProxy();
    return zSMDPService.isZSMDP(id);
  }
  
  public void putInRest(model.Zsmdp zsmdp) throws java.rmi.RemoteException{
    if (zSMDPService == null)
      _initZSMDPServiceProxy();
    zSMDPService.putInRest(zsmdp);
  }
  
  public boolean addZSMDP(java.lang.String location, java.lang.String idZSMDP) throws java.rmi.RemoteException{
    if (zSMDPService == null)
      _initZSMDPServiceProxy();
    return zSMDPService.addZSMDP(location, idZSMDP);
  }
  
  public model.Zsmdp[] getAllZsmdps() throws java.rmi.RemoteException{
    if (zSMDPService == null)
      _initZSMDPServiceProxy();
    return zSMDPService.getAllZsmdps();
  }
  
  
}