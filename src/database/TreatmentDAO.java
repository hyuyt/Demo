package database;

import model.Surgery;
import model.Treatment;
import model.Vaccination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TreatmentDAO {

    public void insertTreatment(Treatment treatment) {
        String sql = """
        INSERT INTO treatment
        (t_id, name, description, cost, date, v_id,
         treatment_type, surgery_type, duration_minutes,
         vaccine_name, next_due_date)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, treatment.getTreatmentId());
            statement.setString(2, treatment.getTreatmentName());
            statement.setString(3, treatment.getDescription());
            statement.setDouble(4, treatment.getCost());
            statement.setDate(5, Date.valueOf(treatment.getTreatmentDate()));
            statement.setInt(6, treatment.getVetId());

            // -------- POLYMORPHISM PART --------
            if (treatment instanceof Surgery surgery) {
                statement.setString(7, "SURGERY");
                statement.setString(8, surgery.getSurgeryType());
                statement.setInt(9, surgery.getDurationMinutes());
                statement.setNull(10, Types.VARCHAR);
                statement.setNull(11, Types.DATE);

            } else if (treatment instanceof Vaccination vaccination) {
                statement.setString(7, "VACCINATION");
                statement.setNull(8, Types.VARCHAR);
                statement.setNull(9, Types.INTEGER);
                statement.setString(10, vaccination.getVaccineName());
                statement.setDate(11, Date.valueOf(vaccination.getNextDueDate()));
            }

            int rows = statement.executeUpdate();
            if (rows > 0) {
                System.out.println(" Treatment inserted successfully!");
            }

            statement.close();

        } catch (SQLException e) {
            System.out.println(" Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public void getAllTreatments() {
        String sql = "SELECT * FROM treatment";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String type = rs.getString("treatment_type");

                Treatment treatment;

                if ("SURGERY".equals(type)) {
                    treatment = new Surgery(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("surgery_type"),
                            rs.getInt("duration_minutes")
                    );
                } else {
                    treatment = new Vaccination(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("vaccine_name"),
                            rs.getDate("next_due_date").toLocalDate()
                    );
                }

                System.out.println(treatment);
            }

            rs.close();
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public static boolean updateSurgery(Surgery surgery) {

        String sql = """
        UPDATE treatment
        SET name = ?, description = ?, cost = ?, date = ?, v_id = ?,
            surgery_type = ?, duration_minutes = ?
        WHERE t_id = ?
        """;

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, surgery.getTreatmentName());
            statement.setString(2, surgery.getDescription());
            statement.setDouble(3, surgery.getCost());
            statement.setDate(4, Date.valueOf(surgery.getTreatmentDate()));
            statement.setInt(5, surgery.getVetId());
            statement.setString(6, surgery.getSurgeryType());
            statement.setInt(7, surgery.getDurationMinutes());
            statement.setInt(8, surgery.getTreatmentId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            return rowsUpdated > 0;   // ✅ success / failure

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public static boolean updateVaccination(Vaccination vaccination) {

        String sql = """
        UPDATE treatment
        SET name = ?, description = ?, cost = ?, date = ?, v_id = ?,
            vaccine_name = ?, next_due_date = ?
        WHERE t_id = ?
        """;

        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, vaccination.getTreatmentName());
            statement.setString(2, vaccination.getDescription());
            statement.setDouble(3, vaccination.getCost());
            statement.setDate(4, Date.valueOf(vaccination.getTreatmentDate()));
            statement.setInt(5, vaccination.getVetId());
            statement.setString(6, vaccination.getVaccineName());
            statement.setDate(7, Date.valueOf(vaccination.getNextDueDate()));
            statement.setInt(8, vaccination.getTreatmentId());

            int rowsUpdated = statement.executeUpdate();
            statement.close();

            return rowsUpdated > 0;   // ✅ success / failure

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public static Treatment getTreatmentById(int id) {

        String sql = "SELECT * FROM treatment WHERE t_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String type = rs.getString("treatment_type");

                if ("SURGERY".equals(type)) {
                    return new Surgery(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("surgery_type"),
                            rs.getInt("duration_minutes")
                    );
                } else {
                    return new Vaccination(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("vaccine_name"),
                            rs.getDate("next_due_date").toLocalDate()
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public static boolean deleteTreatment(int treatmentId) {

        String sql = "DELETE FROM treatment WHERE t_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        if (connection == null) return false;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, treatmentId);

            int rowsDeleted = statement.executeUpdate();
            statement.close();

            if (rowsDeleted > 0) {
                System.out.println("✅ Treatment deleted (ID: " + treatmentId + ")");
                return true;
            } else {
                System.out.println("⚠️ No treatment found with ID: " + treatmentId);
            }

        } catch (SQLException e) {
            System.out.println("❌ Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public static List<Treatment> searchByName(String name) {

        List<Treatment> treatmentList = new ArrayList<>();

        // ILIKE = case-insensitive, % = wildcard
        String sql = """
        SELECT * FROM treatment
        WHERE name ILIKE ?
        ORDER BY name
        """;

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + name + "%"); // wildcards

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String type = rs.getString("treatment_type");
                Treatment treatment;

                if ("SURGERY".equals(type)) {
                    treatment = new Surgery(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("surgery_type"),
                            rs.getInt("duration_minutes")
                    );
                } else {
                    treatment = new Vaccination(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("vaccine_name"),
                            rs.getDate("next_due_date").toLocalDate()
                    );
                }

                treatmentList.add(treatment);
            }

            rs.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }

    public static List<Treatment> searchByCostRange(double minCost, double maxCost) {

        List<Treatment> treatmentList = new ArrayList<>();

        // BETWEEN is inclusive (min and max included)
        String sql = """
        SELECT * FROM treatment
        WHERE cost BETWEEN ? AND ?
        ORDER BY cost DESC
        """;

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minCost);
            statement.setDouble(2, maxCost);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String type = rs.getString("treatment_type");
                Treatment treatment;

                if ("SURGERY".equals(type)) {
                    treatment = new Surgery(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("surgery_type"),
                            rs.getInt("duration_minutes")
                    );
                } else {
                    treatment = new Vaccination(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("vaccine_name"),
                            rs.getDate("next_due_date").toLocalDate()
                    );
                }

                treatmentList.add(treatment);
            }

            rs.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }

    public static List<Treatment> searchByMinCost(double minCost) {

        List<Treatment> treatmentList = new ArrayList<>();

        String sql = """
        SELECT * FROM treatment
        WHERE cost >= ?
        ORDER BY cost DESC
        """;

        Connection connection = DatabaseConnection.getConnection();
        if (connection == null) return treatmentList;

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, minCost);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {

                String type = rs.getString("treatment_type");
                Treatment treatment;

                if ("SURGERY".equals(type)) {
                    treatment = new Surgery(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("surgery_type"),
                            rs.getInt("duration_minutes")
                    );
                } else {
                    treatment = new Vaccination(
                            rs.getInt("t_id"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getDouble("cost"),
                            rs.getDate("date").toLocalDate(),
                            rs.getInt("v_id"),
                            rs.getString("vaccine_name"),
                            rs.getDate("next_due_date").toLocalDate()
                    );
                }

                treatmentList.add(treatment);
            }

            rs.close();
            statement.close();

            System.out.println("✅ Found " + treatmentList.size() + " treatment(s)");

        } catch (SQLException e) {
            System.out.println("❌ Search failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return treatmentList;
    }
}
