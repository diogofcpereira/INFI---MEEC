package MES_Logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Order extends Thread {
    private String client;
    private int number;
    private String workpiece;
    private int quantity;
    private int production_tracker;
    private int due_date;
    private int early_penalty;
    private int late_penalty;
    private long entry_date;
    private long deliver_date;

    private boolean all_done;
    private boolean all_delivered;

    private float totalOrderCost;

    private Piece[] setOfPieces;

    private DB_Interaction db;

    private boolean running;

    public Order(String client, int number, String workpiece, int quantity, int due_date, long currentDay, int early_penalty, int late_penalty) {
        this.client = client;
        this.number = number;
        this.workpiece = workpiece;
        this.quantity = quantity;
        this.production_tracker = 0;
        this.due_date = due_date;
        this.entry_date = currentDay;
        this.early_penalty = early_penalty;
        this.late_penalty = late_penalty;
        this.all_done = false;
        this.all_delivered = false;

        setOfPieces = new Piece[quantity];

        db = new DB_Interaction();

        running = true;
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

    public long getDeliver_date() {
        return this.deliver_date;
    }

    public long getEntry_date() {return this.entry_date;}

    public int getEarly_penalty() {
        return early_penalty;
    }

    public int getLate_penalty() {
        return late_penalty;
    }

    public Piece[] getSetOfPieces() {return this.setOfPieces;}

    public boolean getAll_done() {return this.all_done;}

    public float getTotalOrderCost() {return this.totalOrderCost;}

    public boolean getAll_delivered() {return this.all_delivered;}

    public void setDeliver_date(long deliver_date) {this.deliver_date = deliver_date;}

    public void setAll_done() {
            this.all_done = true;
    }

    public void setAll_delivered() {
        this.all_delivered = true;
    }

    public void addPiece(Piece piece) {
        for(int i = 0; i < setOfPieces.length; i++) {
            if(setOfPieces[i] == null) {
                setOfPieces[i] = piece;
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "Client= " + this.client + " ---> Order{" +
                "number= " + this.number +
                ", workpiece= " + this.workpiece +
                ", quantity= " + this.quantity +
                ", due_date= " + this.due_date +
                ", entry_date= " + this.entry_date +
                "}";
    }

    @Override
    public void run() {
        while(running) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            List<Piece> Aux = new ArrayList<>();
            //probably it will need to be synchronized
            for (int i = 0; i < quantity; i++) {
                if (setOfPieces[i] == null) {
                    if (this.workpiece.equals("P9")) {
                        synchronized (Warehouse_Control.getW2().getWarehouse_pieces()) {
                            Aux = Warehouse_Control.getW2().getWarehouse_pieces();
                            Iterator<Piece> iterator = Aux.iterator();
                            boolean pieceFound = false;
                            while (iterator.hasNext()) {
                                Piece piece = iterator.next();
                                if (piece.getOrder_number() == 0 && piece.getActual_pieceID() == 9) {
                                    piece.setOrder_number(this.number);
                                    setOfPieces[i] = piece;
                                    pieceFound = true;
                                    System.out.println("ADD ONE MORE P9");
                                    break;
                                }
                            }

                            // Debugging information
                            if (!pieceFound) {
                                System.out.println("No suitable P9 piece found in Warehouse W2");
                            }
                        }

                        if (setOfPieces[i] == null || setOfPieces[i].getOrder_number() == 0) {
                            synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
                                Aux = Warehouse_Control.getW1().getWarehouse_pieces();
                                Iterator<Piece> iterator = Aux.iterator();
                                boolean pieceFound = false;
                                while (iterator.hasNext()) {
                                    Piece piece = iterator.next();
                                    if (piece.getOrder_number() == 0 && piece.getActual_pieceID() == 2) {
                                        piece.setOrder_number(this.number);
                                        setOfPieces[i] = piece;
                                        pieceFound = true;
                                        System.out.println("ADD ONE MORE P2");
                                        break;
                                    }
                                }

                                // Debugging information
                                if (!pieceFound) {
                                    System.out.println("No suitable P2 piece found in Warehouse W1");
                                }
                            }
                        }

                        // Final check before accessing setOfPieces[i]
                        if (setOfPieces[i] == null) {
                            System.out.println("setOfPieces[i] is still null after both searches");
                        } else {
                            System.out.println("setOfPieces[i] has been assigned successfully");
                        }
                    } else if (this.workpiece.equals("P7")) {
                        synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
                            Aux = Warehouse_Control.getW1().getWarehouse_pieces();
                            Iterator<Piece> iterator = Aux.iterator();
                            while (iterator.hasNext()) {
                                Piece piece = iterator.next();
                                if (piece.getOrder_number() == 0 && piece.getActual_pieceID() == 2) {
                                    piece.setOrder_number(this.number);
                                    setOfPieces[i] = piece;
                                    System.out.println("ADD ONE MORE P2");
                                    break;
                                }
                            }
                        }
                    } else if (this.workpiece.equals("P6")) {
                        synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
                            Aux = Warehouse_Control.getW1().getWarehouse_pieces();
                            Iterator<Piece> iterator = Aux.iterator();
                            while (iterator.hasNext()) {
                                Piece piece = iterator.next();
                                if (piece.getOrder_number() == 0 && piece.getActual_pieceID() == 1) {
                                    piece.setOrder_number(this.number);
                                    setOfPieces[i] = piece;
                                    System.out.println("ADD ONE MORE P1");
                                    break;
                                }
                            }
                        }
                    } else if (this.workpiece.equals("P5")) {
                        synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
                            Aux = Warehouse_Control.getW1().getWarehouse_pieces();
                            Iterator<Piece> iterator = Aux.iterator();
                            while (iterator.hasNext()) {
                                Piece piece = iterator.next();
                                if (piece.getOrder_number() == 0 && piece.getActual_pieceID() == 1) {
                                    piece.setOrder_number(this.number);
                                    setOfPieces[i] = piece;
                                    System.out.println("ADD ONE MORE P1");
                                    break;
                                }
                            }
                        }
                    }
                }
            }

            /*if (all_done) {
                if (this.due_date == db.getDay()) {
                    for (int i = 0; i < setOfPieces.length; i++) {
                        totalOrderCost += setOfPieces[i].PieceProductionCost();
                    }
                } else if (this.due_date < db.getDay()) {
                    for (int i = 0; i < setOfPieces.length; i++) {
                        totalOrderCost += setOfPieces[i].PieceProductionCost();
                    }
                    totalOrderCost = totalOrderCost + (db.getDay() - this.due_date) * this.late_penalty;
                } else if (this.due_date > db.getDay()) {
                    for (int i = 0; i < setOfPieces.length; i++) {
                        totalOrderCost += setOfPieces[i].PieceProductionCost();
                    }
                    totalOrderCost = totalOrderCost + (this.due_date - db.getDay()) * this.early_penalty;
                }
            }*/
        }
    }

    public void stop_thread() {
        running = false;

        if (this.due_date == db.getDay()) {
            for (int i = 0; i < setOfPieces.length; i++) {
                totalOrderCost += setOfPieces[i].PieceProductionCost();
            }
        } else if (this.due_date < db.getDay()) {
            for (int i = 0; i < setOfPieces.length; i++) {
                totalOrderCost += setOfPieces[i].PieceProductionCost();
            }
            totalOrderCost = totalOrderCost + (db.getDay() - this.due_date) * this.late_penalty;
        } else if (this.due_date > db.getDay()) {
            for (int i = 0; i < setOfPieces.length; i++) {
                totalOrderCost += setOfPieces[i].PieceProductionCost();
            }

            totalOrderCost = totalOrderCost + (this.due_date - db.getDay()) * this.early_penalty;
        }
    }
}