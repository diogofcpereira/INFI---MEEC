package MES_Logic;

import OPC_client.OPC_UA_Client;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class Logic {
    private int done4= 0;
    private int diff4= 0;
    private int done5= 0;
    private int diff5= 0;
    private int done6= 0;
    private int diff6= 0;
    private int done7= 0;
    private int diff7= 0;
    private int done8= 0;
    private int diff8= 0;
    private int done9= 0;
    private int diff9= 0;
    private boolean flag6= false;
    private boolean flag4= false;
    private boolean flag8= false;
    private boolean flag7= false;
    private boolean flag9= false;

    private int Cell_1_Mtop_Op_Time=0;
    private int Cell_2_Mtop_Op_Time=0;
    private int Cell_3_Mtop_Op_Time=0;
    private int Cell_4_Mtop_Op_Time=0;
    private int Cell_5_Mtop_Op_Time=0;
    private int Cell_6_Mtop_Op_Time=0;
    private int Cell_1_Mtop_Counter=0;
    private int Cell_2_Mtop_Counter=0;
    private int Cell_3_Mtop_Counter=0;
    private int Cell_4_Mtop_Counter=0;
    private int Cell_5_Mtop_Counter=0;
    private int Cell_6_Mtop_Counter=0;

    private int Cell_1_MBot_Op_Time=0;
    private int Cell_2_MBot_Op_Time=0;
    private int Cell_3_MBot_Op_Time=0;
    private int Cell_4_MBot_Op_Time=0;
    private int Cell_5_MBot_Op_Time=0;
    private int Cell_6_MBot_Op_Time=0;
    private int Cell_1_MBot_Counter=0;
    private int Cell_2_MBot_Counter=0;
    private int Cell_3_MBot_Counter=0;
    private int Cell_4_MBot_Counter=0;
    private int Cell_5_MBot_Counter=0;
    private int Cell_6_MBot_Counter=0;

    private int P5_Unloaded=0;
    private int P6_Unloaded=0;
    private int P7_Unloaded=0;
    private int P9_Unloaded=0;

    private DB_Interaction db = new DB_Interaction();

    private int actual_quantity;
    private boolean first_p9;

    public boolean Select_piece_to_produce(int piece, int quantity) throws Exception {
        Variant P4 = new Variant(UShort.valueOf((short) 4));
        Variant P5 = new Variant(UShort.valueOf((short) 5));
        Variant P6 = new Variant(UShort.valueOf((short) 6));
        Variant P7 = new Variant(UShort.valueOf((short) 7));
        Variant P8 = new Variant(UShort.valueOf((short) 8));
        Variant P9 = new Variant(UShort.valueOf((short) 9));
        if (piece == 5) {
            Thread.sleep(1000);
            if (OPC_UA_Reader.get_W1_P1() >= quantity || flag4) {
                flag4 = true;
                diff4 = quantity - done4;
                if (diff4 != 0 && !Cell.getC1()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1", P4);
                    Cell_1_Mtop_Op_Time+=45;
                    Cell_1_Mtop_Counter++;
                    Cell_1_MBot_Op_Time+=15;
                    Cell_1_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    //System.out.println("done change 1");
                } else if (diff4 != 0 && !Cell.getC2()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_2", P4);
                    Cell_2_Mtop_Op_Time+=45;
                    Cell_2_Mtop_Counter++;
                    Cell_2_MBot_Op_Time+=15;
                    Cell_2_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    //System.out.println("done change 2");
                } else if (diff4 != 0 && !Cell.getC3()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_3", P4);
                    Cell_3_Mtop_Op_Time+=45;
                    Cell_3_Mtop_Counter++;
                    Cell_3_MBot_Op_Time+=25;
                    Cell_3_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    System.out.println("done change 3");
                }
            }
            if (OPC_UA_Reader.get_W1_P4() > 0){
                diff5 = quantity - done5;
                //System.out.print(quantity);
                if (diff5 > 0 && !Cell.getC4()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_4",P5);
                    Cell_4_Mtop_Op_Time+=25;
                    Cell_4_Mtop_Counter++;
                    done5++;
                }
            }
            if(OPC_UA_Reader.get_W2_P5()==quantity){
                //Deliver(piece, quantity);
                return false;
            }
            return true;
        }
        else if (piece == 6) {
            //System.out.println("ENtered");
            Thread.sleep(1000);
            if (OPC_UA_Reader.get_W1_P1() >= quantity || flag4) {
                flag4 = true;
                //System.out.println("ENtered 2");
                diff4 = quantity - done4;
                if (diff4 != 0 && !Cell.getC1()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1", P4);
                    Cell_1_Mtop_Op_Time+=45;
                    Cell_1_Mtop_Counter++;
                    Cell_1_MBot_Op_Time+=15;
                    Cell_1_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    //System.out.println("done change 1");
                } else if (diff4 != 0 && !Cell.getC2()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_2", P4);
                    Cell_2_Mtop_Op_Time+=45;
                    Cell_2_Mtop_Counter++;
                    Cell_2_MBot_Op_Time+=15;
                    Cell_2_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    //System.out.println("done change 2");
                } else if (diff4 != 0 && !Cell.getC3()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_3", P4);
                    Cell_3_Mtop_Op_Time+=45;
                    Cell_3_Mtop_Counter++;
                    Cell_3_MBot_Op_Time+=25;
                    Cell_3_MBot_Counter++;
                    done4++;
                    diff4 = quantity - done4; // Update diff4 after done4 is incremented
                    System.out.println("done change 3");
                }
            }
            if (OPC_UA_Reader.get_W1_P4() == quantity || flag6){
                //System.out.println("Entered flag6");
                flag6=true;
                diff6 = quantity - done6;
                //System.out.print(quantity);
                if (diff6 > 0 && !Cell.getC1()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1",P6);
                    Cell_1_MBot_Op_Time+=25;
                    Cell_1_MBot_Counter++;
                    done6++;
                    diff6 = quantity - done6;
                } else if (diff6 > 0 && !Cell.getC2()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_2",P6);
                    Cell_2_MBot_Op_Time+=25;
                    Cell_2_MBot_Counter++;
                    done6++;
                    diff6 = quantity - done6;
                }
            }
            if(OPC_UA_Reader.get_W2_P6()==quantity){
                //Deliver(piece, quantity);
                return false;
            }
            return true;
        }
        else if (piece == 7) {
            Thread.sleep(1000);
            int quantity8;
            //int quantity4;
            //quantity4= (int) Math.floor((float)quantity/2);
            //quantity8= (int) Math.ceil((float)quantity/2);
            quantity8=quantity;
            /*if (OPC_UA_Reader.get_W1_P1() >= quantity4 || flag4) {
                flag4=true;
                //System.out.println("ENtered floor");
                diff4 = quantity4 - done4;
                if (diff4 != 0 && !Cell.getC1()) {
                    //System.out.println("ENtered set");
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1", P4);
                    Cell_1_Mtop_Op_Time+=45;
                    Cell_1_Mtop_Counter++;
                    Cell_1_MBot_Op_Time+=15;
                    Cell_1_MBot_Counter++;
                    done4++;
                    diff4 = quantity4 - done4; // Update diff4 after done4 is incremented
                    System.out.println("done change 1");
                } else if (diff4 != 0 && !Cell.getC2()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_2", P4);
                    Cell_2_Mtop_Op_Time+=45;
                    Cell_2_Mtop_Counter++;
                    Cell_2_MBot_Op_Time+=15;
                    Cell_2_MBot_Counter++;
                    done4++;
                    diff4 = quantity4 - done4; // Update diff4 after done4 is incremented
                    System.out.println("done change 2");
                }
            }*/
            if (OPC_UA_Reader.get_W1_P2() >= quantity8 || flag8) {
                flag8=true;
                //System.out.println("ENtered 2");
                diff8 = quantity8 - done8;
                if (diff8 != 0 && !Cell.getC4()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_4", P8);
                    Cell_4_MBot_Op_Time+=45;
                    Cell_4_MBot_Counter++;
                    done8++;
                    diff8 = quantity8 - done8; // Update diff4 after done4 is incremented
                    System.out.println(quantity8);
                    //System.out.println("done change 1 in 8");
                } else if (diff8 != 0 && !Cell.getC5()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_5", P8);
                    Cell_5_MBot_Op_Time+=45;
                    Cell_5_MBot_Counter++;
                    done8++;
                    diff8 = quantity8 - done8; // Update diff4 after done4 is incremented
                    //System.out.println("done change 2 in 8");
                }
            }
            /*if (OPC_UA_Reader.get_W1_P4() > 0){
                diff7 = quantity - done7;
                //System.out.print(quantity);
                if (diff7 > 0 && !Cell.getC3()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_3",P7);
                    Cell_3_MBot_Op_Time+=15;
                    Cell_3_MBot_Counter++;
                    Thread.sleep(1000);
                    done7++;
                }
            }*/
            if (OPC_UA_Reader.get_W1_P8() > 0){
                diff7 = quantity - done7;
                //System.out.print(quantity);
                if (diff7 > 0 && !Cell.getC6()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_6",P7);
                    Cell_6_MBot_Op_Time+=15;
                    Cell_6_MBot_Counter++;
                    Thread.sleep(1000);
                    done7++;
                }
            }
            if(OPC_UA_Reader.get_W2_P7()==quantity){
                //Deliver(piece, quantity);
                return false;
            }
            return true;
        }
        else if (piece == 9) {
            Thread.sleep(1000);
            if(OPC_UA_Reader.get_W2_P9()>=quantity){
                //Deliver(piece, quantity);
                return false;
            }
            if (first_p9) {
                actual_quantity=quantity-OPC_UA_Reader.get_W2_P9();
                first_p9=false;
            }
            if (OPC_UA_Reader.get_W1_P2() >= actual_quantity || flag8) {
                flag8=true;
                diff8 = actual_quantity - done8;
                if (diff8 != 0 && !Cell.getC4()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_4", P8);
                    Cell_4_MBot_Op_Time+=45;
                    Cell_4_MBot_Counter++;
                    done8++;
                    diff8 = actual_quantity - done8; // Update diff4 after done4 is incremented
                    //System.out.println(quantity);
                    //System.out.println("done change 1 in 8");
                } else if (diff8 != 0 && !Cell.getC5()) {
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_5", P8);
                    Cell_5_MBot_Op_Time+=45;
                    Cell_5_MBot_Counter++;
                    done8++;
                    diff8 = actual_quantity - done8; // Update diff4 after done4 is incremented
                    //System.out.println("done change 2 in 8");
                }
            }
            if (OPC_UA_Reader.get_W1_P8() > 0){
                diff9 = actual_quantity - done9;
                //System.out.print(quantity);
                if (diff9 > 0 && !Cell.getC5() && (actual_quantity-done8)==0){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_5",P9);
                    Cell_5_Mtop_Op_Time+=45;
                    Cell_5_Mtop_Counter++;
                    done9++;
                    Thread.sleep(1000);
                } else if (diff9 > 0 && !Cell.getC6()){
                    OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_6",P9);
                    Cell_6_Mtop_Op_Time+=45;
                    Cell_6_Mtop_Counter++;
                    done9++;
                    diff9 = actual_quantity - done9;
                    Thread.sleep(1000);
                }
            }
            return true;
        }
        return true;
    }
    public void resetDone(){
        done4=0;
        done5=0;
        done6=0;
        done7=0;
        done8=0;
        done9=0;
        flag6=false;
        flag4=false;
        flag7=false;
        flag8=false;
        flag9=false;
        first_p9=true;
    }

    public void AddP1(int quantity, int raw_price) throws Exception {
        Variant Num_P1_to_add = new Variant((short) + quantity);
        if (OPC_UA_Reader.get_Add_P1() == 0) {
            OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Add_P1", Num_P1_to_add);
        }
        while(OPC_UA_Reader.get_Add_P1() != 0){
            System.out.println("Wait to add P1");
        }

        synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
            for (int i = 0; i < quantity; i++) {
                Piece p = new Piece(1, 1, raw_price, (int) db.getDay());
                Warehouse_Control.getW1().addPiece(p);
            }
        }
    }
    public void AddP2(int quantity, int raw_price) throws Exception {
        Variant Num_P2_to_add = new Variant((short) + quantity);
        if (OPC_UA_Reader.get_Add_P2() == 0) {
            OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Add_P2", Num_P2_to_add);
        }
        while(OPC_UA_Reader.get_Add_P2() != 0){
            System.out.println("Wait to add P2");
        }

        synchronized (Warehouse_Control.getW1().getWarehouse_pieces()) {
            for (int i = 0; i < quantity; i++) {
                Piece p = new Piece(2, 2, raw_price, (int) db.getDay());
                Warehouse_Control.getW1().addPiece(p);
            }
        }
    }
    public void Clear(boolean indicator) throws Exception {
        if (indicator){
            OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Clear", new Variant(true));
        }
        if (!indicator){
            OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Clear", new Variant(false));
        }
    }
    public void Deliver(Order production_order, VariableDisplay VDisplay) throws Exception {
        System.out.println("Delivering...");
        int piece = Integer.parseInt(production_order.getWorkpiece().replaceAll("\\D+",""));
        int quantity = production_order.getQuantity();
        if(piece==5) {
            Variant Deliver_P5 = new Variant((short) +quantity);
            if (OPC_UA_Reader.get_Deliver_P5() == 0) {
                OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P5", Deliver_P5);
                P5_Unloaded = P5_Unloaded + quantity;
            }
        }
        if(piece==6) {
            Variant Deliver_P6 = new Variant((short) +quantity);
            if (OPC_UA_Reader.get_Deliver_P6() == 0) {
                OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P6", Deliver_P6);
                P6_Unloaded = P6_Unloaded + quantity;
            }
        }
        if(piece==7) {
            Variant Deliver_P7 = new Variant((short) +quantity);
            if (OPC_UA_Reader.get_Deliver_P7() == 0) {
                OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P7", Deliver_P7);
                P7_Unloaded = P7_Unloaded + quantity;
                System.out.println(P7_Unloaded);
            }
        }
        if(piece==9) {
            Variant Deliver_P9 = new Variant((short) +quantity);
            if (OPC_UA_Reader.get_Deliver_P9() == 0) {
                OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.Deliver_P9", Deliver_P9);
                P9_Unloaded = P9_Unloaded + quantity;
            }
        }
        int[] updatedValues = {
                piece, quantity, quantity, 0,
                Get_P5_Unloaded(), Get_P6_Unloaded(), Get_P7_Unloaded(), Get_P9_Unloaded(),
                Get_Cell_1_Mtop_Op_Time(), Get_Cell_1_MBot_Op_Time(), Get_Cell_2_Mtop_Op_Time(), Get_Cell_2_MBot_Op_Time(), Get_Cell_3_Mtop_Op_Time(), Get_Cell_3_MBot_Op_Time(), Get_Cell_4_Mtop_Op_Time(),Get_Cell_4_MBot_Op_Time(), Get_Cell_5_Mtop_Op_Time(), Get_Cell_5_MBot_Op_Time(),
                Get_Cell_6_Mtop_Op_Time(), Get_Cell_6_MBot_Op_Time(), Get_Cell_1_Mtop_Counter(), Get_Cell_1_MBot_Counter(), Get_Cell_2_Mtop_Counter(), Get_Cell_2_MBot_Counter(), Get_Cell_3_Mtop_Counter(), Get_Cell_3_MBot_Counter(),
                Get_Cell_4_Mtop_Counter(), Get_Cell_4_MBot_Counter(), Get_Cell_5_Mtop_Counter(), Get_Cell_5_MBot_Counter(),Get_Cell_6_Mtop_Counter(), Get_Cell_6_MBot_Counter()
        };
        VDisplay.updateValues(updatedValues);
    }
    public void Produce(Order production_order, VariableDisplay VDisplay) throws Exception {
        if((production_order.getQuantity()>(OPC_UA_Reader.get_W1_P2()+OPC_UA_Reader.get_W2_P9())) && (production_order.getWorkpiece().equals("P9"))) {
            while(OPC_UA_Reader.get_Add_P2()==0){
                System.out.println("WAIT START ADD P2");
                Thread.sleep(500);
            }
            while(OPC_UA_Reader.get_Add_P2()!=0){
                System.out.println("WAIT ADD P2");
                Thread.sleep(500);
            }
        }
        if(production_order.getQuantity()>OPC_UA_Reader.get_W1_P2() && (production_order.getWorkpiece().equals("P7"))) {
            while(OPC_UA_Reader.get_Add_P2()==0){
                System.out.println("WAIT START ADD P2");
                Thread.sleep(500);
            }
            while(OPC_UA_Reader.get_Add_P2()!=0){
                System.out.println("WAIT ADD P2");
                Thread.sleep(500);
            }
        }
        if(production_order.getQuantity()>OPC_UA_Reader.get_W1_P1() && (production_order.getWorkpiece().equals("P6"))) {
            while(OPC_UA_Reader.get_Add_P1()==0){
                System.out.println("WAIT START ADD P1");
                Thread.sleep(500);
            }
            while(OPC_UA_Reader.get_Add_P1()!=0){
                System.out.println("WAIT ADD P1");
                Thread.sleep(500);
            }
        }
        if(production_order.getQuantity()>OPC_UA_Reader.get_W1_P1() && (production_order.getWorkpiece().equals("P5"))) {
            while(OPC_UA_Reader.get_Add_P1()==0){
                System.out.println("WAIT START ADD P1");
                Thread.sleep(500);
            }
            while(OPC_UA_Reader.get_Add_P1()!=0){
                System.out.println("WAIT ADD P1");
                Thread.sleep(500);
            }
        }
        //Thread.sleep(3000);
        System.out.println("Order Production Started");
        int piece = Integer.parseInt(production_order.getWorkpiece().replaceAll("\\D+",""));
        int quantity = production_order.getQuantity();

        resetDone();
        boolean state = true;
        //check if set_of_pieces is ok
        while (state) {
            state = Select_piece_to_produce(piece, quantity);
            int alreadyProduced = 0;
            if (piece == 5) {
                alreadyProduced = OPC_UA_Reader.get_W2_P5();
            }
            if (piece == 6) {
                alreadyProduced = OPC_UA_Reader.get_W2_P6();
            }
            if (piece == 7) {
                alreadyProduced = OPC_UA_Reader.get_W2_P7();
            }
            if (piece == 9) {
                alreadyProduced = OPC_UA_Reader.get_W2_P9();
            }

            int[] updatedValues = {
                    piece, quantity, alreadyProduced, 0,
                    Get_P5_Unloaded(), Get_P6_Unloaded(), Get_P7_Unloaded(), Get_P9_Unloaded(),
                    Get_Cell_1_Mtop_Op_Time(), Get_Cell_1_MBot_Op_Time(), Get_Cell_2_Mtop_Op_Time(), Get_Cell_2_MBot_Op_Time(), Get_Cell_3_Mtop_Op_Time(), Get_Cell_3_MBot_Op_Time(), Get_Cell_4_Mtop_Op_Time(), Get_Cell_4_MBot_Op_Time(), Get_Cell_5_Mtop_Op_Time(), Get_Cell_5_MBot_Op_Time(),
                    Get_Cell_6_Mtop_Op_Time(), Get_Cell_6_MBot_Op_Time(), Get_Cell_1_Mtop_Counter(), Get_Cell_1_MBot_Counter(), Get_Cell_2_Mtop_Counter(), Get_Cell_2_MBot_Counter(), Get_Cell_3_Mtop_Counter(), Get_Cell_3_MBot_Counter(),
                    Get_Cell_4_Mtop_Counter(), Get_Cell_4_MBot_Counter(), Get_Cell_5_Mtop_Counter(), Get_Cell_5_MBot_Counter(), Get_Cell_6_Mtop_Counter(), Get_Cell_6_MBot_Counter()
            };
            VDisplay.updateValues(updatedValues);
        }
        if (piece == 5) {
            for (Piece p : production_order.getSetOfPieces()) {
                p.addTotalProductionTime(85);
            }
        } else if (piece == 6) {
            for (Piece p : production_order.getSetOfPieces()) {
                p.addTotalProductionTime(85);
            }
        } else if (piece == 7) {
            for (Piece p : production_order.getSetOfPieces()) {
                p.addTotalProductionTime(60);
            }
        } else if (piece == 9) {
            for (Piece p : production_order.getSetOfPieces()) {
                p.addTotalProductionTime(90);
            }
        }

        production_order.setAll_done();
        System.out.println("Order Production Done");
    }

    public int Get_Cell_1_Mtop_Op_Time(){
        return Cell_1_Mtop_Op_Time;
    }
    public int Get_Cell_2_Mtop_Op_Time(){
        return Cell_2_Mtop_Op_Time;
    }
    public int Get_Cell_3_Mtop_Op_Time(){
        return Cell_3_Mtop_Op_Time;
    }
    public int Get_Cell_4_Mtop_Op_Time(){
        return Cell_4_Mtop_Op_Time;
    }
    public int Get_Cell_5_Mtop_Op_Time(){
        return Cell_5_Mtop_Op_Time;
    }
    public int Get_Cell_6_Mtop_Op_Time(){
        return Cell_6_Mtop_Op_Time;
    }

    public int Get_Cell_1_MBot_Op_Time(){
        return Cell_1_MBot_Op_Time;
    }
    public int Get_Cell_2_MBot_Op_Time(){
        return Cell_2_MBot_Op_Time;
    }
    public int Get_Cell_3_MBot_Op_Time(){
        return Cell_3_MBot_Op_Time;
    }
    public int Get_Cell_4_MBot_Op_Time(){
        return Cell_4_MBot_Op_Time;
    }
    public int Get_Cell_5_MBot_Op_Time(){
        return Cell_5_MBot_Op_Time;
    }
    public int Get_Cell_6_MBot_Op_Time(){
        return Cell_6_MBot_Op_Time;
    }
    public int Get_Cell_1_Mtop_Counter(){
        return Cell_1_Mtop_Counter;
    }
    public int Get_Cell_2_Mtop_Counter(){
        return Cell_2_Mtop_Counter;
    }
    public int Get_Cell_3_Mtop_Counter(){
        return Cell_3_Mtop_Counter;
    }
    public int Get_Cell_4_Mtop_Counter(){
        return Cell_4_Mtop_Counter;
    }
    public int Get_Cell_5_Mtop_Counter(){
        return Cell_5_Mtop_Counter;
    }
    public int Get_Cell_6_Mtop_Counter(){
        return Cell_6_Mtop_Counter;
    }
    public int Get_Cell_1_MBot_Counter(){
         return Cell_1_MBot_Counter;
    }
    public int Get_Cell_2_MBot_Counter(){
        return Cell_2_MBot_Counter;
    }
    public int Get_Cell_3_MBot_Counter(){
        return Cell_3_MBot_Counter;
    }
    public int Get_Cell_4_MBot_Counter(){
        return Cell_4_MBot_Counter;
    }
    public int Get_Cell_5_MBot_Counter(){
        return Cell_5_MBot_Counter;
    }
    public int Get_Cell_6_MBot_Counter(){
        return Cell_6_MBot_Counter;
    }

    public int Get_P5_Unloaded() { return P5_Unloaded; }
    public int Get_P6_Unloaded() { return P6_Unloaded; }
    public int Get_P7_Unloaded() { return P7_Unloaded; }
    public int Get_P9_Unloaded() { return P9_Unloaded; }

    public static void main(String[] args) throws Exception {
        OPC_UA_Client client = new OPC_UA_Client("opc.tcp://localhost:4840");

        Thread startOPCUA = new Thread(new OPC_UA_Reader());
        startOPCUA.start();

        Thread warehouses = new Thread(new Warehouse_Control());
        warehouses.start();

        Thread startCell = new Thread(new Cell());
        startCell.start();

        Logic logic = new Logic();
        Thread.sleep(5000);

        //logic.AddP2(10);

        //logic.Produce(7, 5, );
        //logic.Deliver(7, 5);
    }
}