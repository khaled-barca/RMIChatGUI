package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatRoomServerInt extends Remote {
    public void register(ClientInt client) throws RemoteException;
    public void broadcast(Message message) throws RemoteException;
    public void unregister(ClientInt client) throws RemoteException;
}
