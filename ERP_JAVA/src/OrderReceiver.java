import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class OrderReceiver {
    private final int PORT = 24680;
    private int BUFFER_SIZE = 1024;

    public String GetXML() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("OrderReceiver is running and listening on port " + PORT);
            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            byte[] send = new byte[BUFFER_SIZE];
            String toSend = "Send XML";
            send = toSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(send, send.length, InetAddress.getByName("127.0.0.1"), 24681);

            socket.send(sendPacket);

            socket.receive(packet);

            String receivedData = new String(packet.getData(), 0, packet.getLength());
            if (receivedData.isEmpty()) {
                System.out.println("No XML data received.");
                return null;
            }
            else {
                System.out.println("Received order:\n" + receivedData);

                /*DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(new InputSource(new StringReader(receivedData)));

                String filePath = "received_order.xml";

                File file = new File(filePath);
                if(file.exists()) {
                    file.delete();
                }

                FileWriter writer = new FileWriter(filePath);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(document), new StreamResult(writer));
                writer.close();*/

                return receivedData;
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*public static void main(String[] args) {
        OrderReceiver orderReceiver = new OrderReceiver();
        XML_Parse xmlParser = new XML_Parse();
        String s = orderReceiver.GetXML();

        List<Order> ordersList = new ArrayList<>();
        ordersList = xmlParser.parse_entry(s);

        for(Order order : ordersList) {
            System.out.println(order.toString());
        }
    }*/
}
