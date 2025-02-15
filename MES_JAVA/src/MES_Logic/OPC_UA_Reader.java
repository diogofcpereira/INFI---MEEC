package MES_Logic;

import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import OPC_client.OPC_UA_Client;

public class OPC_UA_Reader implements Runnable {
    private static Variant W1_P1;
    private static Variant W1_P2;
    private static Variant W1_P3;
    private static Variant W1_P4;
    private static Variant W1_P5;
    private static Variant W1_P6;
    private static Variant W1_P7;
    private static Variant W1_P8;
    private static Variant W1_P9;

    private static Variant W2_P1;
    private static Variant W2_P2;
    private static Variant W2_P3;
    private static Variant W2_P4;
    private static Variant W2_P5;
    private static Variant W2_P6;
    private static Variant W2_P7;
    private static Variant W2_P8;
    private static Variant W2_P9;

    private static Variant Add_P1;
    private static Variant Add_P2;

    private static Variant AAA_Piece_tp_1;
    private static Variant AAA_Piece_tp_2;
    private static Variant AAA_Piece_tp_3;
    private static Variant AAA_Piece_tp_4;
    private static Variant AAA_Piece_tp_5;
    private static Variant AAA_Piece_tp_6;

    private static Variant Clear;

    private static Variant Deliver_P5;
    private static Variant Deliver_P6;
    private static Variant Deliver_P7;
    private static Variant Deliver_P9;

    @Override
    public void run() {
        while (true) {
            try {
                W1_P1 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P1");
                W1_P2 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P2");
                W1_P3 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P3");
                W1_P4 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P4");
                W1_P5 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P5");
                W1_P6 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P6");
                W1_P7 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P7");
                W1_P8 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P8");
                W1_P9 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W1_P9");

                W2_P1 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P1");
                W2_P2 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P2");
                W2_P3 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P3");
                W2_P4 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P4");
                W2_P5 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P5");
                W2_P6 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P6");
                W2_P7 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P7");
                W2_P8 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P8");
                W2_P9 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.W2_P9");

                Add_P1 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Add_P1");
                Add_P2 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Add_P2");

                Clear = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Clear");

                Deliver_P5 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P5");
                Deliver_P6 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P6");
                Deliver_P7 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P7");
                Deliver_P9 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P9");

                AAA_Piece_tp_1 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1");
                AAA_Piece_tp_2 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_2");
                AAA_Piece_tp_3 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_3");
                AAA_Piece_tp_4 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_4");
                AAA_Piece_tp_5 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_5");
                AAA_Piece_tp_6 = OPC_UA_Client.read("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_6");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int get_W1_P1() {
        if (W1_P1 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P1.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P1 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P2() {

        if (W1_P2 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P2.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P2 is null");
            return -1;
        }
    }

    public static int get_W1_P3() {
        if (W1_P3 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P3.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P3 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P4() {
        if (W1_P4 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P4.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P4 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P5() {
        if (W1_P5 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P5.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P5 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P6() {
        if (W1_P6 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P6.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P6 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P7() {
        if (W1_P7 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P7.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P7 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P8() {
        if (W1_P8 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P8.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P8 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W1_P9() {
        if (W1_P9 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W1_P9.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W1_P9 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P1() {
        if (W2_P1 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P1.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P1 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P2() {
        if (W2_P2 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P2.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P2 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P3() {
        if (W2_P3 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P3.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P3 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P4() {
        if (W2_P4 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P4.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P4 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P5() {
        if (W2_P5 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P5.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P5 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P6() {
        if (W2_P6 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P6.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P6 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P7() {
        if (W2_P7 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P7.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P7 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P8() {
        if (W2_P8 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P8.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P8 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_W2_P9() {
        if (W2_P9 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(W2_P9.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("W2_P9 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_Add_P1() {
        if (Add_P1 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Add_P1.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Add_P1 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_Add_P2() {
        if (Add_P2 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Add_P2.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Add_P2 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_1() {
        if (AAA_Piece_tp_1 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_1.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_1 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_2() {
        if (AAA_Piece_tp_2 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_2.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_2 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_3() {
        if (AAA_Piece_tp_3 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_3.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_3 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_4() {
        if (AAA_Piece_tp_4 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_4.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_4 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_5() {
        if (AAA_Piece_tp_5 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_5.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_5 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_AAA_Piece_tp_6() {
        if (AAA_Piece_tp_6 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(AAA_Piece_tp_6.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("AAA_Piece_tp_6 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static boolean get_Clear() {
        if (Clear != null) {
            String valueAsString = String.valueOf(Clear.getValue()).trim();
            return valueAsString.equalsIgnoreCase("true");
        } else {
            System.err.println("Clear is null");
            return false; // Return a default value or handle it differently
        }
    }

    public static int get_Deliver_P5() {
        if (Deliver_P5 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Deliver_P5.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Deliver_P5 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_Deliver_P6() {
        if (Deliver_P6 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Deliver_P6.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Deliver_P6 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_Deliver_P7() {
        if (Deliver_P7 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Deliver_P7.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Deliver_P7 is null");
            return -1; // Return a default value or handle it differently
        }
    }

    public static int get_Deliver_P9() {
        if (Deliver_P9 != null) { //tenta só a dar return de uma string
            String valueAsString = String.valueOf(Deliver_P9.getValue()).trim();
            return Integer.parseInt(valueAsString);
        } else {
            System.err.println("Deliver_P9 is null");
            return -1; // Return a default value or handle it differently
        }
    }



    /*public static void main(String[] args) throws Exception {
        OPC_UA_Client client = new OPC_UA_Client("opc.tcp://localhost:4840");

        Thread startOPCUA = new Thread(new OPC_UA_Reader());
        startOPCUA.start();

        Thread.sleep(5000);

        System.out.println("Started");
        System.out.println("W2_P9:" + get_W2_P9());
        System.out.println("Deliver P9: " + get_Deliver_P9());
        System.out.println("Deliver P7: " + get_Deliver_P7());
        System.out.println("Deliver P6: " + get_Deliver_P6());
        System.out.println("Deliver P5: " + get_Deliver_P5());
        System.out.println("Clear: " + get_Clear());
        System.out.println("Piece to Produce on Cell 1: " + get_AAA_Piece_tp_1());
        System.out.println("Piece to Produce on Cell 2: " + get_AAA_Piece_tp_2());
        System.out.println("Piece to Produce on Cell 3: " + get_AAA_Piece_tp_3());
        System.out.println("Piece to Produce on Cell 4: " + get_AAA_Piece_tp_4());
        System.out.println("Piece to Produce on Cell 5: " + get_AAA_Piece_tp_5());
        System.out.println("Piece to Produce on Cell 6: " + get_AAA_Piece_tp_6());

        System.out.println("Add P1: " + get_Add_P1());
        System.out.println("Add P2: " + get_Add_P2());

        System.out.println("W1_P1:" + get_W1_P1());
        System.out.println("W1_P2:" + get_W1_P2());
        System.out.println("W1_P3:" + get_W1_P3());
        System.out.println("W1_P4:" + get_W1_P4());
        System.out.println("W1_P5:" + get_W1_P5());
        System.out.println("W1_P6:" + get_W1_P6());
        System.out.println("W1_P7:" + get_W1_P7());
        System.out.println("W1_P8:" + get_W1_P8());
        System.out.println("W1_P9:" + get_W1_P9());

        System.out.println("W2_P1:" + get_W2_P1());
        System.out.println("W2_P2:" + get_W2_P2());
        System.out.println("W2_P3:" + get_W2_P3());
        System.out.println("W2_P4:" + get_W2_P4());
        System.out.println("W2_P5:" + get_W2_P5());
        System.out.println("W2_P6:" + get_W2_P6());
        System.out.println("W2_P7:" + get_W2_P7());
        System.out.println("W2_P8:" + get_W2_P8());
        System.out.println("W2_P9:" + get_W2_P9());
    }*/
}
