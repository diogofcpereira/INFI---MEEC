import static java.lang.Math.ceil;

public class Supplier_Order extends Thread{
    private Supplier_Check SC;
    private Thread thread;
    private DB_Interaction db;

    private int SystemP1;
    private int SystemP2;

    private int P1_SupplierA;
    private int P1_SupplierB;
    private int P1_SupplierC;
    private int P2_SupplierA;
    private int P2_SupplierB;
    private int P2_SupplierC;

    public Supplier_Order() {
        SC = new Supplier_Check();
        db = new DB_Interaction();
        thread = new Thread(SC);
        thread.start();

        SystemP1 = SC.getP1_count();
        SystemP2 = SC.getP2_count();

        P1_SupplierA = 0;
        P1_SupplierB = 0;
        P1_SupplierC = 0;
        P2_SupplierA = 0;
        P2_SupplierB = 0;
        P2_SupplierC = 0;
    }

    @Override
    public void run() {
        boolean needP1 = false;
        boolean needP2 = false;

        while(true) {
            int total_pieces_P5_P6 = 0;
            int total_pieces_P7_P9 = 0;

            SystemP1 = SC.getP1_count();
            SystemP2 = SC.getP2_count();

            int numberToOrderP1 = 0;
            int numberToOrderP2 = 0;

            Order order1 = db.getMostImportantOrder();
            //Order order2 = Main.server.getProgramOrders().get_priority_order(2);

        /*for (Order order : Main.server.getProgramOrders().orders) {
            if(order.getDue_date() <= Main.Calendar.getCurrentDay() + 6) {
                if (order.getWorkpiece().equals("P5")) {
                    total_pieces_P5 += order.getQuantity();
                }

                if (order.getWorkpiece().equals("P6")) {
                    total_pieces_P6 += order.getQuantity();
                }

                if (order.getWorkpiece().equals("P7")) {
                    total_pieces_P7 += order.getQuantity();
                }

                if (order.getWorkpiece().equals("P9")) {
                    total_pieces_P9 += order.getQuantity();
                }
            }
        }*/
            if (order1 != null) {
                if (order1.getWorkpiece().equals("P5") || order1.getWorkpiece().equals("P6")) {
                    total_pieces_P5_P6 += order1.getQuantity();
                }
                if (order1.getWorkpiece().equals("P7")) {
                    total_pieces_P7_P9 += order1.getQuantity();
                }
                if(order1.getWorkpiece().equals("P9")) {
                    total_pieces_P7_P9 += order1.getQuantity() - SC.getP9_count();
                }
            }

            /*if (order2 != null) {
                if (order2.getWorkpiece().equals("P5") || order2.getWorkpiece().equals("P6")) {
                    total_pieces_P5_P6 += order2.getQuantity();
                }
                if (order2.getWorkpiece().equals("P7") || order2.getWorkpiece().equals("P9")) {
                    total_pieces_P7_P9 += order2.getQuantity();
                }
            }*/

            if (total_pieces_P5_P6 <= SystemP1) {
                needP1 = false;
            } else {
                needP1 = true;
                numberToOrderP1 = total_pieces_P5_P6 - SystemP1;
                if (numberToOrderP1 < 4) {
                    numberToOrderP1 = 4;
                }
                P1_SupplierC += numberToOrderP1;
                Supply_Count_Days_Thread C1 = new Supply_Count_Days_Thread(1, "C1", numberToOrderP1);
                C1.start();
                SystemP1 += numberToOrderP1;
                while(!db.getDelivered(order1.getNumber())) {
                    System.out.println("HOLD 1");
                }
            }

            if (total_pieces_P7_P9 <= SystemP2) {
                needP2 = false;
            } else {
                needP2 = true;
                numberToOrderP2 = total_pieces_P7_P9 - SystemP2;
                if (numberToOrderP2 < 4) {
                    numberToOrderP2 = 4;
                }
                P2_SupplierC += numberToOrderP2;
                Supply_Count_Days_Thread C2 = new Supply_Count_Days_Thread(1, "C2", numberToOrderP2);
                C2.start();
                SystemP2 += numberToOrderP2;
                while(!db.getDelivered(order1.getNumber())) {
                    System.out.println("HOLD 2");
                }
            }

            /*System.out.println("SystemP1: " + SystemP1);
            System.out.println("SystemP2: " + SystemP2);
            System.out.println("GET P1: " + SC.getP1_count());
            System.out.println("GET P2: " + SC.getP2_count());
            while(SystemP1 != SC.getP1_count() || SystemP2 != SC.getP2_count()) {
                System.out.println("HOLD");
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/

            /*if (needP1) {
                numberToOrderP1 = total_pieces_P5_P6 - SystemP1;
                if (numberToOrderP1 < 4) {
                    numberToOrderP1 = 4;
                }
                P1_SupplierC += numberToOrderP1;
                Supply_Count_Days_Thread C1 = new Supply_Count_Days_Thread(1, "C1", numberToOrderP1);
                C1.start();
            }

            if (needP2) {
                numberToOrderP2 = total_pieces_P7_P9 - SystemP2;
                if (numberToOrderP2 < 4) {
                    numberToOrderP2 = 4;
                }
                System.out.println("Number to Order 2: " + numberToOrderP2);
                P2_SupplierC += numberToOrderP2;
                Supply_Count_Days_Thread C2 = new Supply_Count_Days_Thread(1, "C2", numberToOrderP2);
                C2.start();
            }*/


        
        /*if(numberToOrderP1 >= 16) {
            //Supply P1 from A
            P1_SupplierA += numberToOrderP1;
            Supply_Count_Days_Thread A1 = new Supply_Count_Days_Thread(4, "A1", numberToOrderP1);
            A1.start();
        } else if (numberToOrderP1 >= 8) {
            //Supply P1 from B
            P1_SupplierB += numberToOrderP1;
            Supply_Count_Days_Thread B1 = new Supply_Count_Days_Thread(2, "B1", numberToOrderP1);
            B1.start();
        } else if (numberToOrderP1 > 0){
            //Supply P1 fom C
            P1_SupplierC += numberToOrderP1;
            Supply_Count_Days_Thread C1 = new Supply_Count_Days_Thread(1, "C1", numberToOrderP1);
            C1.start();
        }

        if(numberToOrderP2 >= 16) {
            //Supply P2 from A
            P2_SupplierA += numberToOrderP1;
            Supply_Count_Days_Thread A2 = new Supply_Count_Days_Thread(4, "A2", numberToOrderP2);
            A2.start();
        } else if (numberToOrderP2 >= 8) {
            //Supply P2 from B
            P2_SupplierB += numberToOrderP2;
            Supply_Count_Days_Thread B2 = new Supply_Count_Days_Thread(2, "B2", numberToOrderP2);
            B2.start();
        } else if (numberToOrderP2 > 0) {
            //Supply P2 from C
            P2_SupplierC += numberToOrderP2;
            Supply_Count_Days_Thread C2 = new Supply_Count_Days_Thread(1, "C2", numberToOrderP2);
            C2.start();
        }*/
        }
    }

    public int getP1_SupplierA() {
        return P1_SupplierA;
    }

    public int getP1_SupplierB() {
        return P1_SupplierB;
    }

    public int getP1_SupplierC() {
        return P1_SupplierC;
    }

    public int getP2_SupplierA() {
        return P2_SupplierA;
    }

    public int getP2_SupplierB() {
        return P2_SupplierB;
    }

    public int getP2_SupplierC() {
        return P2_SupplierC;
    }
}
