public class Supplier {
    private String name;
    private String[] piece=new String[2];
    private int[] minimum_order=new int[2];
    private int[] price_per_piece=new int[2];
    private int[] days_to_deliver=new int[2];

    public Supplier(String name, int minimum_order_1,int minimum_order_2, int price_per_piece_1, int price_per_piece_2, int days_to_deliver_1, int days_to_deliver_2) {
        this.name = name;
        this.piece[0] = "P1";
        this.piece[1] = "P2";
        this.minimum_order[0] = minimum_order_1;
        this.minimum_order[1] = minimum_order_2;
        this.price_per_piece[0] = price_per_piece_1;
        this.price_per_piece[1] = price_per_piece_2;
        this.days_to_deliver[0] = days_to_deliver_1;
        this.days_to_deliver[1] = days_to_deliver_2;
    }

    public String getName() {
        return name;
    }

    public int[] getMinimum_order() {
        return minimum_order;
    }

    public int[] getPrice_per_piece() {
        return price_per_piece;
    }

    public int[] getDays_to_deliver() {
        return days_to_deliver;
    }

    public String[] getPiece() {
        return piece;
    }
}
