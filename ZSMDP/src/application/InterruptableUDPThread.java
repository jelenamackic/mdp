package application;

import java.net.MulticastSocket;
import java.net.Socket;

public class InterruptableUDPThread extends Thread{

	   private final MulticastSocket socket;

	   public InterruptableUDPThread(MulticastSocket socket){
	      this.socket = socket;
	   }
	 
	   
	   @Override
	   public void interrupt(){
	     super.interrupt();  
	     this.socket.close();
	   }
	}