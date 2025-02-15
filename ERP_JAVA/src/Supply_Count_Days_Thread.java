public class Supply_Count_Days_Thread extends Thread {
    private int number_of_days;
    private String indicator;
    private int quantity;

    private TCPSocketConnection tcp;

    public Supply_Count_Days_Thread(int number_of_days, String indicator, int quantity) {
        this.number_of_days = number_of_days;
        this.indicator = indicator;
        this.quantity = quantity;

        tcp = new TCPSocketConnection();
    }

    public void run() {
        try {
            while (number_of_days > 0) {
                Thread.sleep(60*1000);
                number_of_days--;
            }

            tcp.openConnection();

            switch (indicator) {
                case "A1":
                    tcp.set_SA_P1(quantity);
                    tcp.close();
                    break;

                case "A2":
                    tcp.set_SA_P2(quantity);
                    tcp.close();
                    break;

                case "B1":
                    tcp.set_SB_P1(quantity);
                    tcp.close();
                    break;

                case "B2":
                    tcp.set_SB_P2(quantity);
                    tcp.close();
                    break;

                case "C1":
                    tcp.set_SC_P1(quantity);
                    tcp.close();
                    break;

                case "C2":
                    System.out.println("SEND!!!!!");
                    tcp.set_SC_P2(quantity);
                    tcp.close();
                    break;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
