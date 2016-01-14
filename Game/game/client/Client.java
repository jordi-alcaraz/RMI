package game.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
//Class of our client
public class Client {

    private Client() {}
    //Initializes of client
    public static void main(String[] args) {
    	//Reads the server IP given by command line
        String host = (args.length < 1) ? null : args[0];
        if(args.length > 0 )
        	System.out.println(args[0]+ " "+args.length+ "  " +host);
        try {
        	//Looks if the server is initialized in the given IP 
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println("Connecting...");
        	//Tries to connect to the server and creates the GUI
            // of our game if successful
            if(stub.connect()){
            	System.out.println("Connected");
            	GUI my_gui = new GUI(stub);
            }
            else{
            	System.out.println("Error connecting to the server");
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
