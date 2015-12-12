
package game.client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Hello extends Remote {
    //----------------
    public String disconnect( ) throws RemoteException;
    public boolean connect() throws RemoteException;
    public boolean reset() throws RemoteException;
}
