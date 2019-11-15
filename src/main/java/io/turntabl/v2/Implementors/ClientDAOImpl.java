package io.turntabl.v2.Implementors;

import io.turntabl.v2.DAO.ClientDAO;
import io.turntabl.v2.Transfers.ClientTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ClientDAOImpl implements ClientDAO {

    //  Database credentials
    static final String username = "samuel-kwakye";
    static final String password = "turntabl";


    @Override
    public void addClient(ClientTO client) {
        Connection conn = this.getConnection().get();

    }

    @Override
    public void deleteClient(Integer clientID) {
        Connection conn = this.getConnection().get();

    }

    @Override
    public List<ClientTO> getAllClients() {
        Connection conn = this.getConnection().get();
        return null;
    }

    @Override
    public List<ClientTO> searchClientByName(String clientName) {
        Connection conn = this.getConnection().get();
        return null;
    }

    @Override
    public void updateClient(Map<String, String> requestData) {
        Connection conn = this.getConnection().get();
    }

    public Optional<Connection> getConnection() {

        try{
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql:northwind";
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
}
