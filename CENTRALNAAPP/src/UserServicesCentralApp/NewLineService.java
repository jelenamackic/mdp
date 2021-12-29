/**
 * NewLineService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public interface NewLineService extends java.rmi.Remote {
    public boolean deleteLine(int lineId) throws java.rmi.RemoteException;
    public boolean addLine(int lineId, model.Timetable[] stations, int numberOfStations) throws java.rmi.RemoteException;
    public model.Line[] getLinesForOneStation(int stationId) throws java.rmi.RemoteException;
    public model.Line[] getAllLines() throws java.rmi.RemoteException;
    public boolean isLine(int lineId) throws java.rmi.RemoteException;
}
