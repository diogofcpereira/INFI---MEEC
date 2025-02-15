import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String client;
    private int number;
    private String workpiece;
    private int quantity;
    private int due_date;
    private int late_pen;
    private int early_pen;
    private long entry_date;
    private int priority;
    private long worst_case_time;
    private long today_to_deliver;
    private float priority_index;

    public Order(String client, int number, String workpiece, int quantity, int due_date, int late_pen, int early_pen, long currentDay) {
        this.client = client;
        this.number = number;
        this.workpiece = workpiece;
        this.quantity = quantity;
        this.due_date = due_date;
        this.late_pen = late_pen;
        this.early_pen = early_pen;
        this.entry_date = currentDay;
        calculateWorstCaseTime();
        TodayToDueDate();
        this.priority_index = this.today_to_deliver - (float) this.worst_case_time /60;
        this.priority = 0;
    }

    public void update_deliver_time(long currentDay) {
        this.today_to_deliver = this.due_date - currentDay;
        this.priority_index = this.today_to_deliver - (float) this.worst_case_time /60;
    }

    public String getClient() {
        return this.client;
    }

    public int getNumber() {
        return this.number;
    }

    public String getWorkpiece() {
        return this.workpiece;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getDue_date() {
        return this.due_date;
    }

    public int getLate_pen() {
        return this.late_pen;
    }

    public int getEarly_pen() {
        return this.early_pen;
    }

    public long getEntry_date() {return this.entry_date;}

    public long getWorst_case_time() {return this.worst_case_time;}

    public long getToday_to_deliver() {return this.today_to_deliver;}

    public float getPriority_index() {return this.priority_index;}

    public int getPriority() {return this.priority;}

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void calculateWorstCaseTime() {
        switch (this.getWorkpiece()) {
            case "P5", "P6" -> {
                this.worst_case_time = this.getQuantity() * 95L;
            }
            case "P7" -> {
                this.worst_case_time = this.getQuantity() * 85L;
            }
            case "P9" -> {
                this.worst_case_time = this.getQuantity() * 90L;
            }
        }
    }

    public void TodayToDueDate() {
        this.today_to_deliver = this.due_date - this.entry_date;
    }

    @Override
    public String toString() {
        return "Client= " + this.client + " ---> Order{" +
                "number= " + this.number +
                ", workpiece= " + this.workpiece +
                ", quantity= " + this.quantity +
                ", due_date= " + this.due_date +
                ", late_pen= " + this.late_pen +
                ", early_pen= " + this.early_pen +
                ", entry_date= " + this.entry_date +
                "}";
    }

}
