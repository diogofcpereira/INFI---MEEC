
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class OrderReceiver {
    private final int PORT = 24681;
    private int BUFFER_SIZE = 1024;

    public void SendXML() {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("OrderReceiver is running and listening on port " + PORT);

            sendXMLFile("command5.xml", InetAddress.getByName("127.0.0.1"), 24680, socket);

            //byte[] buffer = new byte[BUFFER_SIZE];
            //DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            //socket.receive(packet);

            //String receivedData = new String(packet.getData(), 0, packet.getLength());
            /*if (receivedData.isEmpty()) {
                System.out.println("No XML data received.");
                //return null;
            }*/
            //else {
                //System.out.println("Received order:\n" + receivedData);

                //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                //DocumentBuilder builder = factory.newDocumentBuilder();
                //Document document = builder.parse(new InputSource(new StringReader(receivedData)));

                //String filePath = "received_order.xml";

                /*File file = new File(filePath);
                if(file.exists()) {
                    file.delete();
                }*/

                /*FileWriter writer = new FileWriter(filePath);
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.transform(new DOMSource(document), new StreamResult(writer));
                writer.close();*/

                //return receivedData;
            //}
        }

        catch (Exception e) {
            e.printStackTrace();
            //return null;
        }
    }

    /*public static void sendRequest(DatagramSocket socket, InetAddress serverAddress, int serverPort, String request) throws Exception {
        byte[] requestData = request.getBytes();
        DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, serverPort);
        socket.send(requestPacket);
    }*/

    private void sendXMLFile(String fileName, InetAddress clientAddress, int clientPort, DatagramSocket socket) throws IOException {
        File file = new File(fileName);
        if (!file.exists() || !file.isFile()) {
            System.out.println("File not found or not a regular file: " + fileName);
            return;
        }

        // Read the contents of the XML file into a byte array
        byte[] fileData = readFileToByteArray(file);

        // Send the file data in packets to the client
        int dataSize = fileData.length;
        int totalPackets = (int) Math.ceil((double) dataSize / BUFFER_SIZE);
        for (int i = 0; i < totalPackets; i++) {
            int offset = i * BUFFER_SIZE;
            int length = Math.min(BUFFER_SIZE, dataSize - offset);
            byte[] packetData = new byte[length];
            System.arraycopy(fileData, offset, packetData, 0, length);
            DatagramPacket packet = new DatagramPacket(packetData, packetData.length, clientAddress, clientPort);
            socket.send(packet);
        }

        System.out.println("File sent: " + fileName);
    }

    private byte[] readFileToByteArray(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        fis.close();
        return bos.toByteArray();
    }

    public static void main(String[] args) {
        OrderReceiver receiver = new OrderReceiver();
        receiver.SendXML();
    }
}
