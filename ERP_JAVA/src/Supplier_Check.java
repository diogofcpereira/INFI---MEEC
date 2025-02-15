import java.util.Comparator;
import java.util.List;

public class Supplier_Check implements Runnable {

    private DB_Interaction db;

    private int P1_count;
    private int P2_count;
    private int P3_count;
    private int P4_count;
    private int P5_count;
    private int P6_count;
    private int P7_count;
    private int P8_count;
    private int P9_count;

    private volatile boolean running;

    public Supplier_Check() {
        db = new DB_Interaction();
        running = true;

    }

    @Override
    public void run() {
        while (running) {
            List<String[]> list_w1 = db.getWarehouse1();
            List<String[]> list_w2 = db.getWarehouse2();

            list_w1.sort(Comparator.comparing(array -> array[0].trim()));
            list_w2.sort(Comparator.comparing(array -> array[0].trim()));

            P1_count = Integer.parseInt(list_w1.get(0)[1].trim()); //+ Integer.parseInt(list_w2.get(0)[1].trim());
            P2_count = Integer.parseInt(list_w1.get(1)[1].trim()); //+ Integer.parseInt(list_w2.get(1)[1].trim());
            P3_count = Integer.parseInt(list_w1.get(2)[1].trim()) + Integer.parseInt(list_w2.get(2)[1].trim());
            P4_count = Integer.parseInt(list_w1.get(3)[1].trim()) + Integer.parseInt(list_w2.get(3)[1].trim());
            P5_count = Integer.parseInt(list_w1.get(4)[1].trim()) + Integer.parseInt(list_w2.get(4)[1].trim());
            P6_count = Integer.parseInt(list_w1.get(5)[1].trim()) + Integer.parseInt(list_w2.get(5)[1].trim());
            P7_count = Integer.parseInt(list_w1.get(6)[1].trim()) + Integer.parseInt(list_w2.get(6)[1].trim());
            P8_count = Integer.parseInt(list_w1.get(7)[1].trim()) + Integer.parseInt(list_w2.get(7)[1].trim());
            P9_count = /*Integer.parseInt(list_w1.get(8)[1].trim()) +*/ Integer.parseInt(list_w2.get(8)[1].trim());
        }
    }

    public void stop() {
        running = false;
    }

    public int getP1_count() {
        return P1_count;
    }

    public int getP2_count() {
        return P2_count;
    }

    public int getP3_count() {
        return P3_count;
    }

    public int getP4_count() {
        return P4_count;
    }

    public int getP5_count() {
        return P5_count;
    }

    public int getP6_count() {
        return P6_count;
    }

    public int getP7_count() {
        return P7_count;
    }

    public int getP8_count() {
        return P8_count;
    }

    public int getP9_count() {
        return P9_count;
    }
}
