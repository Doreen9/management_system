package io.turntabl.v2.Implementors;

import io.turntabl.Utilities.ConsoleTable;
import io.turntabl.v1.Client;
import io.turntabl.v2.DAO.ClientDAO;
import io.turntabl.v2.Transfers.ClientTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {

    //  Database credentials
    static final String username = "sammy";
    static final String password = "turntabl";

    List<ClientTO> clients = new ArrayList<>();


    @Override
    public void addClient(Map<String,String> requestData) {

        try{
            Connection conn = this.getConnection().get();
            PreparedStatement query = conn.prepareStatement("insert into customers(client_name,client_address,client_telephone,client_email) values(?,?,?,?)");
            query.clearParameters();
            query.setString(1,requestData.get("client_name"));
            query.setString(2,requestData.get("client_address"));
            query.setString(3,requestData.get("client_telephone"));
            query.setString(4,requestData.get("client_email"));
            query.executeUpdate();
            query.close();
            System.out.println("Client Added Successfully");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }

    @Override
    public void deleteClient(Integer clientID) {
        try{
            Connection conn = this.getConnection().get();
            PreparedStatement query = conn.prepareStatement("delete from customers where client_id = ?");
            query.clearParameters();
            query.setInt(1,clientID);
            query.executeUpdate();
            query.close();
            System.out.println("Client Deleted Successfully");
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }

    }

    @Override
    public List<ClientTO> getAllClients() {

        try{
            Connection conn = this.getConnection().get();
            PreparedStatement query = conn.prepareStatement(
                    "select * from customers"
            );
            query.clearParameters();
            ResultSet rs = query.executeQuery();
            while(rs.next()){ ;
                clients.add(rowMappper(rs));
            }

        }catch (SQLException sqle){
            System.out.println("Connection err: " + sqle);
        }
        return clients;
    }

    @Override
    public List<ClientTO> searchClientByName(String clientName) {
        List<ClientTO> searchClients = new ArrayList<>();
        try{
            Connection conn = this.getConnection().get();
            PreparedStatement query = conn.prepareStatement(
                    "select * from customers where client_name like ?"
            );
            query.clearParameters();
            query.setString(1,clientName + "%");
            ResultSet rs = query.executeQuery();
            while(rs.next()){ ;
                searchClients.add(rowMappper(rs));
            }


        }catch (SQLException sqle){
            System.out.println("Connection err: " + sqle);
        }
        return searchClients;
    }


    public Optional<ClientTO> searchClientByID(Integer clientID) {
        try{
            Optional<ClientTO> client = Optional.empty();
            Connection conn = this.getConnection().get();
            PreparedStatement query = conn.prepareStatement(
                    "select * from customers where client_id = ?"
            );
            query.clearParameters();
            query.setInt(1,clientID);
            ResultSet rs = query.executeQuery();
            while(rs.next()){ ;
                client = Optional.of(rowMappper(rs));
            }
            return client;

        }catch (SQLException sqle){
            System.out.println("Connection err: " + sqle);
            return Optional.empty();
        }

    }

    @Override
    public void updateClient(Map<String, String> requestData) {
        try{
            Integer id = Integer.parseInt(requestData.get("client_id"));
            Optional<ClientTO> client = searchClientByID(Integer.parseInt(requestData.get("client_id")));

            if (client.isPresent()){
                String client_name;
                String client_address;
                String client_telephone;
                String client_email;
                if (requestData.get("client_name").isEmpty()){
                    client_name = client.get().getName();
                }else{
                    client_name = requestData.get("client_name");
                }
                if (requestData.get("client_address").isEmpty()){
                    client_address = client.get().getAddress();
                }else{
                    client_address = requestData.get("client_address");
                }
                if (requestData.get("client_telephone").isEmpty()){
                    client_telephone = client.get().getPhoneNumber();
                }else{
                    client_telephone = requestData.get("client_telephone");
                }
                if (requestData.get("client_email").isEmpty()){
                    client_email = client.get().getEmail();
                }else{
                    client_email = requestData.get("client_email");
                }
                Connection conn = this.getConnection().get();
                PreparedStatement query = conn.prepareStatement(
                        "update customers set client_name = ?, client_address = ?,client_telephone = ?, client_email = ? where client_id = ?"
                );
                query.clearParameters();
                query.setString(1,client_name);
                query.setString(2,client_address);
                query.setString(3,client_telephone);
                query.setString(4,client_email);
                query.setInt(5,id);
                query.executeUpdate();
                System.out.println("Client Details Updated Successfully");

            }else {
                System.out.println("Client with this "+id+" ID does not exist");
            }


        }catch (SQLException sqle){
            System.out.println("Connection err: " + sqle);
        }
    }

    public Optional<Connection> getConnection() {

        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql:managementdb";
            try {
                Optional<Connection> db_con = Optional.ofNullable(DriverManager.getConnection(url, username, password));
                return db_con;
            }catch (SQLException e){
                e.printStackTrace();
                System.out.println("Connection  Error: " + e.getMessage());
                return Optional.empty();
            }
        }catch (ClassNotFoundException e ){
            System.out.println("Driver Class Error: " + e.getMessage());
            return Optional.empty();
        }
    }
    private ClientTO rowMappper(ResultSet rs) throws SQLException {
        ClientTO client = new ClientTO(
                rs.getInt("client_id"),
                rs.getString("client_name"),
                rs.getString("client_address"),
                rs.getString("client_telephone"),
                rs.getString("client_email")
        );
        return client;
    }
    public void printFormat(List<ClientTO> clientList){

        ConsoleTable st = new ConsoleTable();
        st.setShowVerticalLines(false);//if false (default) then no vertical lines are shown
        st.setHeaders("Client ID", "Name", "Address","Telephone Number","Email");
        for (ClientTO client: clientList){
            st.addRow(String.valueOf(client.getId()), client.getName(), client.getAddress(),client.getPhoneNumber(),client.getEmail());
        }
        st.print();
    }
}
