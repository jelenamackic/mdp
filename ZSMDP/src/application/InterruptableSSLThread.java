package application;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.SSLSocket;

public class InterruptableSSLThread extends Thread{
		
		public SSLSocket sslSocket;

	   public InterruptableSSLThread(SSLSocket sslSocket){
	      this.sslSocket = sslSocket;
	   }
	 
	   
	   @Override
	   public void interrupt(){
	     super.interrupt();  
	     try {
			this.sslSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Logger.getLogger(App.class.getName()).log(Level.WARNING, e.fillInStackTrace().toString());
		}
	   }
	}