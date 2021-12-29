package UserServicesCentralApp;

public class LineServiceProxy implements UserServicesCentralApp.LineService {
  private String _endpoint = null;
  private UserServicesCentralApp.LineService lineService = null;
  
  public LineServiceProxy() {
    _initLineServiceProxy();
  }
  
  public LineServiceProxy(String endpoint) {
    _endpoint = endpoint;
    _initLineServiceProxy();
  }
  
  private void _initLineServiceProxy() {
    try {
      lineService = (new UserServicesCentralApp.LineServiceServiceLocator()).getLineService();
      if (lineService != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)lineService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)lineService)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (lineService != null)
      ((javax.xml.rpc.Stub)lineService)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public UserServicesCentralApp.LineService getLineService() {
    if (lineService == null)
      _initLineServiceProxy();
    return lineService;
  }
  
  public void markTheStationIsPassed(java.lang.String lineID, java.lang.String stationID, java.lang.String time) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    lineService.markTheStationIsPassed(lineID, stationID, time);
  }
  
  public model.Line[] getLinesForOneStation(java.lang.String stationId) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.getLinesForOneStation(stationId);
  }
  
  public java.lang.String writeLine(model.Line line) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.writeLine(line);
  }
  
  public boolean addLine(int lineId, model.Timetable[] stations, int numberOfStations) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.addLine(lineId, stations, numberOfStations);
  }
  
  public boolean deleteLine(int lineId) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.deleteLine(lineId);
  }
  
  public model.Line[] getAllLines() throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.getAllLines();
  }
  
  public boolean isLine(int lineId) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.isLine(lineId);
  }
  
  public model.Line[] readAllFromRedis() throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.readAllFromRedis();
  }
  
  public void putInRedis(model.Line line) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    lineService.putInRedis(line);
  }
  
  public model.Timetable makeTimetable(java.lang.String estimatedTime, java.lang.String stationID) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.makeTimetable(estimatedTime, stationID);
  }
  
  public model.Line getALine(java.lang.String lineId) throws java.rmi.RemoteException{
    if (lineService == null)
      _initLineServiceProxy();
    return lineService.getALine(lineId);
  }
  
  
}