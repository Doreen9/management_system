package io.turntabl;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class Dbtest {
    private static final String url = "jdbc:h2:test";
    public static Optional<Connection> getConnection() {
        try{
            Class.forName("org.h2.Driver");
            try {
                return Optional.ofNullable(DriverManager.getConnection(url, "", ""));
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


    public static void createDb() throws SQLException {

        Connection conn = getConnection().get();
        Statement statement = conn.createStatement();
        statement.execute("create table clients(client_id int primary key, client_name text not null, client_address text not null, client_telephone text not null, client_email text not null)");
        statement.execute("insert into clients values(1, 'David Ameyaw', 'Lapaz', '0558314855', 'davidameyaw@gmail.com')");
        statement.execute("insert into clients values(2, 'Doreen Amankwa', 'Accra', '0505092133', 'doreen@gmail.com')");
        statement.execute("insert into clients values(3, 'Kwakye Osei', 'Capecoast', '0549163989', 'oseikwakye@gmail.com')");
        statement.execute("insert into clients values(4, 'Alex owusu', 'Achimota', '023654786', 'alexowusu@gmail.com')");
    }

    @Test
    public void viewClients() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        List<String> client_details = new ArrayList<>();
        
        try{
        Connection conn = DriverManager.getConnection(url, "", "");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from clients");
        while (rs.next()) {
            client_details.add(rs.getString("client_name"));
            client_details.add(rs.getString("client_address"));
        }
        List<String> details = Arrays.asList("David Ameyaw", "Lapaz", "Doreen Amankwa", "Accra", "Kwakye Osei", "Capecoast", "Alex owusu", "Achimota");
        assertEquals(details, client_details);
        }
        catch(SQLException e){
            System.err.println("Connection error: " + e);
        }

    }

    @Test
    public void countRows() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        try {
        Connection conn = DriverManager.getConnection(url, "", "");
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("select * from clients");
        int count = 0;
        while (rs.next()) {
            count += 1;
        }
        int expected = 4;
        assertEquals(expected, count);
    }
        
        catch(SQLException e){
            System.err.println("Connection error: " + e);
        }

    }


}
