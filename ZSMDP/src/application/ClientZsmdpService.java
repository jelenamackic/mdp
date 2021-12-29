package application;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;

import UserServicesCentralApp.ZSMDPService;
import UserServicesCentralApp.ZSMDPServiceServiceLocator;
import model.Zsmdp;

public class ClientZsmdpService {

	public static Zsmdp[] getAllZsmdps() {
		ZSMDPServiceServiceLocator locator = new ZSMDPServiceServiceLocator();
		try {
			ZSMDPService service = locator.getZSMDPService();
			return service.getAllZsmdps();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		return null;
	}
}
