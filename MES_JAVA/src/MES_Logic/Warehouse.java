package MES_Logic;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private int[] warehouse_counter;

    private List<Piece> warehouse_pieces;

    public Warehouse() {
        this.warehouse_counter = new int[9];
        this.warehouse_pieces = new ArrayList<Piece>();
    }

    public void setWarehouse_counter(int[] warehouse_counter) {
        for (int i = 0; i < warehouse_counter.length; i++) {
            this.warehouse_counter[i] = warehouse_counter[i];
        }
    }

    public int[] getWarehouse_counter() {
        return warehouse_counter;
    }

    public void setWarehouse_pieces(List<Piece> warehouse_pieces) {
        this.warehouse_pieces = warehouse_pieces;
    }

    public List<Piece> getWarehouse_pieces() {
        return warehouse_pieces;
    }

    public void addPiece(Piece piece) {
        this.warehouse_pieces.add(piece);
    }

    public void removePiece(Piece piece) {
        this.warehouse_pieces.remove(piece);
    }
}
