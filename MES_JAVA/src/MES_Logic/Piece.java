package MES_Logic;

public class Piece {
    private int source_pieceID;
    private int actual_pieceID;
    private int order_number;

    private int raw_price;

    private int arrival_date;
    private int dispatch_date;

    private double totalProductionTime;

    public Piece(int source_pieceID, int actual_pieceID, int raw_price, int arrival_date) {
        this.source_pieceID = source_pieceID;
        this.actual_pieceID = actual_pieceID;

        this.raw_price = raw_price;

        this.arrival_date = arrival_date;

        this.totalProductionTime = 0;
    }

    public void setActual_pieceID(int actual_pieceID) {
        this.actual_pieceID = actual_pieceID;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public void setRaw_price(int raw_price) {
        this.raw_price = raw_price;
    }

    public void setDispatch_date(int dispatch_date) {
        this.dispatch_date = dispatch_date;
    }

    public int getSource_pieceID() {
        return source_pieceID;
    }

    public int getActual_pieceID() {
        return actual_pieceID;
    }

    public int getOrder_number() {
        return order_number;
    }

    public int getRaw_price() {
        return raw_price;
    }

    public int getArrival_date() {
        return arrival_date;
    }

    public int getDispatch_date() {
        return dispatch_date;
    }

    public double getTotalProductionTime() {
        return totalProductionTime;
    }

    public void addTotalProductionTime(double totalProductionTimeToAdd) {
        this.totalProductionTime += totalProductionTimeToAdd;
    }

    public float PieceProductionCost() {
        float depreciation_cost = (float) (raw_price * (dispatch_date - arrival_date) * 0.01);

        float production_cost = (float) (1 * totalProductionTime);

        return raw_price + depreciation_cost + production_cost;
    }
}
