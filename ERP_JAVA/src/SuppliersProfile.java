import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SuppliersProfile {
    List<Supplier> suppliers;

    public SuppliersProfile() {
        suppliers = new ArrayList<Supplier>();

        Supplier A = new Supplier("SupplierA", 16, 16, 30, 10, 4, 4);
        Supplier B = new Supplier("SupplierB", 8, 8, 45, 15, 2, 2);
        Supplier C = new Supplier("SupplierC", 4, 4, 55, 18, 1, 1);

        addSupplier(A);
        addSupplier(B);
        addSupplier(C);

        DB_Interaction db = new DB_Interaction();
        for (Supplier s : this.suppliers) {
            db.insertSuppliersToDB(s);
        }
    }

    public void addSupplier(Supplier supplier) {
        suppliers.add(supplier);
    }

    public List<Supplier> getSupplierByName(String name) {
        List<Supplier> return_supplier = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            if(supplier.getName().equals(name)) {
                return_supplier.add(supplier);
            }
        }

        return return_supplier;
    }

    public String[][] TransformToArray() {
        String[][] Array = new String[this.suppliers.size()*2][5];
        int i = 0;
        for (Supplier supplier : this.suppliers) {
            Array[i][0] = supplier.getName();
            Array[i][1] = supplier.getPiece()[0];
            Array[i][2] = String.valueOf(supplier.getMinimum_order()[0]);
            Array[i][3] = String.valueOf(supplier.getPrice_per_piece()[0]);
            Array[i][4] = String.valueOf(supplier.getDays_to_deliver()[0]);
            i++;
            Array[i][0] = supplier.getName();
            Array[i][1] = supplier.getPiece()[1];
            Array[i][2] = String.valueOf(supplier.getMinimum_order()[1]);
            Array[i][3] = String.valueOf(supplier.getPrice_per_piece()[1]);
            Array[i][4] = String.valueOf(supplier.getDays_to_deliver()[1]);
            i++;
        }

        return Array;
    }
}
