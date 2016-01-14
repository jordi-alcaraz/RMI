
package game.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;

//Functions that can be called by the client
public interface Hello extends Remote {
	//Safely closes the server
	public String disconnect( ) throws RemoteException;
	//Connect to the server
  public boolean connect() throws RemoteException;
  //Restart the game
  public boolean reset() throws RemoteException;
  //Restart the game
  public boolean winner(int col, int row, int player) throws RemoteException, ServerNotActiveException;
  //New move done by the server
  public int server_move() throws RemoteException, ServerNotActiveException;
  //Send the move done by the client to the server
  public boolean client_move(int row, int col, int player) throws RemoteException;
  //Gets the game board
  public int[][] get_Board_server() throws RemoteException, ServerNotActiveException;
}