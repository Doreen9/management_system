package io.turntabl;

import java.io.Serializable;
import java.util.Random;

public class Client implements Serializable {
    public String name;
    private Integer id;
    private String address;
    private String phoneNumber;
    private String email;

    public Client(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.id = this.getRandomNumberInts();
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getRandomNumberInts(){
        Random rnd = new Random();
        int clientID = 100000 + rnd.nextInt(900000);
        return clientID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Client Details: {" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}