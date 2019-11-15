package io.turntabl;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ClientControllerTest {

//    @Test
//    public void testDeleteClient() {
//        ClientController clientController = new ClientController();
//        Map<String,String> actual = clientController.removeClient("519900");
//        Map<String,String> expected = new HashMap<>();
//        expected.put("code","00");
//        expected.put("msg","Client deleted Successfully");
//
//        assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testUpdateClient() {
//        ClientController clientController = new ClientController();
//        Map<String,String> updateData = new HashMap<>();
//        updateData.put("name","");
//        updateData.put("address","");
//        updateData.put("phoneNumber","055555555");
//        updateData.put("email","maxwell@gmail.com");
//        updateData.put("id","540602");
//
//        Map<String,String> actual = clientController.updateClient(updateData);
//        Map<String,String> expected = new HashMap<>();
//        expected.put("code","00");
//        expected.put("msg","Clients details Updated Successfully");
//
//        assertEquals(expected,actual);
//    }
//
//    @Test
//    public void testViewAllClients() {
//        ClientController clientController = new ClientController();
//        List<Client> clientList = clientController.getAllClients();
//        List<String> actualEmails = clientList.stream().
//                map(Client::getEmail).collect(Collectors.toList());
//        List<String>  expectedEmails = Arrays.asList("doreen@gmail.com", "maxwell@gmail.com");
//        assertEquals(expectedEmails, actualEmails);
//    }
//
//    @Test
//    public void testSearchByName() {
//        ClientController clientController = new ClientController();
//        Optional<String> actualEmail = clientController.searchClient("CHARLES");
//        Optional<String> expected = Optional.of("doreen@gmail.com");
//        assertEquals(expected.get(), actualEmail.get());
//
//    }
}
