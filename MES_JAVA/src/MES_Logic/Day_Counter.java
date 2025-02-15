package MES_Logic;

public class Day_Counter extends Thread {
    private long today;
    private int second;

    private DB_Interaction db;

    private Logic logic;

    public Day_Counter() {
        db = new DB_Interaction();
        logic = new Logic();
        today = 0;
        second = 0;
        System.out.println("Calendar START");
    }

    @Override
    public void run() {
        while(true) {
            //System.out.println("running");
            today = db.getDay();
            second = db.getSecond();
            //System.out.println(second);

            if(second > 45) {
                //System.out.println("CLEAR");
                try {
                    logic.Clear(true);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                //System.out.println("NOT CLEAR");
                try {
                    logic.Clear(false);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public long getToday() {
        return today;
    }

    public int getSecond() {
        return second;
    }
}
