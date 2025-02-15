import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UDP_Server extends Thread {
    private XML_Parse xml = new XML_Parse();
    private static final int PORT = 24680;
    private static final int BUFFER_SIZE = 1024;
    private DatagramPacket packet;
    private String receivedData = "";

    private Orders_List PROGRAM_ORDERS;
    private List<String[]> COMPLETED_ORDERS;
    
    private DB_Interaction db;

    public UDP_Server() {
        PROGRAM_ORDERS = new Orders_List();
        COMPLETED_ORDERS = new ArrayList<>();
        db = new DB_Interaction();
    }

    @Override
    public void run() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("XMLFileServer is running and listening on port " + PORT);

            while (true) {
                byte[] buffer = new byte[BUFFER_SIZE];
                packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                
                receivedData = new String(packet.getData(), 0, packet.getLength());
                if(receivedData.isEmpty()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {

                    System.out.println("File received");
                    //System.out.println(receivedData);

                    List<Order> order_entry_list = xml.parse_entry(receivedData, Main.Calendar.getCurrentDay());
                    List<Order> ordersToAdd = new ArrayList<>(order_entry_list);
                    receivedData = "";

                    for (Order order : ordersToAdd) {
                        PROGRAM_ORDERS.addOrder(order);
                        //System.out.println(PROGRAM_ORDERS);
                    }

                    synchronized (PROGRAM_ORDERS) {
                        COMPLETED_ORDERS = db.getCertain_Orders(true);

                        // Create an iterator for the program orders
                        Iterator<Order> orderIterator = PROGRAM_ORDERS.orders.iterator();
                        while (orderIterator.hasNext()) {
                            Order order = orderIterator.next();

                            for (String[] order_completed : COMPLETED_ORDERS) {
                                // Check if the order number matches
                                if (Integer.parseInt(order_completed[0].trim()) == order.getNumber()) {
                                    // Remove the order using the iterator
                                    orderIterator.remove();
                                    System.out.println("PROGRAM_ORDERS: " + PROGRAM_ORDERS.orders);
                                    break; // Break out of the inner loop as we have already removed this order
                                }
                            }
                        }

                        // Update priorities
                        PROGRAM_ORDERS.update_priorities();

                        // Insert remaining orders to the database
                        for (Order order : PROGRAM_ORDERS.orders) {
                            db.insertOrderToDB(order);
                        }
                    }
                }
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public String getReceivedData() {
        return receivedData;
    }

    public Orders_List getProgramOrders() {
        return PROGRAM_ORDERS;
    }
}
