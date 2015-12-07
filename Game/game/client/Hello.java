
package game.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Hello extends Remote {
	public ArrayList<Integer> myNumbers() throws RemoteException;
    String sayHello(int num) throws RemoteException;
    public String disconnect( ) throws RemoteException;
}
