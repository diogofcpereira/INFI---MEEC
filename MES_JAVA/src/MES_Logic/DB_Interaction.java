package MES_Logic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB_Interaction {
    static final String URL = "jdbc:postgresql://db.fe.up.pt:5432/infind202416";
    static final String USERNAME = "infind202416";
    static final String PASSWORD = "inf2024";

    public void update_values(Order orderToUpdate) throws SQLException {
        String updateQuery = "UPDATE  \"INFI_db\".\"Orders\" SET \"delivered\"=?, \"production_cost\"=? WHERE \"number\"=?;";

        try (
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        ) {
            updateStatement.setBoolean(1, orderToUpdate.getAll_done());
            updateStatement.setString(2, String.valueOf(orderToUpdate.getTotalOrderCost()));
            updateStatement.setString(3, String.valueOf(orderToUpdate.getNumber()));

            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Order Updated Successfully.");
            }
            else {
                System.out.println("Order Update Failed.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertW1ToDB (Warehouse warehouseToInsert) throws SQLException {
        String updateQuery = "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P1';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P2';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P3';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P4';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P5';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P6';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P7';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P8';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P9';";

        try(
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        ) {
            updateStatement.setString(1, String.valueOf(warehouseToInsert.getWarehouse_counter()[0]));
            updateStatement.setString(2, String.valueOf(warehouseToInsert.getWarehouse_counter()[1]));
            updateStatement.setString(3, String.valueOf(warehouseToInsert.getWarehouse_counter()[2]));
            updateStatement.setString(4, String.valueOf(warehouseToInsert.getWarehouse_counter()[3]));
            updateStatement.setString(5, String.valueOf(warehouseToInsert.getWarehouse_counter()[4]));
            updateStatement.setString(6, String.valueOf(warehouseToInsert.getWarehouse_counter()[5]));
            updateStatement.setString(7, String.valueOf(warehouseToInsert.getWarehouse_counter()[6]));
            updateStatement.setString(8, String.valueOf(warehouseToInsert.getWarehouse_counter()[7]));
            updateStatement.setString(9, String.valueOf(warehouseToInsert.getWarehouse_counter()[8]));

            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("Warehouse Inserted Successfully.");
            }
            else {
                System.out.println("Warehouse Insertion Failed.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertW2ToDB (Warehouse warehouseToInsert) throws SQLException {
        String updateQuery = "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P1';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P2';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P3';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P4';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P5';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P6';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P7';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P8';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = ? WHERE \"Piece\" = 'P9';";

        try(
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
        ) {
            updateStatement.setString(1, String.valueOf(warehouseToInsert.getWarehouse_counter()[0]));
            updateStatement.setString(2, String.valueOf(warehouseToInsert.getWarehouse_counter()[1]));
            updateStatement.setString(3, String.valueOf(warehouseToInsert.getWarehouse_counter()[2]));
            updateStatement.setString(4, String.valueOf(warehouseToInsert.getWarehouse_counter()[3]));
            updateStatement.setString(5, String.valueOf(warehouseToInsert.getWarehouse_counter()[4]));
            updateStatement.setString(6, String.valueOf(warehouseToInsert.getWarehouse_counter()[5]));
            updateStatement.setString(7, String.valueOf(warehouseToInsert.getWarehouse_counter()[6]));
            updateStatement.setString(8, String.valueOf(warehouseToInsert.getWarehouse_counter()[7]));
            updateStatement.setString(9, String.valueOf(warehouseToInsert.getWarehouse_counter()[8]));


            int rowsUpdated = updateStatement.executeUpdate();
            if (rowsUpdated > 0) {
                //System.out.println("Warehouse Inserted Successfully.");
            }
            else {
                System.out.println("Warehouse Insertion Failed.");
            }
        }
    }

    public Order getMostImportantOrder() {
        String getMinPriorityQuery = "SELECT MIN(CAST(\"priority\" AS INTEGER)) AS min_priority FROM \"INFI_db\".\"Orders\" WHERE \"delivered\"=FALSE;";
        String getOrderQuery = "SELECT * FROM \"INFI_db\".\"Orders\" WHERE \"priority\"=?::text AND \"delivered\"=FALSE LIMIT 1;";

        try (
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement minPriorityStatement = conn.prepareStatement(getMinPriorityQuery);
                ResultSet minPriorityResultSet = minPriorityStatement.executeQuery();
        ) {
            if (minPriorityResultSet.next()) {
                int minPriority = minPriorityResultSet.getInt("min_priority");

                try (
                        PreparedStatement getOrderStatement = conn.prepareStatement(getOrderQuery);
                ) {
                    getOrderStatement.setInt(1, minPriority);
                    ResultSet orderResultSet = getOrderStatement.executeQuery();

                    if (orderResultSet.next()) {
                        String[] aux = new String[9];
                        aux[0] = orderResultSet.getString("client");
                        aux[1] = orderResultSet.getString("entry_date");
                        aux[2] = orderResultSet.getString("priority");
                        aux[3] = orderResultSet.getString("number");
                        aux[4] = orderResultSet.getString("workpiece");
                        aux[5] = orderResultSet.getString("quantity");
                        aux[6] = orderResultSet.getString("due_date");
                        aux[7] = orderResultSet.getString("late_penalty");
                        aux[8] = orderResultSet.getString("early_penalty");

                        return new Order(
                                aux[0].trim(),
                                Integer.parseInt(aux[3].trim()),
                                aux[4].trim(),
                                Integer.parseInt(aux[5].trim()),
                                Integer.parseInt(aux[6].trim()),
                                Integer.parseInt(aux[1].trim()),
                                Integer.parseInt(aux[8].trim()),
                                Integer.parseInt(aux[7].trim())
                        );
                    }
                }
            }

            return null; // No undelivered orders found
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String[]> getOrders() {
        String getQuery = "SELECT * FROM \"INFI_db\".\"Orders\" WHERE \"delivered\"=FALSE;";

        List<String[]> orders = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
                while(resultSet.next()) {
                    String[] aux = new String[9];
                    aux[0] = resultSet.getString("client");
                    aux[1] = resultSet.getString("entry_date");
                    aux[2] = resultSet.getString("priority");
                    aux[3] = resultSet.getString("number");
                    aux[4] = resultSet.getString("workpiece");
                    aux[5] = resultSet.getString("quantity");
                    aux[6] = resultSet.getString("due_date");
                    aux[7] = resultSet.getString("late_penalty");
                    aux[8] = resultSet.getString("early_penalty");

                    orders.add(aux);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public long getDay() {
        String getQuery = "SELECT \"Day\" FROM \"INFI_db\".\"Calendar\";";

        long day = 0;

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                day = Long.parseLong(resultSet.getString("Day").trim());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return day;
    }

    public int getSecond() {
        String getQuery = "SELECT \"Second\" FROM \"INFI_db\".\"Calendar\";";

        int second = 0;

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            if (resultSet.next()) {
                second = Integer.parseInt(resultSet.getString("Second").trim());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return second;
    }

    public List<String[]> getWarehouse1() {
        List<String[]> warehouse1 = new ArrayList<>();

        String getQuery = "SELECT * FROM \"INFI_db\".\"Warehouse1\";";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String[] aux = new String[2];
                aux[0] = resultSet.getString("Piece");
                aux[1] = resultSet.getString("Quantity");

                warehouse1.add(aux);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouse1;
    }

    public List<String[]> getWarehouse2() {
        List<String[]> warehouse2 = new ArrayList<>();

        String getQuery = "SELECT * FROM \"INFI_db\".\"Warehouse2\";";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                String[] aux = new String[2];
                aux[0] = resultSet.getString("Piece");
                aux[1] = resultSet.getString("Quantity");

                warehouse2.add(aux);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return warehouse2;
    }
}
