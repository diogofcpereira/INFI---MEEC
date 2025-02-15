/*import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;*/

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tables extends JFrame {
    private JTabbedPane tabbedPane;

    private JPanel titlePane;
    private JPanel warehouse1Pane;
    private JPanel warehouse2Pane;

    private JTable orders_table;
    private JTable completed_orders_table;
    private JTable pt_table;
    private JTable suppliers_table;
    private JTable suppliers_statistics_table;
    private JTable Warehouse1;
    private JTable Warehouse2;

    private JLabel titleLabel;
    private JLabel dateLabel;
    private JLabel w1Label;
    private JLabel w2Label;
    private JLabel w1PercentageLabel;
    private JLabel w2PercentageLabel;

    private OrdersTableModel orderstableModel;
    private CompletedOrdersTableModel completedorderstableModel;
    private PTTableModel pttableModel;
    private SuppliersTableModel supplierstableModel;
    private SuppliersStatisticsTableModel supplierstableStatisticsModel;
    private WarehouseTableModel w1TableModel;
    private WarehouseTableModel w2TableModel;

    private DB_Interaction db;

    public Tables() {
        setTitle("Orders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1080, 720);
        setResizable(false);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);

        db = new DB_Interaction();

        List<String[]> orders = new ArrayList<>();
        orderstableModel = new OrdersTableModel(orders);

        List<String[]> completedOrders = new ArrayList<>();
        completedorderstableModel = new CompletedOrdersTableModel(completedOrders);

        List<String[]> pt = new ArrayList<>();
        pttableModel = new PTTableModel(pt);

        List<String[]> suppliers = new ArrayList<>();
        supplierstableModel = new SuppliersTableModel(suppliers);

        List<String[]> supplierStatistics = new ArrayList<>();
        supplierstableStatisticsModel = new SuppliersStatisticsTableModel(supplierStatistics);

        List<String[]> w1 = new ArrayList<>();
        w1TableModel = new WarehouseTableModel(w1);

        List<String[]> w2 = new ArrayList<>();
        w2TableModel = new WarehouseTableModel(w2);

        orders_table = new JTable(orderstableModel);
        completed_orders_table = new JTable(completedorderstableModel);
        pt_table = new JTable(pttableModel);
        suppliers_table = new JTable(supplierstableModel);
        suppliers_statistics_table = new JTable(supplierstableStatisticsModel);
        Warehouse1 = new JTable(w1TableModel);
        Warehouse2 = new JTable(w2TableModel);

        titleLabel = new JLabel("Shop Floor Simulator");
        dateLabel = new JLabel();
        w1Label = new JLabel("Warehouse 1 Usage Percentage:");
        w2Label = new JLabel("Warehouse 2 Usage Percentage:");
        w1PercentageLabel = new JLabel();
        w2PercentageLabel = new JLabel();

        JScrollPane scrollPane_orders = new JScrollPane(orders_table);
        JScrollPane scrollPane_completed_orders = new JScrollPane(completed_orders_table);
        JScrollPane scrollPane_pt = new JScrollPane(pt_table);
        JScrollPane scrollPane_suppliers = new JScrollPane(suppliers_table);
        JScrollPane scrollPane_suppliers_statistics = new JScrollPane(suppliers_statistics_table);
        JScrollPane scrollPane_w1 = new JScrollPane(Warehouse1);
        JScrollPane scrollPane_w2 = new JScrollPane(Warehouse2);

        tabbedPane = new JTabbedPane();

        titlePane = new JPanel();
        warehouse1Pane = new JPanel();
        warehouse2Pane = new JPanel();

        titlePane.setLayout(new BorderLayout());

        tabbedPane.addTab("Orders To Process", scrollPane_orders);
        tabbedPane.addTab("Completed Orders", scrollPane_completed_orders);
        tabbedPane.addTab("Product Transformations", scrollPane_pt);
        tabbedPane.addTab("Suppliers", scrollPane_suppliers);
        tabbedPane.addTab("Supplier Statistics", scrollPane_suppliers_statistics);
        tabbedPane.addTab("Warehouse 1 Availability", warehouse1Pane);
        tabbedPane.addTab("Warehouse 2 Availability", warehouse2Pane);

        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        titlePane.add(titleLabel, BorderLayout.WEST);

        dateLabel.setFont(new Font("Arial", Font.BOLD, 15));
        dateLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        dateLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        titlePane.add(dateLabel, BorderLayout.EAST);

        w1Label.setFont(new Font("Arial", Font.BOLD, 30));
        w1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        w1PercentageLabel.setFont(new Font("Arial", Font.BOLD, 50));
        w1PercentageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        w2Label.setFont(new Font("Arial", Font.BOLD, 30));
        w2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        w2PercentageLabel.setFont(new Font("Arial", Font.BOLD, 50));
        w2PercentageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        warehouse1Pane.add(w1Label);
        warehouse1Pane.add(w1PercentageLabel);
        warehouse2Pane.add(w2Label);
        warehouse2Pane.add(w2PercentageLabel);

        getContentPane().add(titlePane, BorderLayout.NORTH);

        GroupLayout layout1 = new GroupLayout(warehouse1Pane);
        GroupLayout layout2 = new GroupLayout(warehouse2Pane);

        warehouse1Pane.setLayout(layout1);
        warehouse2Pane.setLayout(layout2);

        layout1.setHorizontalGroup(layout1.createSequentialGroup().addComponent(scrollPane_w1).addGap(50).addGroup(layout1.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(w1Label).addComponent(w1PercentageLabel)));
        layout1.setVerticalGroup(layout1.createParallelGroup().addComponent(scrollPane_w1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout1.createSequentialGroup().addComponent(w1Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 20).addComponent(w1PercentageLabel)));

        layout2.setHorizontalGroup(layout2.createSequentialGroup().addComponent(scrollPane_w2).addGap(50).addGroup(layout2.createParallelGroup(GroupLayout.Alignment.TRAILING).addComponent(w2Label).addComponent(w2PercentageLabel)));
        layout2.setVerticalGroup(layout2.createParallelGroup().addComponent(scrollPane_w2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addGroup(layout2.createSequentialGroup().addComponent(w2Label).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, 20).addComponent(w2PercentageLabel)));

        Thread dataUpdateThread = new Thread(new DataUpdateTask());
        dataUpdateThread.start();
    }

    private class DataUpdateTask implements Runnable {
        @Override
        public void run() {
            // Simulate dynamic data updates
            while (true) {
                List<String[]> OrdersData = fetchOrders();
                OrdersData.sort(Comparator.comparing(array -> Integer.parseInt(array[2].trim())));
                List<String[]> CompletedOrdersData = fetchCompletedOrders();
                CompletedOrdersData.sort(Comparator.comparing(array -> Integer.parseInt(array[0].trim())));
                List<String[]> PTData = fetchPT();
                List<String[]> supplierData = fetchSuppliers();
                List<String[]> supplierStatisticsData = fetchSupplierStatistics();
                String date = fetchDate();
                List<String[]> w1 = fetchWarehouse1();
                w1.sort(Comparator.comparing(array -> array[0].trim()));
                List<String[]> w2 = fetchWarehouse2();
                w2.sort(Comparator.comparing(array -> array[0].trim()));
                String w1Percentage = obtainPercentageUsage(w1);
                String w2Percentage = obtainPercentageUsage(w2);
                orderstableModel.data = OrdersData;
                completedorderstableModel.data = CompletedOrdersData;
                pttableModel.data = PTData;
                supplierstableModel.data = supplierData;
                supplierstableStatisticsModel.data = supplierStatisticsData;
                w1TableModel.data = w1;
                w2TableModel.data = w2;
                orderstableModel.fireTableDataChanged();
                completedorderstableModel.fireTableDataChanged();
                pttableModel.fireTableDataChanged();
                supplierstableModel.fireTableDataChanged();
                supplierstableStatisticsModel.fireTableDataChanged();
                dateLabel.setText(date);
                w1TableModel.fireTableDataChanged();
                w2TableModel.fireTableDataChanged();
                w1PercentageLabel.setText(w1Percentage);
                w2PercentageLabel.setText(w2Percentage);
                /*try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }

        private List<String[]> fetchOrders() {
            List<String[]> newData = new ArrayList<>();

            newData = db.getCertain_Orders(false);
            return newData;
        }

        private List<String[]> fetchCompletedOrders() {
            List<String[]> newData = new ArrayList<>();

            newData = db.getCertain_Orders(true);
            return newData;
        }

        private List<String[]> fetchPT() {
            List<String[]> newData = new ArrayList<>();
            newData = db.getPT();
            return newData;
        }

        private List<String[]> fetchSuppliers() {
            List<String[]> newData = new ArrayList<>();
            newData = db.getSuppliers();
            return newData;
        }

        private List<String[]> fetchSupplierStatistics() {
            List<String[]> newData = new ArrayList<>();
            String[] A = new String[3];
            String[] B = new String[3];
            String[] C = new String[3];

            A[0] = "SupplierA";
            A[1] = String.valueOf(Main.SOR.getP1_SupplierA());
            A[2] = String.valueOf(Main.SOR.getP2_SupplierA());
            newData.add(A);

            B[0] = "SupplierB";
            B[1] = String.valueOf(Main.SOR.getP1_SupplierB());
            B[2] = String.valueOf(Main.SOR.getP2_SupplierB());
            newData.add(B);

            C[0] = "SupplierC";
            C[1] = String.valueOf(Main.SOR.getP1_SupplierC());
            C[2] = String.valueOf(Main.SOR.getP2_SupplierC());
            newData.add(C);

            return newData;
        }

        private String fetchDate() {
            return "Day " + db.getDay() + " Second " + db.getSecond();
        }

        private List<String[]> fetchWarehouse1() {
            List<String[]> newData = new ArrayList<>();
            newData = db.getWarehouse1();
            return newData;
        }

        private List<String[]> fetchWarehouse2() {
            List<String[]> newData = new ArrayList<>();
            newData = db.getWarehouse2();
            return newData;
        }

        private String obtainPercentageUsage(List<String[]> data) {
            int sum = 0;

            for (String[] row : data) {
                sum = sum + Integer.parseInt(row[1].trim());
            }

            int percentage = Math.round(((float) sum / 32)*100);

            return percentage + " %";
        }
    }

    class OrdersTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"NumberId", "Client", "Entry date", "Priority", "WorkPiece", "Quantity", "Due Date", "Late Penalty", "Early Penalty"};

        public OrdersTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    class CompletedOrdersTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"Number", "Client", "Production Cost"};

        public CompletedOrdersTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    class PTTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"Starting Piece", "Processed Piece", "Tool", "Processing Time"};

        public PTTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    class SuppliersTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"Supplier", "Piece", "Minimum Order", "Price per Piece", "Delivery Time"};

        public SuppliersTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    class SuppliersStatisticsTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"Supplier", "P1", "P2"};

        public SuppliersStatisticsTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    class WarehouseTableModel extends AbstractTableModel {
        private List<String[]> data;
        private String[] columnNames = {"Piece", "Quantity"};

        public WarehouseTableModel(List<String[]> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int col) {
            return columnNames[col];
        }

        @Override
        public Object getValueAt(int row, int col) {
            return data.get(row)[col];
        }
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Tables t = new Tables();
            t.setVisible(true);
        });
    }*/
}
