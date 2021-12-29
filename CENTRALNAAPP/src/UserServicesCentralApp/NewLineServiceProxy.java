package UserServicesCentralApp;

public class NewLineServiceProxy implements UserServicesCentralApp.NewLineService {
  private String _endpoint = null;
  private UserServicesCentralApp.NewLineService newLineService = null;
  
  public NewLineServiceProxy() {
    _initNewLineServiceProxy();
  }
  
  public NewLineServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initNewLineServiceProxy();
  }
  
  private void _initNewLineServiceProxy() {
    try {
      newLineService = (new UserServicesCentralApp.NewLineServiceServiceLocator()).getNewLineService();
      if (newLineService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)newLineService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)newLineService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (newLineService != null)
      ((javax.xml.rpc.Stub)newLineService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public UserServicesCentralApp.NewLineService getNewLineService() {
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService;
  }
  
  public boolean deleteLine(int lineId) throws java.rmi.RemoteException{
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService.deleteLine(lineId);
  }
  
  public boolean addLine(int lineId, model.Timetable[] stations, int numberOfStations) throws java.rmi.RemoteException{
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService.addLine(lineId, stations, numberOfStations);
  }
  
  public model.Line[] getLinesForOneStation(int stationId) throws java.rmi.RemoteException{
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService.getLinesForOneStation(stationId);
  }
  
  public model.Line[] getAllLines() throws java.rmi.RemoteException{
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService.getAllLines();
  }
  
  public boolean isLine(int lineId) throws java.rmi.RemoteException{
    if (newLineService == null)
      _initNewLineServiceProxy();
    return newLineService.isLine(lineId);
  }
  
  
}