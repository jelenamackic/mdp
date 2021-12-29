/**
 * ZSMDPUserService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public interface ZSMDPUserService extends java.rmi.Remote {
    public boolean deleteUser(model.User user) throws java.rmi.RemoteException;
    public boolean isZSMDP(java.lang.String id) throws java.rmi.RemoteException;
    public model.Zsmdp[] getZSMDPs() throws java.rmi.RemoteException;
    public boolean addZSMDP(java.lang.String location, java.lang.String idZSMDP) throws java.rmi.RemoteException;
}
