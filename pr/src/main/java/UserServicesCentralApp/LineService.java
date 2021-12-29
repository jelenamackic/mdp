/**
 * LineService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public interface LineService extends java.rmi.Remote {
    public void markTheStationIsPassed(java.lang.String lineID, java.lang.String stationID, java.lang.String time) throws java.rmi.RemoteException;
    public model.Line[] getLinesForOneStation(java.lang.String stationId) throws java.rmi.RemoteException;
    public java.lang.String writeLine(model.Line line) throws java.rmi.RemoteException;
    public boolean addLine(int lineId, model.Timetable[] stations, int numberOfStations) throws java.rmi.RemoteException;
    public boolean deleteLine(int lineId) throws java.rmi.RemoteException;
    public model.Line[] getAllLines() throws java.rmi.RemoteException;
    public boolean isLine(int lineId) throws java.rmi.RemoteException;
    public model.Line[] readAllFromRedis() throws java.rmi.RemoteException;
    public void putInRedis(model.Line line) throws java.rmi.RemoteException;
    public model.Timetable makeTimetable(java.lang.String estimatedTime, java.lang.String stationID) throws java.rmi.RemoteException;
    public model.Line getALine(java.lang.String lineId) throws java.rmi.RemoteException;
}
