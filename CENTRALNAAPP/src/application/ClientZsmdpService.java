package application;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.UserServiceServiceLocator;
import UserServicesCentralApp.ZSMDPService;
import UserServicesCentralApp.ZSMDPServiceService;
import UserServicesCentralApp.ZSMDPServiceServiceLocator;
import model.Zsmdp;

public class ClientZsmdpService {
	public Zsmdp[] getAllZsmdps() {
		ZSMDPServiceServiceLocator locator = new ZSMDPServiceServiceLocator();
		try {
			ZSMDPService zsmdpService = locator.getZSMDPService();
			try {
				return zsmdpService.getAllZsmdps();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		System.out.println("Nema stanica");
		return null;
	}
	
	
	
}
