package rmi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import model.ReportDescriptor;

public class ReportServer implements ReportServiceInterface {
	public static final String LOG_FILE = "logs.log";
	public static final String PROP_FILE = "prop.properties";
	public final static Logger LOGGER = Logger.getLogger(ReportServer.class.getName());
	public static Handler handler;
	public static Properties properties;
	public ReportServer() throws RemoteException{
		
	}

	@Override
	public void uploadReport(String username, String stationId, String filename, LocalTime time, long fileSize,
			byte[] data) throws RemoteException {
		System.out.println("Uslo je u upload");
		ReportDescriptor reportDescriptor = new ReportDescriptor(username, stationId, filename, time, fileSize);
		Gson gson = new Gson();
		try {
			System.out.println("Da li ej uslo");
			File file = new File(properties.getProperty("DESCRIPTOR_PATH_STRING") + File.separator + filename + ".out");
			file.createNewFile();
			FileWriter outFileWriter = new FileWriter(file);
			outFileWriter.write(gson.toJson(reportDescriptor));
			outFileWriter.flush();
			outFileWriter.close();
			
		
		
		} catch (IOException e) {
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		
		File file = new File(properties.getProperty("PATH_STRING") + File.separator + filename);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
				e.printStackTrace();
			}
		}
		try {
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(data);
			outputStream.flush();
			outputStream.close();
		
		} catch (FileNotFoundException e) {
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		
		
	}

	@Override
	public byte[] downloadReport(String fileName) throws RemoteException {
		try {
			
			FileInputStream fileInputStream = new FileInputStream(new File(properties.getProperty("PATH_STRING") + File.separator +  fileName));
			System.out.println("DOWNLOAD");
			long size = fileInputStream.getChannel().size();
			byte[] bytes = new byte[(int)size];
			fileInputStream.read(bytes);
			return bytes;
		
		} catch (FileNotFoundException e) {
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		} catch (IOException e) {
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<ReportDescriptor> getInormationAboutAllReports() throws RemoteException{
		ArrayList<ReportDescriptor> arrayList = new ArrayList<ReportDescriptor>();
		File dir = new File("C:\\Users\\admin\\eclwrkspace\\AZSMDP\\reportDescriptors");
		File[] directoryListing = dir.listFiles();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.println(child.getName());
				BufferedReader in;
				try {
					in = new BufferedReader(new FileReader(child));
					ReportDescriptor reportDescriptor = gson.fromJson(in.readLine(), ReportDescriptor.class);
					System.out.println("DES: " + reportDescriptor);
					arrayList.add(reportDescriptor);
					
				} catch (FileNotFoundException e) {
					Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
					e.printStackTrace();
				} catch (JsonSyntaxException e) {
					Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
					e.printStackTrace();
				} catch (IOException e) {
					Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
					e.printStackTrace();
				}
			
			}
		}
		return arrayList;
	}

	
	public static void main(String args[]) {
	    	try {
				handler = new FileHandler(LOG_FILE,true);
				LOGGER.addHandler(handler);
				LOGGER.setUseParentHandlers(false);
			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}
			
			FileReader reader;
			try {
				
				reader = new FileReader(PROP_FILE);
				properties=new Properties();  
				properties.load(reader);  
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			} catch (IOException e) {
				e.printStackTrace();
				Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
			}  
	    
		System.setProperty("java.security.policy","server_policyfile.txt");
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		try {
			ReportServer server = new ReportServer();
			ReportServiceInterface stub = (ReportServiceInterface) UnicastRemoteObject.exportObject(server,
					0);
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.rebind("ReportServer", stub);
			System.out.println("Server starteddddddddd.");
		} catch (Exception e) {
			e.printStackTrace();
			Logger.getLogger(ReportServer.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
	}

}
