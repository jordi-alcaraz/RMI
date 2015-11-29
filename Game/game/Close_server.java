package game;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Close_server {

    private Close_server() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        //System.out.println(args[0]+ " "+args.length+ "  " +host);
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
        	try{
        		if(stub.disconnect().equals("Server closed")){
        			System.out.println("Server closed, closing client");
        		}
        	}catch (Exception e) {
        		System.err.println("Client exception: " + e.toString());
                e.printStackTrace();
        	}
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
