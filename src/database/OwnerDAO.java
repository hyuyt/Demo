package database;


import model.Owner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerDAO {
    public void insertOwner(Owner owner) {
        String sql = "INSERT INTO owner (fullName, phoneNum, email) VALUES (?, ?, ?)";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, owner.getFullName());
            statement.setString(2, owner.getPhoneNum());
            statement.setString(3, owner.getEmail());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println(" Staff inserted successfully!");
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void getAllOwners() {
        String sql = "SELECT * FROM owner";
        Connection connection = DatabaseConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("\n--- ALL OWNERS FROM DATABASE ---");
            while (resultSet.next()) {
                String name = resultSet.getString("fullName");
                String phone = resultSet.getString("phoneNum");
                String mail = resultSet.getString("email");
                System.out.println("Name: " + name);
                System.out.println("Phone number: " + phone);
                System.out.println("Email: " + mail);
                System.out.println("---");
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println(" Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }
}