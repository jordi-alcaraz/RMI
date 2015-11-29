
package game;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

public class Server implements Hello {
	private static Hello stub;
    public Server() {}

    public String sayHello(int num ) {
        return "Hello, world!"+ " "+get_random(9, 0);
    }
    
    private int get_random(int max, int min){
        int randomNum = min+ (int) (Math.random() * ( max - min ));
        return randomNum;
    }
    
    public String disconnect( ) {
    	int succesful = 0;
    	String response = null;
    	try{
        	Registry registry = LocateRegistry.getRegistry();
            registry.unbind("Hello");
            UnicastRemoteObject.unexportObject(this, false);
            response = "Server closed";
            Runnable r = new Runnable() {
                public void run() {
                	try {
						Thread.sleep(1000);
	                    System.exit(0);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
                }
            };
            new Thread(r).start();
    	}
    	catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
            succesful = 1;
            response = "Failed to close server";
        }
    	return response;
    }

    public static void main(String args[]) {

        try {
            Server obj = new Server();
            stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);

            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
