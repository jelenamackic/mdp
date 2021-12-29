package application;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.rpc.ServiceException;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.User;
import rmi.ReportServiceInterface;


 
public class App extends Application {
	
	public static Stage stage;
	public static Scene scene;
	public static ClientUserService clientUserService;
	public static ClientZsmdpService clientZsmdpService;
	public static ClientLineRestService clientLineRestService;
	public static ReportServiceInterface reportServiceInterface;
	public static MulticastService multicastService;
	public static InterruptableUDPThread interruptableUDPThread;
	public static InterruptableSSLThread interruptableSSLThread;
	public static User activeUser;
	public static ChatClient chatClientService;
	public static final String LOG_FILE = "logs.log";
	public static final String PROP_FILE = "prop.properties";
	public final static Logger LOGGER = Logger.getLogger(App.class.getName());
	public static Handler handler;
	public static Properties properties;
    public static void main(String[] args) {
    	try {
			handler = new FileHandler(LOG_FILE,true);
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		
		FileReader reader;
		try {
			
			reader = new FileReader(PROP_FILE);
			properties=new Properties();  
			properties.load(reader);  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}  
    
    	System.setProperty("java.security.policy",properties.getProperty("policyFile"));
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
			
		}
		clientUserService = new ClientUserService();
		clientZsmdpService = new ClientZsmdpService();
		clientLineRestService = new ClientLineRestService();
		
		try {
			
			Registry registry = LocateRegistry.getRegistry(1099);
			if(properties != null) {
				
				reportServiceInterface = (ReportServiceInterface) registry.lookup(properties.getProperty("serverName"));
			}else {
				System.out.println("Properties are null");
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
    		
		launch(args);
		if(MulticastService.socket != null && chatClientService != null && chatClientService.getSocket() != null) {
			interruptableUDPThread = new InterruptableUDPThread(MulticastService.socket);
			interruptableUDPThread.interrupt();
			interruptableSSLThread = new InterruptableSSLThread(chatClientService.getSocket());
			interruptableSSLThread.interrupt();
		}
		
    }
    
    @Override
    public void start(Stage primaryStage) {
    	showSimulation("loginForm");
    	
    }
    public void showSimulation(String fileName) {
        try {
        	//System.out.println("PUTANJA: " + fileName);
            stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource(properties.getProperty(fileName)));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            stage.show();
        } catch (IOException e) {
        	Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
        }
    }
    
  
}