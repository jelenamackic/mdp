/**
 * UserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public interface UserService extends java.rmi.Remote {
    public void main(java.lang.String[] args) throws java.rmi.RemoteException;
    public boolean isActive(model.User user) throws java.rmi.RemoteException;
    public void deleteFile(java.lang.String filename) throws java.rmi.RemoteException;
    public model.User[] getUsers() throws java.rmi.RemoteException;
    public model.User[] getActiveUsersForStation(java.lang.String stationId) throws java.rmi.RemoteException;
    public model.User[] getUsersFromStation(java.lang.String stationId) throws java.rmi.RemoteException;
    public model.User login(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public java.lang.String isUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public void logout(model.User user) throws java.rmi.RemoteException;
    public boolean addUser(java.lang.String username, java.lang.String password, java.lang.String idZSMDP) throws java.rmi.RemoteException;
    public model.User[] deserializeWithXML() throws java.rmi.RemoteException;
    public boolean isAlreadyUser(java.lang.String username) throws java.rmi.RemoteException;
    public void serializeWithXML(model.User user) throws java.rmi.RemoteException;
    public model.User getThisUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public boolean deleteUser(model.User user) throws java.rmi.RemoteException;
}
