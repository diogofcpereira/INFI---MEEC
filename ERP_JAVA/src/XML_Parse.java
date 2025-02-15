import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class XML_Parse {

    List<Order> parse_entry(String filepath, long currentDate) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        String Cname = "";
        int Onumber = 0;
        String Oworkpiece = "";
        int Oquantity = 0;
        int Oduedate = 0;
        int Olatepen = 0;
        int Oearlypen = 0;
        List<Order> entry_orders_array = new ArrayList<>();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Document document = builder.parse(new File(filepath));
            Document document = builder.parse(new InputSource(new StringReader(filepath)));
            //change two lines above to alternate between udp server of direct filepath

            document.getDocumentElement().normalize();

            NodeList documentList = document.getElementsByTagName("DOCUMENT");

            for(int i = 0; i < documentList.getLength(); i++) {
                Node doc = documentList.item(i);

                if(doc.getNodeType() == Node.ELEMENT_NODE) {
                    //NodeList documentDetails = doc.getChildNodes();

                    Element docElement = (Element) doc;

                    NodeList clientList = docElement.getElementsByTagName("Client");

                    for(int size = 0; size < clientList.getLength(); size++) {
                        Node clientNode = clientList.item(size);
                        if(clientNode != null && clientNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element nameID = (Element) clientNode;
                            //System.out.println(nameID.getTagName() + ": " + nameID.getAttribute("NameId"));
                            Cname = nameID.getAttribute("NameId");
                        }
                    }

                    NodeList orderList = docElement.getElementsByTagName("Order");

                    for(int j = 0; j < orderList.getLength(); j++) {
                        Node orderNode = orderList.item(j);
                        if(orderNode != null && orderNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element orderElement = (Element) orderNode;
                            //System.out.println("Order number: " + (j+1));
                            //System.out.println("   " + "Number" + ": " + Integer.parseInt(orderElement.getAttribute("Number")));
                            Onumber = Integer.parseInt(orderElement.getAttribute("Number"));
                            //System.out.println("   " + "WorkPiece" + ": " + orderElement.getAttribute("WorkPiece"));
                            Oworkpiece = orderElement.getAttribute("WorkPiece");
                            //System.out.println("   " + "Quantity" + ": " + Integer.parseInt(orderElement.getAttribute("Quantity")));
                            Oquantity = Integer.parseInt(orderElement.getAttribute("Quantity"));
                            //System.out.println("   " + "DueDate" + ": " + Integer.parseInt(orderElement.getAttribute("DueDate")));
                            Oduedate = Integer.parseInt(orderElement.getAttribute("DueDate"));
                            //System.out.println("   " + "LatePen" + ": " + Integer.parseInt(orderElement.getAttribute("LatePen")));
                            Olatepen = Integer.parseInt(orderElement.getAttribute("LatePen"));
                            //System.out.println("   " + "EarlyPen" + ": " + Integer.parseInt(orderElement.getAttribute("EarlyPen")));
                            Oearlypen = Integer.parseInt(orderElement.getAttribute("EarlyPen"));
                            Order order_entry = new Order(Cname, Onumber, Oworkpiece, Oquantity, Oduedate, Olatepen, Oearlypen, currentDate);
                            entry_orders_array.add(order_entry);
                        }
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Failed to parse XML");
            throw new RuntimeException(e);
        }

        return entry_orders_array;
    }
}
