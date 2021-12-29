/**
 * ZSMDPService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package UserServicesCentralApp;

public interface ZSMDPService extends java.rmi.Remote {
    public boolean isZSMDP(java.lang.String id) throws java.rmi.RemoteException;
    public void putInRest(model.Zsmdp zsmdp) throws java.rmi.RemoteException;
    public boolean addZSMDP(java.lang.String location, java.lang.String idZSMDP) throws java.rmi.RemoteException;
    public model.Zsmdp[] getAllZsmdps() throws java.rmi.RemoteException;
}
