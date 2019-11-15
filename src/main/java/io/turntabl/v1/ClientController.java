package io.turntabl.v1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import io.turntabl.Utilities.ConsoleTable;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

public class ClientController {
    private File filename = new File("ClientStore.json");

    public Map<String,String> addNewClient(Client client){
        Map<String,String> response = new HashMap<>();
        try{
            if (!filename.exists()){
                filename.createNewFile();
            }
            if (filename.exists()) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                // construct Type that tells Gson about the generic type
                Type dtoListType = new TypeToken<List<Client>>(){}.getType();
                FileReader fr = new FileReader(filename);
                List<Client> dtos = gson.fromJson(fr, dtoListType);
                fr.close();
                // If it was an empty one create initial list
                if(dtos == null || dtos.isEmpty()) {
                    dtos = new ArrayList<>();
                }
                // Add new item to the list
                dtos.add(client);
                // No append replace the whole file
                FileWriter fw  = new FileWriter(filename);
                gson.toJson(dtos, fw);
                fw.close();
                response.put("code","00");
                response.put("msg","New Client Added Successfully!!!");
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();

            response.put("01","Oops!!,something went wrong, try again later.");
        } catch (IOException e) {
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Oops!!,something went wrong, try again later.");
        }
        return response;
    }
    public List<Client> getAllClients(){

        try {
            if (!filename.exists()){
                filename.createNewFile();
            }
            Gson gson = new Gson();
            Type dtoListType = new TypeToken<List<Client>>(){}.getType();
            FileReader fr = new FileReader(filename);
            List<Client> clients = gson.fromJson(fr, dtoListType);
            fr.close();
            if(clients == null || clients.isEmpty()){
                return new ArrayList<>();
            }else {
                return clients;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();

        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public Map<String,String> updateClient(Map<String,String> requestData){

        Map<String,String> response = new HashMap<>();
        Optional<Client> client;
        String name;
        String address;
        String phoneNumber;
        String email;
        if (requestData.get("id").isEmpty()){
            response.put("code","01");
            response.put("msg","Client ID is empty,kindly enter Client ID");
        }else {
            Integer id = Integer.parseInt(requestData.get("id"));
            List<Client> clientList = this.getAllClients();
            Optional<Client> clientObj = clientList.stream()
                    .filter(client1 -> client1.getID() == id)
                    .findFirst();
            if (clientObj.isPresent()){
                client = Optional.of(clientObj.get());
            }else {
                client = Optional.empty();
            }
            if (client.isPresent()){
                if (!requestData.get("name").isEmpty()){
                    name = requestData.get("name");
                }else {
                    name = client.get().getName();
                }
                if (!requestData.get("address").isEmpty()){
                    address = requestData.get("address");
                }else {
                    address = client.get().getAddress();
                }
                if (!requestData.get("phoneNumber").isEmpty()){
                    phoneNumber = requestData.get("phoneNumber");
                }else {
                    phoneNumber = client.get().getPhoneNumber();
                }
                if (!requestData.get("email").isEmpty()){
                    email = requestData.get("email");
                }else {
                    email = client.get().getEmail();
                }
                Client newClient = new Client(name,address,phoneNumber,email);
                this.addNewClient(newClient);
                this.removeClient(requestData.get("id"));
                response.put("code","00");
                response.put("msg","Clients details Updated Successfully");

            }else {
                String msg = "Client doesn't exist";
                response.put("code","01");
                response.put("msg",msg);
            }
        }

        return response;
    }
    public Map<String,String> removeClient(String id){
        List<Client> clientList = this.getAllClients();
        Map<String,String> response = new HashMap<>();
        Integer clientId = Integer.parseInt(id);
        List<Client> newclients = clientList.stream()
                .filter(client1 -> client1.getID() != clientId)
                .collect(Collectors.toList());
        try {
            if (filename.exists()){
                filename.delete();
                filename.createNewFile();
            }
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Type dtoListType = new TypeToken<List<Client>>(){}.getType();
            FileReader fr = new FileReader(filename);
            List<Client> dtos = gson.fromJson(fr, dtoListType);
            fr.close();
            // If it was an empty one create initial list
            if(dtos == null || dtos.isEmpty()) {
                dtos = new ArrayList<>();
            }
            // Add new item to the list
            for(Client client: newclients){
                dtos.add(client);
            }

            // No append replace the whole file
            FileWriter fw  = new FileWriter(filename);
            gson.toJson(dtos, fw);
            fw.close();
            response.put("code","00");
            response.put("msg","Client deleted Successfully");
        }catch (FileNotFoundException e) {
            e.printStackTrace();
            response.put("code","01");
            response.put("msg","Oops!!,something went wrong, try again later.");

        } catch (IOException e) {
            e.printStackTrace();
            response.put("code","02");
            response.put("msg","Oops!!,something went wrong, try again later.");
        }
        return response;
    }
    public Optional<String> searchClient(String clientName) {
        String lowerCaseName = clientName.toLowerCase();
        List<Client> clients = this.getAllClients();
        Optional<String> searchClient = clients.stream()
                .filter(c -> c.getName().toLowerCase()
                .startsWith(lowerCaseName))
                .map(Client::getEmail)
                .findFirst();
        return searchClient;

    }

    public void printFormat(List<Client> clientList){

        ConsoleTable st = new ConsoleTable();
        st.setShowVerticalLines(false);//if false (default) then no vertical lines are shown
        st.setHeaders("Client ID", "Name", "Address","Telephone Number","Email");
        for (Client client: clientList){
            st.addRow(String.valueOf(client.getID()), client.getName(), client.getAddress(),client.getPhoneNumber(),client.getEmail());
        }
        st.print();
    }
}