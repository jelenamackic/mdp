package model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;



public class SerializationUtil {

	public static void serializeWithXML(User user) {
		
		try {
			File file = new File("users" + File.separator + user.getUsername() + ".out" );
			System.out.println("STa se desilo");
			file.createNewFile();
			System.out.println("NAPRAVILO FAJL " + file.getAbsolutePath());
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(file));
			encoder.writeObject(user);
			encoder.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Prosla serijalizacija");
		
	}

	public static ArrayList<User> deserializeWithXML() {
		long start = System.currentTimeMillis();
		try {
			ArrayList<User> users = new ArrayList<User>();
			File dir = new File("./users");
			System.out.println(dir.isDirectory());
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					System.out.println(child.getName());
					if(child.getName().endsWith(".out")) {
						XMLDecoder decoder = new XMLDecoder(new FileInputStream(child)); 
						User user = (User)decoder.readObject();
						System.out.println(user);
						users.add(user);
						decoder.close();
					}
					  
					 
				}
				return users;
			} else {
				// Handle the case where dir is not really a directory.
				// Checking dir.isDirectory() above would not be sufficient
				// to avoid race conditions with another process that deletes
				// directories.
				return null;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// System.out.println("Trajanje XML deserijalizacije: " +
		// (System.currentTimeMillis() - start));
	}
	public static void deleteFile(String filename) {
		File dir = new File("./users");
		  System.out.println(dir.isDirectory());
		  File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					System.out.println(child.getName());
					if(child.getName().equals(filename + ".out")) {
						child.delete();
						break;
					}
				}
			}
	}

	public static void main(String args[]) {
		User user = new User("jelena","jelena","A");
		serializeWithXML(user);
		deserializeWithXML();
		deleteFile(user.getUsername());
	}

}
