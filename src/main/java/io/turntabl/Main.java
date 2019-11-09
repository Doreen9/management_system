package io.turntabl;

import java.util.*;
import java.util.stream.Collectors;


public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static ClientController clientController = new ClientController();

    public static void mainMenu() {
        System.out.println("\033[1;34m  \t ************************************************************************************ \033[0m");
        System.out.println();
        System.out.println("\t \t \t  \t \033[0;93m Welcome to Turntabl Client Management System(TCMS) \033[0m");
        System.out.println();
        System.out.println("\033[1;34m \t ************************************************************************************** \033[0m");
        System.out.println();
        System.out.println("\033[1;34m1.\033[0m To add new client details, enter '1' ");
        System.out.println();
        System.out.println("\033[1;34m2.\033[0m To view all clients, enter '2' ");
        System.out.println();
        System.out.println("\033[1;34m3.\033[0m To search, update and delete a client, enter '3' ");
        System.out.println();
        System.out.println("\033[1;34m4.\033[0m To exit, enter '4'");
        System.out.println();

    }

    public static Map<String, String> optionToAdd() {
        Map<String, String> clientDetails = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;93m  TCMS Add new Client Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("Enter Client Name: ");
        String name = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Address: ");
        String address = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Telephone: ");
        String telephone = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's email:");
        String email = scanner.nextLine();
        System.out.println();
        clientDetails.put("name", name);
        clientDetails.put("address", address);
        clientDetails.put("telephone", telephone);
        clientDetails.put("email", email);

        return clientDetails;

    }

    public static void clientAddFunction(Client client){
        System.out.println();
        Map<String, String> response =  clientController.addNewClient(client);
        if (response.get("code").equals("00")){
            System.out.println("\033[0;92m"+ response.get("msg") + "\033[0m");
        }else{
            System.out.println("\033[0;91m Oops!!!, something went wrong, try again later\033[0m");
        }


    }

    public static String optionToEnterClientId() {
        System.out.println();
        System.out.println("\033[0;93m Enter the Client's ID to select client \033[0m");
        return scanner.nextLine();

    }

    public static void optionToDeleteClient() {
        System.out.println();
        System.out.println("\033[0;93mAre you sure you want to delete client? y/n: \033[0m ");
        System.out.println();
        System.out.println("\033[1;34m1.\033[0m Enter '1' to confirm  ");
        System.out.println();
        System.out.println("\033[1;34m2.\033[0m Enter '2' to decline" );
        System.out.println();

    }

    public static void optionToUpdateClient() {
        System.out.println("\033[1;34m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;93m  TCMS Update Client's detsils Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[1;34m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("\033[1;34m1.\033[0m Enter '1' to change Name");
        System.out.println();
        System.out.println("\033[1;34m2.\033[0m Enter '2' to change Address");
        System.out.println();
        System.out.println("\033[1;34m3.\033[0m Enter '3' to change Telephone number");
        System.out.println();
        System.out.println("\033[1;34m4.\033[0m Enter '4' to change Email Address");
        System.out.println();
        System.out.println("\033[1;34m5.\033[0m Enter '5' to go to main menu");

    }

    public static void optionToDeleteUpdate() {
        System.out.println();
        System.out.println("\033[1;34m1.\033[0m Enter '1' to delete client");
        System.out.println();
        System.out.println("\033[1;34m2.\033[0m Enter '2' to update client's details");
        System.out.println();
        System.out.println("\033[1;34m3.\033[0m Enter '3' to go to main menu.");

    }


    public static String optionToChangeClientName() {
        System.out.println();
        System.out.println("Enter Client's New name: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientAddress() {
        System.out.println();
        System.out.println("Enter Client's New Address: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientTelephone() {
        System.out.println();
        System.out.println("Enter Client's New Telephone number: ");
        return scanner.nextLine();

    }
    public static String optionToChangeClientEmail() {
        System.out.println();
        System.out.println("Enter Client's New Email: ");
        return scanner.nextLine();

    }

    public static String menuToEnterClientName() {
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("---------------- \033[0;92m  TCMS Search Menu \033[0m    --------------");
        System.out.println();
        System.out.println("\033[0;94m *************************************************************** \033[0m");
        System.out.println();
        System.out.println("Enter Client's name: ");
        return scanner.nextLine();

    }
    public static List<Client> searchClient(){
        String searchClient = menuToEnterClientName();
        String lowerName = searchClient.toLowerCase();
        List<Client> clients = clientController.getAllClients();
        return clients.stream().filter(c -> c.getName().toLowerCase().startsWith(lowerName)).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        mainMenu();
        while (true){
            String response = scanner.nextLine();
            try {
                int actualResponse = Integer.parseInt(response);
                if (actualResponse < 1 || actualResponse > 4) {
                    System.out.println("\033[1;31m Input not valid option!! \033[0m");
                } else if (actualResponse == 1) {
                    System.out.println();
                    Map<String, String> addedClientDetails = optionToAdd();
                    String name = addedClientDetails.get("name");
                    String address = addedClientDetails.get("address");
                    String telephone = addedClientDetails.get("telephone");
                    String email = addedClientDetails.get("email");
                    Client clientAdded = new Client(name, address, telephone, email);
                    mainMenu();
                    clientAddFunction(clientAdded);
                } else if (actualResponse == 2) {
                    mainMenu();
                    System.out.println();
                    List<Client> clientDetails = clientController.getAllClients();
                    clientController.printFormat(clientDetails);

                } else if (actualResponse == 3) {
                    List<Client> clientName = searchClient();
                    if (clientName.size() == 0) {
                        mainMenu();
                        System.out.println("\033[1;31m Name Entered does not exist in Client List \033[0m");
                        System.out.println();

                    } else {
                        clientController.printFormat(clientName);
                        optionToDeleteUpdate();
                         while (true) {
                                System.out.println();
                                 String updateOption = scanner.nextLine();
                            try {
                                int actualUpdateOption = Integer.parseInt(updateOption);
                                if (actualUpdateOption == 1) {
                                optionToDeleteClient();
                                String deleteOption = scanner.nextLine();
                                int actualDeleteOption = Integer.parseInt(deleteOption);
                                if (actualDeleteOption == 1) {
                                    String clientId = optionToEnterClientId();
                                    Map<String, String> errorCodes = clientController.removeClient(clientId);
                                    mainMenu();
                                    if (errorCodes.get("code").equals("00")) {
                                        System.out.println();
                                        System.out.println("\033[0;92m" + errorCodes.get("msg") + "\033[0m");
                                        System.out.println();
                                    } else {
                                        System.out.println(errorCodes.get("msg"));
                                    }
                                    break;
                                } else if (actualDeleteOption == 2) {
                                    mainMenu();
                                    System.out.println();
                                    System.out.println("\033[0;92mAborted!!\033[0m");
                                    System.out.println();
                                    break;
                                } else {
                                    System.out.println();
                                    System.out.println("\033[1;31m Enter a valid option\033[0m");
                                    System.out.println();
                                }
                            } else if (actualUpdateOption == 2) {
                                String clientId = optionToEnterClientId();
                                optionToUpdateClient();
                                String updateOptionResponse = scanner.nextLine();
                                mainMenu();
                                int updateOptionResponse1 = Integer.parseInt(updateOptionResponse);
                                if (updateOptionResponse1 == 1) {
                                    String newClientName = optionToChangeClientName();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", newClientName);
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", "");
                                    mapOfNames.put("email", "");
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")) {
                                        System.out.println();
                                        System.out.println("\033[0;92m" + mapOfErrorCodes.get("msg") + "\033[0m");
                                    } else {
                                        System.out.println();
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }
                                } else if (updateOptionResponse1 == 2) {
                                    String newClientAddress = optionToChangeClientAddress();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", newClientAddress);
                                    mapOfNames.put("phoneNumber", "");
                                    mapOfNames.put("email", "");
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")) {
                                        System.out.println();
                                        System.out.println("\033[0;92m" + mapOfErrorCodes.get("msg") + "\033[0m");
                                    } else {
                                        System.out.println();
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }
                                } else if (updateOptionResponse1 == 3) {
                                    String newClientTelephone = optionToChangeClientTelephone();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", newClientTelephone);
                                    mapOfNames.put("email", "");
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")) {
                                        System.out.println();
                                        System.out.println("\033[0;92m" + mapOfErrorCodes.get("msg") + "\033[0m");
                                    } else {
                                        System.out.println();
                                        System.out.println(mapOfErrorCodes.get("msg"));
                                    }
                                } else if (updateOptionResponse1 == 4) {
                                    String newClientEmail = optionToChangeClientEmail();
                                    Map<String, String> mapOfNames = new HashMap<>();
                                    mapOfNames.put("name", "");
                                    mapOfNames.put("address", "");
                                    mapOfNames.put("phoneNumber", "");
                                    mapOfNames.put("email", newClientEmail);
                                    mapOfNames.put("id", clientId);
                                    Map<String, String> mapOfErrorCodes = clientController.updateClient(mapOfNames);
                                    if (mapOfErrorCodes.get("code").equals("00")) {
                                        System.out.println();
                                        System.out.println("\033[0;92m" + mapOfErrorCodes.get("msg") + "\033[0m");
                                    } else {
                                        System.out.println();
                                        System.out.println("\033[1;31" + mapOfErrorCodes.get("msg") + "\033[0m");
                                    }
                                }
                                break;

                            } else if(actualUpdateOption == 3){
                                    mainMenu();
                                    break;
                                }
                                else if (actualUpdateOption > 2) {
                                System.out.println();
                                System.out.println("\033[1;31mPlease enter a valid option!\033[0m ");
                            }

                        } catch (NumberFormatException exception) {
                            System.out.println();
                            System.out.println("\033[1;31mInput not number\033[0m");
                        }
                    }

                }
            }
                else if(actualResponse == 4){
                    break;
                }


            }
            catch(NumberFormatException exception){
                System.out.println();
                System.out.println("\033[1;31mInput not number\033[0m");


            }

        }


    }

}

