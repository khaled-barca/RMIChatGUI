package sample;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientImpl extends UnicastRemoteObject implements ClientInt {
    transient ChatController controller;
    ChatRoomServerInt chatRoomServer;
    public ClientImpl() throws RemoteException {
        Registry registry = LocateRegistry.getRegistry(4000);
        try {
            chatRoomServer = (ChatRoomServerInt) registry.lookup("chatRoom");
            chatRoomServer.register(this);
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message) throws RemoteException {
        chatRoomServer.broadcast(message);
    }

    public void setController(ChatController controller) {
        this.controller = controller;
    }

    public void closeConnection() throws RemoteException {
        chatRoomServer.unregister(this);
    }

    @Override
    public void receiveMsg(Message message) throws RemoteException {
        controller.displayMessage(message);
    }
}
