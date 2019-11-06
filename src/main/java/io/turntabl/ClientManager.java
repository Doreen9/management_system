package io.turntabl;

import java.util.ArrayList;
import java.util.List;

public class ClientManager<index> {
    private List<Client> listOfClient;

    public ClientManager(){
        this.listOfClient = new ArrayList<>();
    }
    public int addClient(Client c){
        this.listOfClient.add(c);
        return this.listOfClient.size();
    }
    public int count(){
        return this.listOfClient.size();
    }
    public int getClient(int index){
        if (index < 0 || index >= count())
            return Integer.parseInt(null);
        return index;
    }
    //return this.listOfClient.get(index);

}
