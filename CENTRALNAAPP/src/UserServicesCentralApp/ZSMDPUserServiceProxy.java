package UserServicesCentralApp;

public class ZSMDPUserServiceProxy implements UserServicesCentralApp.ZSMDPUserService {
  private String _endpoint = null;
  private UserServicesCentralApp.ZSMDPUserService zSMDPUserService = null;
  
  public ZSMDPUserServiceProxy() {
    _initZSMDPUserServiceProxy();
  }
  
  public ZSMDPUserServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initZSMDPUserServiceProxy();
  }
  
  private void _initZSMDPUserServiceProxy() {
    try {
      zSMDPUserService = (new UserServicesCentralApp.ZSMDPUserServiceServiceLocator()).getZSMDPUserService();
      if (zSMDPUserService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zSMDPUserService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zSMDPUserService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zSMDPUserService != null)
      ((javax.xml.rpc.Stub)zSMDPUserService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public UserServicesCentralApp.ZSMDPUserService getZSMDPUserService() {
    if (zSMDPUserService == null)
      _initZSMDPUserServiceProxy();
    return zSMDPUserService;
  }
  
  public boolean deleteUser(model.User user) throws java.rmi.RemoteException{
    if (zSMDPUserService == null)
      _initZSMDPUserServiceProxy();
    return zSMDPUserService.deleteUser(user);
  }
  
  public boolean isZSMDP(java.lang.String id) throws java.rmi.RemoteException{
    if (zSMDPUserService == null)
      _initZSMDPUserServiceProxy();
    return zSMDPUserService.isZSMDP(id);
  }
  
  public model.Zsmdp[] getZSMDPs() throws java.rmi.RemoteException{
    if (zSMDPUserService == null)
      _initZSMDPUserServiceProxy();
    return zSMDPUserService.getZSMDPs();
  }
  
  public boolean addZSMDP(java.lang.String location, java.lang.String idZSMDP) throws java.rmi.RemoteException{
    if (zSMDPUserService == null)
      _initZSMDPUserServiceProxy();
    return zSMDPUserService.addZSMDP(location, idZSMDP);
  }
  
  
}