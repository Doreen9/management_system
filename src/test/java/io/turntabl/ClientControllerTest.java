package io.turntabl;

import io.turntabl.v2.Implementors.ClientDAOImpl;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ClientControllerTest {

    @Test
    public void testDeleteClient() {
        ClientDAOImpl clientDAO = new ClientDAOImpl();
        Map<String,String> actual = new HashMap<>();
        actual.put("client_name","Hannah");
        actual.put("client_address","Kasoa");
        actual.put("client_telephone","025555555");
        actual.put("client_email","h@gmail.com");
        actual.put("client_id","12");
        //clientDAO.addClient(actual);
        //System.out.println(clientDAO.getAllClients());
        //clientDAO.deleteClient(13);
        //clientDAO.searchClientByName("Hannah Sam");
        clientDAO.updateClient(actual);
        System.out.println(clientDAO.getAllClients());


    }

}
