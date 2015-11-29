
package game;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
    String sayHello(int num) throws RemoteException;
    public String disconnect( ) throws RemoteException;
}
