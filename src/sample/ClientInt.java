package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientInt extends Remote {
    public void receiveMsg(Message message) throws RemoteException;
}
