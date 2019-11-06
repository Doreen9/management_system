package io.turntabl;

public class Client {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    public Client(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Client() {

    }

    public String getName() {
        return name;
    }

    public String getAddress(int i) {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void delete(int index) {
    }
}
