
package game.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Hello extends Remote {
    public String disconnect( ) throws RemoteException;
    public boolean connect() throws RemoteException;
    public boolean reset() throws RemoteException;
    public boolean winner(int col, int row, int player) throws RemoteException;
    public int server_move() throws RemoteException;
    public boolean client_move(int row, int col, int player) throws RemoteException;
}
