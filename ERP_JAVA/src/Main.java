import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Time_And_Day Calendar = new Time_And_Day(60);
    public static UDP_Server server = new UDP_Server();
    public static Transformations_Graph tg = new Transformations_Graph();
    public static SuppliersProfile s = new SuppliersProfile();
    public static Supplier_Order SOR;
    private static DB_Interaction db = new DB_Interaction();

    public static void main(String[] args) throws SQLException {
        db.EnsureGoodManners();
        SOR = new Supplier_Order();
        UInterfaceDisplay();
        Calendar.start();
        server.start();
        SOR.start();
    }

    public static void UInterfaceDisplay() {
        SwingUtilities.invokeLater(() -> {
            Tables t = new Tables();
            t.setVisible(true);
        });
    }
}
