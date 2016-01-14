package game.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//Class to close the server safely 
public class Close_server {
	//Class initializer
    private Close_server() {}

    	
    public static void main(String[] args) {
    	//Select the IP from the remote server
        String host = (args.length < 1) ? null : args[0];
        System.out.println(args[0]+ " "+args.length+ "  " +host);
        try {
        	//Looks for the server in the remote IP
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
        	try{
        		//Disconnect the server
        		if(stub.disconnect().equals("Server closed")){
        			System.out.println("Server closed!");
        		}
        	}catch (Exception e) {
        		System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
        	}
        } catch (Exception e) {
        	if(e.getClass() == java.rmi.NotBoundException.class)
        		System.err.println("Cannot connect to server");
        	else
        	{	
        		System.err.println("Client exception: " + e.getClass());
        		e.printStackTrace();
        	}
        }
    }
}
