package application;
	
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import controllers.MainPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import rmi.ReportServiceInterface;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;


public class Main extends Application {
	
	public static ClientUserService clientUserService;
	public static ClientLineService clientLineService;
	public static ClientZsmdpService clientZsmdpService;
	public static ReportServiceInterface reportServiceInterface;
	public static MulticastService multicastService;
	public static MainPageController mainPageController;
	public static final String LOG_FILE = "logs.log";
	public static final String PROP_FILE = "prop.properties";
	public final static Logger LOGGER = Logger.getLogger(Main.class.getName());
	public static Handler handler;
	public static Properties properties;

	
	@Override
	public void start(Stage primaryStage) {
		try {
            Stage stage = new Stage();
            Pane myPane = FXMLLoader.load(getClass().getResource(properties.getProperty("mainPage")));
            Scene myScene = new Scene(myPane);
            stage.setScene(myScene);
            
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
        }
	}
	
	public static void main(String[] args) {
		
		
		FileReader reader;
		try {
			handler = new FileHandler(LOG_FILE);
			LOGGER.addHandler(handler);
			LOGGER.setUseParentHandlers(false);
		} catch (IOException e) {
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		try {
			
			reader = new FileReader(PROP_FILE);
			properties=new Properties();  
			properties.load(reader);  
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}  
    
		clientUserService = new ClientUserService();
		clientLineService = new ClientLineService();
		clientZsmdpService = new ClientZsmdpService();
		multicastService = new MulticastService();
		System.setProperty("java.security.policy",properties.getProperty("POLICY_FILE"));
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		try {
			String name = "ReportServer";
			Registry registry = LocateRegistry.getRegistry(Integer.parseInt(properties.getProperty("reportServerPort")));
			reportServiceInterface = (ReportServiceInterface) registry.lookup(properties.getProperty("serverName"));
			if(reportServiceInterface != null) {
				System.out.println("Razlicito");
			}else {
				System.out.println("nije");
			}
		}catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(Main.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
		launch(args);
		
	}
}
