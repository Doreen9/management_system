package io.turntabl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Blob;
import java.util.*;
import java.util.stream.Collectors;

public class ClientController {
    private static final Path FILEPATH = Paths.get("./resources/clientsInformation.txt");
    private File filename = new File("ClientStore.json");
    private List<Client> clientList = new ArrayList<>();
    private List<Client> getClientList = new ArrayList<>();

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
    public boolean delete(int id) throws IOException {
        if ( fileIsReady()) {
            if (Files.readAllLines(FILEPATH).size() > 0) {
                List<Client> removed = filterClientById(id);
                writingFilteredClientToFile(removed);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    private void writingFilteredClientToFile(List<Client> removed) throws IOException {
        Files.delete(FILEPATH);
        if (fileIsReady()) {
            removed.forEach(clientData -> {
                try {
                    Files.write(FILEPATH, clientToCsvString(clientData).getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private String clientToCsvString(Client client){
        String[] split = client.toCSV().split(",");
        if (split.length != 5){
            return "";
        }
        return ( split[0] + "," + split[1] + "," + split[2] + "," + split[3] + "," + split[4] + "\n");
    }

    private Client stringToClient(String s) {
        String[] split = s.split(",");
        if ( split.length != 5){
            return new Client(0,"","", "","");
        }
        int id = Integer.parseInt(split[0]);
        return new Client(id, split[1], split[2], split[3], split[4]);
    }

    private boolean fileIsReady() throws IOException {
        if (!Files.isDirectory(Paths.get("./resources"))){
            Files.createDirectory(Paths.get("./resources"));
        }
        if ( Files.notExists(FILEPATH)) {
            Files.createFile(FILEPATH);
        }
        return true;
    }

    private List<Client> filterClientById(int id) throws IOException {
        return readFile()
                .stream()
                .filter(line -> !hasSameId(id, line))
                .map(this::stringToClient)
                .collect(Collectors.toList());
    }

    private List<String> readFile() throws IOException {
        if ( fileIsReady()) {
            List<String> allLines = Files.readAllLines(FILEPATH);
            return  allLines.stream()
                    .filter(line -> !line.isEmpty() && !line.equals("0,,,,"))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    private boolean hasSameId(int id, String line) {
        int x = Integer.parseInt(line.split(",")[0]);
        return (x == id);
    }

    private int generateId() throws IOException {
        List<String> allData = readFile();
        int collectionSize = allData.size();
        if ( collectionSize == 0){
            return 1;
        }

        String ints = allData.get(collectionSize - 1).split(",")[0];
        return  ( Integer.parseInt(ints) +1);
    }

}