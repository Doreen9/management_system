package io.turntabl.v3.Menus;

import io.turntabl.v2.Implementors.ClientDAOImpl;
import io.turntabl.v2.Transfers.ClientTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menus {
    private static Scanner scanner = new Scanner(System.in);
    private static ClientDAOImpl clientController = new ClientDAOImpl();

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
        clientDetails.put("client_name", name);
        clientDetails.put("client_address", address);
        clientDetails.put("client_telephone", telephone);
        clientDetails.put("client_email", email);

        return clientDetails;

    }

    public static void clientAddFunction() throws InterruptedException {
        Map<String,String> clientDetails = optionToAdd();
        System.out.println();

        Thread addClientsThread = new Thread(()->{
            try{
                Thread.sleep(3000);
                clientController.addClient(clientDetails);
            }catch(InterruptedException e){}
        });
        addClientsThread.setDaemon(true);
        addClientsThread.start();
        addClientsThread.join();
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
    public static List<ClientTO> searchClient(){
        String searchClient = menuToEnterClientName();
        List<ClientTO> clients = clientController.searchClientByName(searchClient);
        return clients;
    }

    public  void main() {
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

                    Thread addClientsThread = new Thread(()->{
                        try{
                            Thread.sleep(3000);
                            clientController.addClient(addedClientDetails);
                        }catch(InterruptedException e){}
                    });
                    addClientsThread.setDaemon(true);
                    addClientsThread.start();
                    addClientsThread.join();

                    mainMenu();
                } else if (actualResponse == 2) {
                    mainMenu();
                    System.out.println();
                    Thread viewAllClientsThread = new Thread(()->{
                        try{
                            Thread.sleep(3000);
                            List<ClientTO> clientDetails = clientController.getAllClients();
                            clientController.printFormat(clientDetails);
                        }catch(InterruptedException e){}
                    });
                    viewAllClientsThread.setDaemon(true);
                    viewAllClientsThread.start();
                    viewAllClientsThread.join();




                } else if (actualResponse == 3) {
                    List<ClientTO> clientName = searchClient();
                    if (clientName.size() == 0) {
                        mainMenu();
                        System.out.println("\033[1;31m Name Entered does not exist in Client List \033[0m");
                        System.out.println();

                    } else {
                        Thread searchClient = new Thread(()->{
                            try{
                                Thread.sleep(3000);
                                clientController.printFormat(clientName);
                            }catch(InterruptedException e){}
                        });
                        searchClient.setDaemon(true);
                        searchClient.start();
                        searchClient.join();
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
                                        Thread deleteClient = new Thread(()->{
                                            try{
                                                Thread.sleep(3000);
                                                clientController.deleteClient(Integer.parseInt(clientId));
                                            }catch(InterruptedException e){}
                                        });
                                        deleteClient.setDaemon(true);
                                        deleteClient.start();
                                        deleteClient.join();
                                        mainMenu();
                                        break;
                                    } else if (actualDeleteOption == 0) {
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
                                        mapOfNames.put("client_name", newClientName);
                                        mapOfNames.put("client_address", "");
                                        mapOfNames.put("client_telephone", "");
                                        mapOfNames.put("client_email", "");
                                        mapOfNames.put("client_id", clientId);
                                        Thread updateclientName = new Thread(()->{
                                            try{
                                                Thread.sleep(3000);
                                                clientController.updateClient(mapOfNames);
                                            }catch(InterruptedException e){}
                                        });
                                        updateclientName.setDaemon(true);
                                        updateclientName.start();
                                        updateclientName.join();
                                    } else if (updateOptionResponse1 == 2) {
                                        String newClientAddress = optionToChangeClientAddress();
                                        Map<String, String> mapOfNames = new HashMap<>();
                                        mapOfNames.put("client_name", "");
                                        mapOfNames.put("client_address", newClientAddress);
                                        mapOfNames.put("client_telephone", "");
                                        mapOfNames.put("client_email", "");
                                        mapOfNames.put("client_id", clientId);
                                        Thread updateclientAddress = new Thread(()->{
                                            try{
                                                Thread.sleep(3000);
                                                clientController.updateClient(mapOfNames);
                                            }catch(InterruptedException e){}
                                        });
                                        updateclientAddress.setDaemon(true);
                                        updateclientAddress.start();
                                        updateclientAddress.join();
                                    } else if (updateOptionResponse1 == 3) {
                                        String newClientTelephone = optionToChangeClientTelephone();
                                        Map<String, String> mapOfNames = new HashMap<>();
                                        mapOfNames.put("client_name", "");
                                        mapOfNames.put("client_address", "");
                                        mapOfNames.put("client_phoneNumber", newClientTelephone);
                                        mapOfNames.put("client_email", "");
                                        mapOfNames.put("client_id", clientId);
                                        Thread updateclientTelephone = new Thread(()->{
                                            try{
                                                Thread.sleep(3000);
                                                clientController.updateClient(mapOfNames);
                                            }catch(InterruptedException e){}
                                        });
                                        updateclientTelephone.setDaemon(true);
                                        updateclientTelephone.start();
                                        updateclientTelephone.join();
                                    } else if (updateOptionResponse1 == 4) {
                                        String newClientEmail = optionToChangeClientEmail();
                                        Map<String, String> mapOfNames = new HashMap<>();
                                        mapOfNames.put("client_name", "");
                                        mapOfNames.put("client_address", "");
                                        mapOfNames.put("client_telephone", "");
                                        mapOfNames.put("client_email", newClientEmail);
                                        mapOfNames.put("client_id", clientId);
                                        Thread updateclientEmail = new Thread(()->{
                                            try{
                                                Thread.sleep(3000);
                                                clientController.updateClient(mapOfNames);
                                            }catch(InterruptedException e){}
                                        });
                                        updateclientEmail.setDaemon(true);
                                        updateclientEmail.start();
                                        updateclientEmail.join();
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
            catch(NumberFormatException | InterruptedException exception){
                System.out.println();
                System.out.println("\033[1;31mInput not number\033[0m");


            }

        }


    }
}
