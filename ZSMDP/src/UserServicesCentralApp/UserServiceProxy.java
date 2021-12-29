package UserServicesCentralApp;

public class UserServiceProxy implements UserServicesCentralApp.UserService {
  private String _endpoint = null;
  private UserServicesCentralApp.UserService userService = null;
  
  public UserServiceProxy() {
    _initUserServiceProxy();
  }
  
  public UserServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initUserServiceProxy();
  }
  
  private void _initUserServiceProxy() {
    try {
      userService = (new UserServicesCentralApp.UserServiceServiceLocator()).getUserService();
      if (userService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)userService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (userService != null)
      ((javax.xml.rpc.Stub)userService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public UserServicesCentralApp.UserService getUserService() {
    if (userService == null)
      _initUserServiceProxy();
    return userService;
  }
  
  public void main(java.lang.String[] args) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.main(args);
  }
  
  public boolean isActive(model.User user) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.isActive(user);
  }
  
  public void deleteFile(java.lang.String filename) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.deleteFile(filename);
  }
  
  public model.User[] getUsers() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getUsers();
  }
  
  public model.User[] getActiveUsersForStation(java.lang.String stationId) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getActiveUsersForStation(stationId);
  }
  
  public model.User[] getUsersFromStation(java.lang.String stationId) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getUsersFromStation(stationId);
  }
  
  public model.User login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.login(username, password);
  }
  
  public java.lang.String isUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.isUser(username, password);
  }
  
  public void logout(model.User user) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.logout(user);
  }
  
  public boolean addUser(java.lang.String username, java.lang.String password, java.lang.String idZSMDP) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.addUser(username, password, idZSMDP);
  }
  
  public model.User[] deserializeWithXML() throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.deserializeWithXML();
  }
  
  public boolean isAlreadyUser(java.lang.String username) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.isAlreadyUser(username);
  }
  
  public void serializeWithXML(model.User user) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    userService.serializeWithXML(user);
  }
  
  public model.User getThisUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.getThisUser(username, password);
  }
  
  public boolean deleteUser(model.User user) throws java.rmi.RemoteException{
    if (userService == null)
      _initUserServiceProxy();
    return userService.deleteUser(user);
  }
  
  
}