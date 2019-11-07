package io.turntabl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

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
                if(dtos == null) {
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
            response.put("code","01");
            response.put("msg","Oops!!,something went wrong, try again later.");
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
}
