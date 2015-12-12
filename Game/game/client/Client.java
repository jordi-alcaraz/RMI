package game.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        if(args.length > 0 )
        	System.out.println(args[0]+ " "+args.length+ "  " +host);
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
            System.out.println("Connecting...");
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
