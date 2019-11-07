package io.turntabl;

import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

    public static void menu(){

        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("1. To add new client details, enter '1' ");
        System.out.println();
        System.out.println("2. To view all clients, enter '2' ");
        System.out.println();
        System.out.println("3. To search, update and delete a client, enter '3' ");
        System.out.println();
        System.out.println("4. To exit, enter '4'");
        System.out.println();

    }
    public static void input1(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("Enter Client Name: ");
        String name  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Address: ");
        String address  = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's Telephone: ");
        String telephone = scanner.nextLine();
        System.out.println();
        System.out.println("Enter Client's email: ");
        String email  = scanner.nextLine();
        System.out.println();

        Client client = new Client(0, name,address,telephone,email);
        System.out.println("Client Id:"+ client.getID());
        ClientController clientController = new ClientController();
        System.out. println("Client with the following details added successfully");
        System.out.println("Client's name: " + name);
        System.out.println("Client's Address: " + address);
        System.out.println("Client's Telephone: " + telephone);
        System.out.println("Client's Email: " + email);
        System.out.println();

        Map<String,String> response =  clientController.addNewClient(client);
        if (response.get("code").equals("00")){
            System.out.println(response.get("msg"));
        }else{
            System.out.println("Oops!!!, something went wrong, try again later");
        }



    }

    public static void input2(){
        ClientController clientController = new ClientController();
        List<Client> clients = clientController.getAllClients();
        System.out.println("View All Clients: "+ clients);
    }
    public static void input3(){
        System.out.println("----------------Welcome to Turntabl Client Management System(TCMS)--------------");
        System.out.println();
        System.out.println("Enter Client's name: ");


    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        menu();
        while(true) {
            String response = scanner.nextLine();
            try {
                int actualResponse = Integer.parseInt(response);
                if (actualResponse > 4) {
                    System.out.println("Please enter a valid option ");
                } else if (actualResponse == 1) {
                    input1();
                } else if (actualResponse == 2) {
                    input2();
                } else if(actualResponse == 3){
                    input3();
                }
                else if (actualResponse == 4) {
                    break;
                }
            } catch (NumberFormatException exception) {
                System.out.println("Input is not an integer");

            }
        }

        }

    //Check the user input
            //Delete a Client
            //Code goes here
            /*System.out.println("Client has been Deleted Successfully");
            break;
        case "4":
            //Exit from the application
            System.out.println("Exiting...");
            System.exit(0);
        default:
            //Inform user in case of invalid choice.
            System.out.println("Invalid choice. Read the options carefully...");
    }*/
}

