import java.util.*;

class Workpiece {
    private final String name;

    public Workpiece(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class ProductionTransformation {
    private final Workpiece from;
    private final Workpiece to;
    private final int timeToProduce;
    private final String toolToUse;

    public ProductionTransformation(Workpiece from, Workpiece to, int timeToProduce, String toolToUse) {
        this.from = from;
        this.to = to;
        this.timeToProduce = timeToProduce;
        this.toolToUse = toolToUse;
    }

    public Workpiece getFrom() {
        return from;
    }

    public Workpiece getTo() {
        return to;
    }

    public int getTimeToProduce() {
        return timeToProduce;
    }

    public String getToolToUse() {
        return toolToUse;
    }
}

public class Transformations_Graph {
    public List<Workpiece> Nodes;
    public List<ProductionTransformation> Links;

    public Transformations_Graph() {
        this.Nodes = new ArrayList<>();
        this.Links = new ArrayList<>();

        Workpiece[] w = new Workpiece[9];
        for(int i = 1; i < 10; i++) {
            String piece = "P" + i;
            w[i-1] = new Workpiece(piece);
            this.addWorkpiece(w[i-1]);
        }

        this.addProductionTransformation(w[0], w[2], 45, "T1");
        this.addProductionTransformation(w[2], w[3], 15, "T2");
        this.addProductionTransformation(w[2], w[3], 25, "T3");
        this.addProductionTransformation(w[3], w[4], 25, "T4");
        this.addProductionTransformation(w[3], w[5], 25, "T2");
        this.addProductionTransformation(w[3], w[6], 15, "T3");
        this.addProductionTransformation(w[1], w[7], 45, "T1");
        this.addProductionTransformation(w[7], w[6], 15, "T6");
        this.addProductionTransformation(w[7], w[8], 45, "T5");

        DB_Interaction db = new DB_Interaction();
        for(ProductionTransformation pt : this.Links) {
            db.insertTransformationToDB(pt);
        }
    }

    public void addWorkpiece(Workpiece workpiece) {
        this.Nodes.add(workpiece);
    }

    public void addProductionTransformation(Workpiece from, Workpiece to, int timeToProduce, String toolToUse) {
        ProductionTransformation pt = new ProductionTransformation(from, to, timeToProduce, toolToUse);
        this.Links.add(pt);
    }

    public String toString(ProductionTransformation pt) {
        String p = "Starting Piece: " + pt.getFrom().getName() +
                "| Produced Piece: " + pt.getTo().getName() +
                "| Tool: " + pt.getToolToUse() +
                "| Processing Time: " + pt.getTimeToProduce();

        return p;
    }

    public String[][] TransformToArray() {
        String[][] Array = new String[this.Links.size()][4];
        int i = 0;
        for (ProductionTransformation pt : this.Links) {
            Array[i][0] = pt.getFrom().getName();
            Array[i][1] = pt.getTo().getName();
            Array[i][2] = String.valueOf(pt.getToolToUse());
            Array[i][3] = String.valueOf(pt.getTimeToProduce());
            i++;
        }
        return Array;
    }

    /*public List<ProductionTransformation> calculateBestPath(Workpiece from, Workpiece to) {
        List<ProductionTransformation> bestPath = new ArrayList<>();
        //identify node where "from" is
        //check if there is one step path
        //if not list all the possible paths and find possible path to "to" inside the other nodes
        //if found define the path in the list in the right order
        //if not repeate the two lines above task one last time to check if there is even a possible path to the transformation

        //if this method doesnt work try a differente approach where you start on the "to" noe and find a path to the "from" node
    }*/
}
