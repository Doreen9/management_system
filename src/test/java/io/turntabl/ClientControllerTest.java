package io.turntabl;

import org.junit.Test;
import io.turntabl.ClientController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ClientControllerTest {

    @Test
    public void testDeleteClient() {
        ClientController clientController = new ClientController();
        Map<String,String> actual = clientController.removeClient("194029");
        Map<String,String> expected = new HashMap<>();
        expected.put("code","00");
        expected.put("msg","Client deleted Successfully");

        assertEquals(expected,actual);
    }

    @Test
    public void testUpdateClient() {
        ClientController clientController = new ClientController();
        Map<String,String> updateData = new HashMap<>();
        updateData.put("name","");
        updateData.put("address","Legon");
        updateData.put("phoneNumber","055555555");
        updateData.put("email","yaaMaame@gmail.com");
        updateData.put("id","738811");

        Map<String,String> actual = clientController.updateClient(updateData);
        Map<String,String> expected = new HashMap<>();
        expected.put("code","00");
        expected.put("msg","Clients details Updated Successfully");

        assertEquals(expected,actual);
    }


}