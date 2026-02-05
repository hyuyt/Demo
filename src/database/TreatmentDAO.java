package database;

import model.Surgery;
import model.Treatment;
import model.Vaccination;

import java.sql.*
import java.util.ArrayList;
import java.util.List;

public class TreatmentDAO {

    public void insertTreatment(Treatment treatment) {
        String sql = """
            INSERT INTO treatment
            (t_id, name, description, cost, date, v_id,
             type, surgery_type, duration_minutes,
             vaccine_name, next_due_date)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, treatment.getTreatmentId());
            statement.setString(2, treatment.getTreatmentName());
            statement.setString(3, treatment.getDescription());
            statement.setDouble(4, treatment.getCost());
            statement.setDate(5, Date.valueOf(treatment.getTreatmentDate()));
            statement.setInt(6, treatment.getVetId());

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
            } else {
                throw new IllegalArgumentException("Unknown treatment type");
            }

            statement.executeUpdate();
            System.out.println("✅ Treatment inserted successfully!");

        } catch (SQLException e) {
            System.out.println("❌ Insert failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }
    }

    public List<Treatment> getAllTreatments() {
        List<Treatment> list = new ArrayList<>();
        String sql = "SELECT * FROM treatment ORDER BY t_id";
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                list.add(extractTreatment(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Select failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return list;
    }

    public static Treatment getTreatmentById(int id) {
        String sql = "SELECT * FROM treatment WHERE t_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) return extractTreatment(rs);
            }

        } catch (SQLException e) {
            System.out.println("❌ Select by ID failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return null;
    }

    public static boolean updateSurgery(Surgery surgery) {
        String sql = """
            UPDATE treatment
            SET name = ?, description = ?, cost = ?, date = ?, v_id = ?,
                type = 'SURGERY',
                surgery_type = ?, duration_minutes = ?,
                vaccine_name = NULL, next_due_date = NULL
            WHERE t_id = ?
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, surgery.getTreatmentName());
            statement.setString(2, surgery.getDescription());
            statement.setDouble(3, surgery.getCost());
            statement.setDate(4, Date.valueOf(surgery.getTreatmentDate()));
            statement.setInt(5, surgery.getVetId());
            statement.setString(6, surgery.getSurgeryType());
            statement.setInt(7, surgery.getDurationMinutes());
            statement.setInt(8, surgery.getTreatmentId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Update surgery failed!");
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
                type = 'VACCINATION',
                vaccine_name = ?, next_due_date = ?,
                surgery_type = NULL, duration_minutes = NULL
            WHERE t_id = ?
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, vaccination.getTreatmentName());
            statement.setString(2, vaccination.getDescription());
            statement.setDouble(3, vaccination.getCost());
            statement.setDate(4, Date.valueOf(vaccination.getTreatmentDate()));
            statement.setInt(5, vaccination.getVetId());
            statement.setString(6, vaccination.getVaccineName());
            statement.setDate(7, Date.valueOf(vaccination.getNextDueDate()));
            statement.setInt(8, vaccination.getTreatmentId());

            return statement.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("❌ Update vaccination failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public static boolean deleteTreatment(int id) {
        String sql = "DELETE FROM treatment WHERE t_id = ?";
        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("❌ Delete failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return false;
    }

    public List<Treatment> searchByType(String type) {
        List<Treatment> list = new ArrayList<>();
        String sql = """
            SELECT * FROM treatment
            WHERE type = ?
            ORDER BY t_id
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, type);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) list.add(extractTreatment(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Search by type failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return list;
    }

    public static List<Treatment> searchByName(String name) {
        List<Treatment> list = new ArrayList<>();
        String sql = """
            SELECT * FROM treatment
            WHERE name ILIKE ?
            ORDER BY name
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "%" + name + "%");

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) list.add(extractTreatment(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Search by name failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return list;
    }

    public static List<Treatment> searchByCostRange(double min, double max) {
        List<Treatment> list = new ArrayList<>();
        String sql = """
            SELECT * FROM treatment
            WHERE cost BETWEEN ? AND ?
            ORDER BY cost DESC
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, min);
            statement.setDouble(2, max);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) list.add(extractTreatment(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Search by cost range failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return list;
    }

    public static List<Treatment> searchByMinCost(double min) {
        List<Treatment> list = new ArrayList<>();
        String sql = """
            SELECT * FROM treatment
            WHERE cost >= ?
            ORDER BY cost DESC
            """;

        Connection connection = DatabaseConnection.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, min);

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) list.add(extractTreatment(rs));
            }

        } catch (SQLException e) {
            System.out.println("❌ Search by min cost failed!");
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection(connection);
        }

        return list;
    }

    private static Treatment extractTreatment(ResultSet rs) throws SQLException {
        String type = rs.getString("type");

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
        }

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
