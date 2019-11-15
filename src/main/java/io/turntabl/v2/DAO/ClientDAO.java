package io.turntabl.v2.DAO;

import io.turntabl.v2.Transfers.ClientTO;

import java.util.List;
import java.util.Map;

public interface ClientDAO {
    public void addClient(ClientTO client);
    public void deleteClient(Integer clientID);
    public List<ClientTO> getAllClients();
    public List<ClientTO> searchClientByName(String clientName);
    public void updateClient(Map<String,String> requestData);
}
