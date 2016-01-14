
package game.server;

import game.client.Hello;


import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;


public class Server implements Hello {
	private static Hello stub;
    public Server() {}
    private HashMap<String,Game> current_games = new HashMap<String,Game>();

    public boolean winner(int row, int col, int player) throws ServerNotActiveException{
    	Game this_game = current_games.get(RemoteServer.getClientHost());
    	boolean win = this_game.check_winner(row, col, player);
    	this_game.show_board();
    	return win;
    }
    
    public int server_move() throws ServerNotActiveException{
    	return current_games.get(RemoteServer.getClientHost()).new_move();
    }
    
    public int[][] get_Board_server() throws ServerNotActiveException{
    	return current_games.get(RemoteServer.getClientHost()).game_getBoard();
    }
    
    public boolean client_move(int row, int col, int player){
    	boolean valid = false;
    	try {
    		valid = current_games.get(RemoteServer.getClientHost()).check_move(row, col, player);
		} catch (ServerNotActiveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return valid;
    }
    
    public boolean connect(){
    	String name_client = "";
    	try {
			name_client = RemoteServer.getClientHost();
			if(!current_games.containsKey(name_client))
	    	{
	    		Game my_game = new Game();
	    		current_games.put(name_client, my_game);
	    		
	    	} 
	    	return true;
		} catch (ServerNotActiveException e1) {
			e1.printStackTrace();
			return false;
		} 
    }
    
    public boolean reset(){
    	String name_client = "";
    	try {
			name_client = RemoteServer.getClientHost();
	    	Game my_game = new Game();
	    	current_games.put(name_client, my_game);
	    	return true;
		} catch (ServerNotActiveException e1) {
			e1.printStackTrace();
			return false;
		} 
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


    public static void main(String args[]) {

        try {
        	if(System.getProperty("java.rmi.server.hostname")==null)
        	{
        		Init_ip my_ip = new Init_ip();
        		System.setProperty("java.rmi.server.hostname", my_ip.getIP());
        	}
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
