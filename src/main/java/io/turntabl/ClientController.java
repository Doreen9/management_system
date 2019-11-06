package io.turntabl;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private String filename = "data.ser";
    private List<Client> clientList = new ArrayList<>();
    private List<Client> getClientList = new ArrayList<>();

    public void addNewClient(Client client){

        try{
            ObjectOutputStream clientObject = new ObjectOutputStream(new FileOutputStream(filename));
            clientList.add(client);
            clientObject.writeObject(clientList);
            clientObject.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<Client> getAllClients(){

        try {
            ObjectInputStream getClientObject = new ObjectInputStream(new FileInputStream(filename));
            getClientList=(List<Client>)getClientObject.readObject();
            getClientObject.close();
            return getClientList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return getClientList;
        } catch (IOException e) {
            e.printStackTrace();
            return getClientList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return getClientList;
        }
    }
}
