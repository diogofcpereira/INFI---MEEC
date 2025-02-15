import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DB_Interaction {
    static final String URL = "jdbc:postgresql://db.fe.up.pt:5432/infind202416";
    static final String USERNAME = "infind202416";
    static final String PASSWORD = "inf2024";

    public void insertOrderToDB(Order orderToInsert) throws SQLException {
        String selectQuery = "SELECT * FROM \"INFI_db\".\"Orders\" WHERE \"number\"=?;";

        try (
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
        ) {
            selectStatement.setString(1, String.valueOf(orderToInsert.getNumber()));

            ResultSet rowsDetected = selectStatement.executeQuery();
            if (!rowsDetected.next()) {
                String insertQuery = "INSERT INTO \"INFI_db\".\"Orders\" (\"client\",\"entry_date\",\"priority\",\"number\",\"workpiece\",\"quantity\",\"due_date\",\"late_penalty\",\"early_penalty\",\"delivered\",\"production_cost\") VALUES (?,?,?,?,?,?,?,?,?,?,?);";

                try (
                    PreparedStatement preparedStatement = conn.prepareStatement(insertQuery);
                ) {

                    preparedStatement.setString(1, orderToInsert.getClient());
                    preparedStatement.setString(2, String.valueOf(orderToInsert.getEntry_date()));
                    preparedStatement.setString(3, String.valueOf(orderToInsert.getPriority()));
                    preparedStatement.setString(4, String.valueOf(orderToInsert.getNumber()));
                    preparedStatement.setString(5, orderToInsert.getWorkpiece());
                    preparedStatement.setString(6, String.valueOf(orderToInsert.getQuantity()));
                    preparedStatement.setString(7, String.valueOf(orderToInsert.getDue_date()));
                    preparedStatement.setString(8, String.valueOf(orderToInsert.getLate_pen()));
                    preparedStatement.setString(9, String.valueOf(orderToInsert.getEarly_pen()));
                    preparedStatement.setBoolean(10, false);
                    preparedStatement.setString(11, String.valueOf(0));

                    int rowsInserted = preparedStatement.executeUpdate();
                    if(rowsInserted > 0) {
                        //System.out.println("Order Inserted Successfully.");
                    }
                    else {
                        //System.out.println("Order Insertion Failed.");
                    }
                }
            }
            else {
                String updateQuery = "UPDATE \"INFI_db\".\"Orders\" SET \"priority\"=? WHERE \"number\"=?;";

                try (
                    PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
                ) {
                    updateStatement.setString(1, String.valueOf(orderToInsert.getPriority()));
                    updateStatement.setString(2, String.valueOf(orderToInsert.getNumber()));

                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        //System.out.println("Order Updated Successfully.");
                    }
                    else {
                        //System.out.println("Order Update Failed.");
                    }
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertTransformationToDB(ProductionTransformation transformationToInsert) {
        String insertQuery = "INSERT INTO \"INFI_db\".\"graph\" (\"StartingPiece\",\"ProcessedPiece\",\"Tool\",\"ProcessingTime\") " +
                "SELECT ?,?,?,?" +
                "WHERE NOT EXISTS (" +
                "SELECT 1" +
                "FROM \"INFI_db\".\"graph\"" +
                "WHERE \"StartingPiece\" = ? AND \"ProcessedPiece\" = ? AND \"Tool\" = ? AND \"ProcessingTime\" = ?" +
                ");";
        try (
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            preparedStatement.setString(1, transformationToInsert.getFrom().getName());
            preparedStatement.setString(2, transformationToInsert.getTo().getName());
            preparedStatement.setString(3, transformationToInsert.getToolToUse());
            preparedStatement.setString(4, String.valueOf(transformationToInsert.getTimeToProduce()));
            preparedStatement.setString(5, transformationToInsert.getFrom().getName());
            preparedStatement.setString(6, transformationToInsert.getTo().getName());
            preparedStatement.setString(7, transformationToInsert.getToolToUse());
            preparedStatement.setString(8, String.valueOf(transformationToInsert.getTimeToProduce()));

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) {
                //System.out.println("Transformation Inserted Successfully.");
            }
            else {
                //System.out.println("Transformation To Insert Already Exists.");
            }
        }
        catch (SQLException e) {
            System.out.println("Transformation Insertion Failed.");
            e.printStackTrace();
        }
    }

    public void insertSuppliersToDB(Supplier supplier) {
        String aux1 = "INSERT INTO \"INFI_db\".\"supplier\" (\"Supplier\",\"Piece\",\"MinimumOrder\",\"PricePerPiece\",\"DeliveryTime\") " +
                "SELECT ?,?,?,?,?" +
                "WHERE NOT EXISTS (" +
                "SELECT 1" +
                "FROM \"INFI_db\".\"supplier\"" +
                "WHERE \"Supplier\" = ? AND \"Piece\" = ? AND \"MinimumOrder\" = ? AND \"PricePerPiece\" = ? AND \"DeliveryTime\" = ?" +
                ");";

        String aux2 = "INSERT INTO \"INFI_db\".\"supplier\" (\"Supplier\",\"Piece\",\"MinimumOrder\",\"PricePerPiece\",\"DeliveryTime\") " +
                "SELECT ?,?,?,?,?" +
                "WHERE NOT EXISTS (" +
                "SELECT 1" +
                "FROM \"INFI_db\".\"supplier\"" +
                "WHERE \"Supplier\" = ? AND \"Piece\" = ? AND \"MinimumOrder\" = ? AND \"PricePerPiece\" = ? AND \"DeliveryTime\" = ?" +
                ");";

        String insertQuery = aux1 + aux2;

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
        ) {
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getPiece()[0]);
            preparedStatement.setString(3, String.valueOf(supplier.getMinimum_order()[0]));
            preparedStatement.setString(4, String.valueOf(supplier.getPrice_per_piece()[0]));
            preparedStatement.setString(5, String.valueOf(supplier.getDays_to_deliver()[0]));

            preparedStatement.setString(6, supplier.getName());
            preparedStatement.setString(7, supplier.getPiece()[0]);
            preparedStatement.setString(8, String.valueOf(supplier.getMinimum_order()[0]));
            preparedStatement.setString(9, String.valueOf(supplier.getPrice_per_piece()[0]));
            preparedStatement.setString(10, String.valueOf(supplier.getDays_to_deliver()[0]));

            preparedStatement.setString(11, supplier.getName());
            preparedStatement.setString(12, supplier.getPiece()[1]);
            preparedStatement.setString(13, String.valueOf(supplier.getMinimum_order()[1]));
            preparedStatement.setString(14, String.valueOf(supplier.getPrice_per_piece()[1]));
            preparedStatement.setString(15, String.valueOf(supplier.getDays_to_deliver()[1]));

            preparedStatement.setString(16, supplier.getName());
            preparedStatement.setString(17, supplier.getPiece()[1]);
            preparedStatement.setString(18, String.valueOf(supplier.getMinimum_order()[1]));
            preparedStatement.setString(19, String.valueOf(supplier.getPrice_per_piece()[1]));
            preparedStatement.setString(20, String.valueOf(supplier.getDays_to_deliver()[1]));

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) {
                //System.out.println("Supplier Inserted Successfully.");
            }
            else {
                //System.out.println("Supplier To Insert Already Exists.");
            }
        }
        catch (SQLException e) {
            System.out.println("Supplier Insertion Failed.");
            e.printStackTrace();
        }
    }

    public void updateDay(long currentDay) {
        String updateQuery = "UPDATE \"INFI_db\".\"Calendar\" SET \"Day\"=?;";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, String.valueOf(currentDay));

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) {
                //System.out.println("Day Updated Successfully.");
            }
            else {
                //System.out.println("Day Update Failed.");
            }
        }
        catch (SQLException e) {
            //System.out.println("Day Update Failed.");
            e.printStackTrace();
        }
    }

    public void updateSecond(int currentSecond) {
        String updateQuery = "UPDATE \"INFI_db\".\"Calendar\" SET \"Second\"=?;";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            preparedStatement.setString(1, String.valueOf(currentSecond));

            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) {
                //System.out.println("Second Updated Successfully.");
            }
            else {
                //System.out.println("Second Update Failed.");
            }
        }
        catch (SQLException e) {
            //System.out.println("Second Update Failed.");
            e.printStackTrace();
        }
    }

    public List<String[]> getOrders() {
        String getQuery = "SELECT * FROM \"INFI_db\".\"Orders\";";

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

    public List<String[]> getCertain_Orders(boolean indicator) {
        String getQuery;
        List<String[]> orders = new ArrayList<>();

        if(indicator) {
            getQuery = "SELECT \"number\",\"client\",\"production_cost\" FROM \"INFI_db\".\"Orders\" WHERE \"delivered\"=TRUE;";

            try (
                    Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {

                while(resultSet.next()) {
                    String[] aux = new String[3];
                    aux[0] = resultSet.getString("number").trim();
                    aux[1] = resultSet.getString("client").trim();
                    aux[2] = resultSet.getString("production_cost").trim();

                    orders.add(aux);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            getQuery = "SELECT \"number\",\"client\",\"entry_date\",\"priority\",\"workpiece\",\"quantity\",\"due_date\",\"late_penalty\",\"early_penalty\" FROM \"INFI_db\".\"Orders\" WHERE \"delivered\"=FALSE;";

            try (
                    Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                    ResultSet resultSet = preparedStatement.executeQuery();
            ) {

                while(resultSet.next()) {
                    String[] aux = new String[9];
                    aux[0] = resultSet.getString("number");
                    aux[1] = resultSet.getString("client");
                    aux[2] = resultSet.getString("entry_date");
                    aux[3] = resultSet.getString("priority");
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

        }
        return orders;
    }

    public List<String[]> getPT() {
        String getQuery = "SELECT * FROM \"INFI_db\".\"graph\";";

        List<String[]> pt = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                String[] aux = new String[4];
                aux[0] = resultSet.getString("StartingPiece");
                aux[1] = resultSet.getString("ProcessedPiece");
                aux[2] = resultSet.getString("Tool");
                aux[3] = resultSet.getString("ProcessingTime");

                pt.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pt;
    }

    public List<String[]> getSuppliers() {
        String getQuery = "SELECT * FROM \"INFI_db\".\"supplier\";";

        List<String[]> suppliers = new ArrayList<>();

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while(resultSet.next()) {
                String[] aux = new String[5];
                aux[0] = resultSet.getString("Supplier");
                aux[1] = resultSet.getString("Piece");
                aux[2] = resultSet.getString("MinimumOrder");
                aux[3] = resultSet.getString("PricePerPiece");
                aux[4] = resultSet.getString("DeliveryTime");

                suppliers.add(aux);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return suppliers;
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

    public void EnsureGoodManners() {
        String updateQuery = "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '20' WHERE \"Piece\" = 'P1';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P2';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P3';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P4';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P5';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P6';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P7';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P8';" +
                "UPDATE \"INFI_db\".\"Warehouse1\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P9';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P1';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P2';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P3';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P4';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P5';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P6';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P7';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '0' WHERE \"Piece\" = 'P8';" +
                "UPDATE \"INFI_db\".\"Warehouse2\" SET \"Quantity\" = '10' WHERE \"Piece\" = 'P9';";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        ) {
            int rowsInserted = preparedStatement.executeUpdate();
            if(rowsInserted > 0) {
                //System.out.println("Good Manners Applied.");
            }
            else {
                //System.out.println("Good Manners Not Applied.");
            }
        }
        catch (SQLException e) {
            //System.out.println("Good Manners Not Applied.");
            e.printStackTrace();
        }
    }

    public boolean getDelivered(int number) {
        boolean delivered = false;

        String getQuery = "SELECT \"delivered\" FROM \"INFI_db\".\"Orders\" WHERE \"number\" = ?;";

        try (
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement preparedStatement = connection.prepareStatement(getQuery);
        ) {
            preparedStatement.setString(1, String.valueOf(number));
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                delivered = resultSet.getBoolean("delivered");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return delivered;
    }
}
