package MES_Logic;

import java.util.ArrayList;
import java.util.List;

public class Deliver_Thread extends Thread {
    private List<Order> deliver_orders;
    private Logic logic;

    public Deliver_Thread() {
        deliver_orders = new ArrayList<>();
        logic = new Logic();
    }

    public void add_order(Order order) {
        deliver_orders.add(order);
    }

    public void remove_order(Order order) {
        deliver_orders.remove(order);
    }

    public void run() {
        while(true) {
            //System.out.println(Main.DC.getSecond());
            if(Main.DC.getSecond() < 30) {
                //System.out.println("CHECK");
                for(Order order : deliver_orders) {
                    try {
                        //System.out.println("deliver in second:" + Main.DC.getSecond());
                        logic.Deliver(order, Main.variableDisplay);
                        this.remove_order(order);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
