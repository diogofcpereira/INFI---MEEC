package MES_Logic;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import java.sql.SQLException;
import java.util.Arrays;

import OPC_client.OPC_UA_Client;

public class Warehouse_Control implements Runnable{
    private static Warehouse W1;
    private static Warehouse W2;

    private DB_Interaction db;

    public Warehouse_Control() throws Exception {
        W1 = new Warehouse();
        W2 = new Warehouse();
        db = new DB_Interaction();

        for(int i = 0; i < 20; i++) {
            Piece p = new Piece(1, 1, 30, 0);
            W1.addPiece(p);
        }
        for(int i = 0; i < 10; i++) {
            Piece p = new Piece(2, 9, 10, 0);
            W2.addPiece(p);
        }
    }

    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(100);
                setW1(OPC_UA_Reader.get_W1_P1(), OPC_UA_Reader.get_W1_P2(), OPC_UA_Reader.get_W1_P3(), OPC_UA_Reader.get_W1_P4(), OPC_UA_Reader.get_W1_P5(), OPC_UA_Reader.get_W1_P6(), OPC_UA_Reader.get_W1_P7(), OPC_UA_Reader.get_W1_P8(), OPC_UA_Reader.get_W1_P9());
                setW2(OPC_UA_Reader.get_W2_P1(), OPC_UA_Reader.get_W2_P2(), OPC_UA_Reader.get_W2_P3(), OPC_UA_Reader.get_W2_P4(), OPC_UA_Reader.get_W2_P5(), OPC_UA_Reader.get_W2_P6(), OPC_UA_Reader.get_W2_P7(), OPC_UA_Reader.get_W2_P8(), OPC_UA_Reader.get_W2_P9());
                sendW1ToDB();
                sendW2ToDB();
            } catch (SQLException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendW1ToDB () throws SQLException {
            db.insertW1ToDB(W1);
    }

    public void sendW2ToDB () throws SQLException {
            db.insertW2ToDB(W2);
    }

    public void setW1 (int P1, int P2, int P3, int P4, int P5, int P6, int P7, int P8, int P9) {
        int[] array = new int[9];
        array[0] = P1;
        array[1] = P2;
        array[2] = P3;
        array[3] = P4;
        array[4] = P5;
        array[5] = P6;
        array[6] = P7;
        array[7] = P8;
        array[8] = P9;
        W1.setWarehouse_counter(array);
    }

    public void setW2 (int P1, int P2, int P3, int P4, int P5, int P6, int P7, int P8, int P9) {
        int[] array = new int[9];
        array[0] = P1;
        array[1] = P2;
        array[2] = P3;
        array[3] = P4;
        array[4] = P5;
        array[5] = P6;
        array[6] = P7;
        array[7] = P8;
        array[8] = P9;
        W2.setWarehouse_counter(array);
    }

    public static Warehouse getW1() {
        return W1;
    }

    public static Warehouse getW2() {
        return W2;
    }

    /*public static void main(String[] args) throws Exception {
        OPC_UA_Client client = new OPC_UA_Client("opc.tcp://localhost:4840");
        Thread startOPCUA = new Thread(new OPC_UA_Reader());
        startOPCUA.start();

        Thread warehouse_control = new Thread(new Warehouse_Control());
        warehouse_control.start();

        while(true) {
            Thread.sleep(2000);
            System.out.println("W1");
            System.out.println(Arrays.toString(W1.getWarehouse_counter()));
            System.out.println("W2");
            System.out.println(Arrays.toString(W2.getWarehouse_counter()));
            System.out.println("----------");
        }
    }*/
}
