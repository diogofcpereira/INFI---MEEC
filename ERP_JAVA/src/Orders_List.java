import java.util.*;

class Pair {
    int numberID;
    float index;
    int priority;

    Pair(int numberID, float index) {
        this.numberID = numberID;
        this.index = index;
    }
}

public class Orders_List {
    //public but maybe can be private
    public List<Order> orders;

    public Orders_List() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public void removeOrder(Order order) {
        this.orders.remove(order);
    }

    public List<Order> getAllOrders() {
        return this.orders;
    }

    public List<Order> getOrdersByClient(String client) {
        List<Order> client_orders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getClient().equals(client)) {
                client_orders.add(order);
            }
        }
        return client_orders;
    }

    public Order getOrderByNumber(int number) {
        for (Order order : this.orders) {
            if (order.getNumber() == number) {
                return order;
            }
        }
        return null;
    }

    public List<Order> getOrdersByDueDate(int due_date) {
        List<Order> due_date_orders = new ArrayList<>();
        for (Order order : this.orders) {
            if(order.getDue_date() == due_date) {
                due_date_orders.add(order);
            }
        }
        return due_date_orders;
    }

    public List<Order> getOrdersByWorkpieces(String workpiece) {
        List<Order> workpiece_orders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getWorkpiece().equals(workpiece)) {
                workpiece_orders.add(order);
            }
        }
        return workpiece_orders;
    }

    public int getTotalOrders() {
        return this.orders.size();
    }

    public List<String[]> TransformToArray() {
        List<String[]> Array = new ArrayList<>();
        for (Order order : this.orders) {
            String[] aux = new String[9];
            aux[0] = order.getClient();
            aux[3] = String.valueOf(order.getNumber());
            aux[4] = order.getWorkpiece();
            aux[5] = String.valueOf(order.getQuantity());
            aux[6] = String.valueOf(order.getDue_date());
            aux[7] = String.valueOf(order.getLate_pen());
            aux[8] = String.valueOf(order.getEarly_pen());
            aux[1] = String.valueOf(order.getEntry_date());
            aux[2] = String.valueOf(order.getPriority());
            Array.add(aux);
            System.out.println("CLASS" + Arrays.toString(aux));
        }
        return Array;
    }

    public void update_priorities() {
        for (Order order : this.orders) {
            order.update_deliver_time(Main.Calendar.getCurrentDay());
        }

        assign_priority();
    }

    public void assign_priority() {
        int i = 0;
        Pair[] pairs = new Pair[this.orders.size()];

        for (Order order : this.orders) {
            pairs[i] = new Pair(order.getNumber(), order.getPriority_index());
            i++;
        }

        Arrays.sort(pairs, Comparator.comparingDouble(pair->pair.index));

        int priority = 1;
        for (int j = 0; j < pairs.length; j++) {
            pairs[j].priority = priority;
            priority++;
        }

        for (int j = 0; j < pairs.length; j++) {
            for (Order order : this.orders) {
                if (pairs[j].numberID == order.getNumber()) {
                    order.setPriority(pairs[j].priority);
                }
            }
        }
    }

    public Order get_min_priority_order() {
        int min_priority = 10000;
        Order selected_order = null;
        for(Order order : this.orders) {
            if(order.getPriority() < min_priority) {
                min_priority = order.getPriority();
                selected_order = order;
            }
        }
        return selected_order;
    }
}
