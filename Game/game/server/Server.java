
package game.server;

import game.client.Hello;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


public class Server implements Hello {
	private static Hello stub;
    public Server() {}
    private Game my_game = new Game("Hello ");

    public String sayHello(int num ) {
    	
        return my_game.getString();
    }
    
    public ArrayList<Integer> myNumbers()    {
	    ArrayList<Integer> numbers = new ArrayList<Integer>();
	    numbers.add(5);
	    numbers.add(11);
	    numbers.add(3);
	    return(numbers);
	}
    
    public String disconnect( ) {
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
            response = "Failed to close server";
        }
    	return response;
    }
    /*
    static void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	if(Inet4Address.class == inetAddress.getClass() )
            	System.out.printf("InetAddress: %s\n", inetAddress.getHostAddress());
        }
        System.out.printf("\n");
     }*/

    public static void main(String args[]) {
    	/*try {
    		Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets))
                displayInterfaceInformation(netint);	
    	}catch (Exception ex) {
            System.err.println("Server exception: " + ex.toString());
            ex.printStackTrace();
        }*/
        try {
            Server obj = new Server();
            stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);

            // Bind the remote object's stub in the registry
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Hello", stub);
            
            System.err.println("Server ready");
        } catch (Exception ex) {
            System.err.println("Server exception: " + ex.toString());
            ex.printStackTrace();
        }
    }
}
