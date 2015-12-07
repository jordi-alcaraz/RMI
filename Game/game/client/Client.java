package game.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;

public class Client {

    private Client() {}

    public static void main(String[] args) {

        String host = (args.length < 1) ? null : args[0];
        if(args.length > 0 )
        	System.out.println(args[0]+ " "+args.length+ "  " +host);
        try {
            Registry registry = LocateRegistry.getRegistry(host);
            Hello stub = (Hello) registry.lookup("Hello");
        	int num = 0;
        	String response = stub.sayHello(num);
            System.out.println("response: " + response);
            ArrayList<Integer> numbers = new ArrayList<Integer>();
            numbers = stub.myNumbers();
            for (int i = 0; i < numbers.size(); i++) {
    			System.out.print(numbers.get(i) +" ");
    		}
            System.out.println("");
            System.out.println(numbers.size());
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
