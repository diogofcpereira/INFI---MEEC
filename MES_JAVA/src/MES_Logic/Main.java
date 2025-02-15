package MES_Logic;

import OPC_client.OPC_UA_Client;

public class Main {
    private static DB_Interaction db = new DB_Interaction();
    public static Day_Counter DC = new Day_Counter();
    //public static Deliver_Thread DT = new Deliver_Thread();

    public static VariableDisplay variableDisplay = new VariableDisplay();

    public static void main(String[] args) throws Exception {
        OPC_UA_Client client = new OPC_UA_Client("opc.tcp://localhost:4840");

        Thread startOPCUA = new Thread(new OPC_UA_Reader());
        startOPCUA.start();

        DC.start();
        //DT.start();

        Thread server = new TCP_server();
        server.start();

        Thread warehouses = new Thread(new Warehouse_Control());
        warehouses.start();

        Thread startCell = new Thread(new Cell());
        startCell.start();

        Logic logic = new Logic();


        Thread.sleep(2000);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        while (true) {
            //logic.AddP2(10);
            Order order = null;
            while (order == null) {
                System.out.println("Waiting for order");
                Thread.sleep(500);
                order = db.getMostImportantOrder();
            }
            //Order order = new Order("clientAA", 5, "P5", 2, 6, 1, 5, 6);
            //Order real_order = new Order(order.getClient(), order.getNumber(), order.getWorkpiece(),order.getQuantity(), order.getDue_date(), order.getEntry_date(), order.getEarly_penalty(), order.getLate_penalty());
            order.start();

            logic.Produce(order, variableDisplay);

            while (DC.getSecond() >= 30) {
                System.out.println("Waiting for <30s");
                Thread.sleep(500);
            }

            logic.Deliver(order, variableDisplay);
            while (!order.getAll_done()) {
                System.out.println("Not done");
            }

            order.stop_thread();
            System.out.println("Is it done: " + order.getAll_done());
            System.out.println("Order Production Cost: " + order.getTotalOrderCost());

            db.update_values(order);

            //DT.add_order(order);
        }
    }
}