package MES_Logic;

import OPC_client.OPC_UA_Client;

public class Cell implements Runnable {
    private static boolean C1;
    private static boolean C2;
    private static boolean C3;
    private static boolean C4;
    private static boolean C5;
    private static boolean C6;

    public static boolean getC1() {
        return C1;
    }
    public static boolean getC2() {
        return C2;
    }
    public static boolean getC3() {
        return C3;
    }
    public static boolean getC4() {
        return C4;
    }
    public static boolean getC5() {
        return C5;
    }
    public static boolean getC6() {
        return C6;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
                if (OPC_UA_Reader.get_AAA_Piece_tp_1() == 0) {
                    C1=false;
                }
                else{
                    C1=true;
                }

                if (OPC_UA_Reader.get_AAA_Piece_tp_2() == 0) {
                    C2=false;
                }
                else{
                    C2=true;
                }

                if (OPC_UA_Reader.get_AAA_Piece_tp_3() == 0) {
                    C3=false;
                }
                else{
                    C3=true;
                }

                if (OPC_UA_Reader.get_AAA_Piece_tp_4() == 0) {
                    C4=false;
                }
                else{
                    C4=true;
                }

                if (OPC_UA_Reader.get_AAA_Piece_tp_5() == 0) {
                    C5=false;
                }
                else{
                    C5=true;
                }

                if (OPC_UA_Reader.get_AAA_Piece_tp_6() == 0) {
                    C6=false;
                }
                else{
                    C6=true;
                }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
