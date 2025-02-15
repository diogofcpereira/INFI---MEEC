import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSocketConnection {
    private String IP_Address = "172.22.130.65";  //"127.0.0.1";
    private int PORT = 8080;
    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;

    public TCPSocketConnection() {

    }

    public boolean openConnection() {
        try {
            socket = new Socket(IP_Address, PORT);

            InputStream input = socket.getInputStream();
            OutputStream output = socket.getOutputStream();
            reader = new BufferedReader(new InputStreamReader(input));
            writer = new PrintWriter(output, true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String message) {
        if (writer != null) {
            writer.println(message);
        }
    }

    public void set_SA_P1(int quantity) {
        String message = "A1:" + quantity;
        sendMessage(message);
    }
    
    public void set_SA_P2(int quantity) {
        String message = "A2:" + quantity;
        sendMessage(message);
    }
    
    public void set_SB_P1(int quantity) {
        String message = "B1:" + quantity;
        sendMessage(message);
    }
    
    public void set_SB_P2(int quantity) {
        String message = "B2:" + quantity;
        sendMessage(message);
    }
    
    public void set_SC_P1(int quantity) {
        String message = "C1:" + quantity;
        System.out.println("Supplier send 1");
        sendMessage(message);
    }
    
    public void set_SC_P2(int quantity) {
        String message = "C2:" + quantity;
        System.out.println("Supplier send 2");
        sendMessage(message);
    }

    public String receiveMessage() {
        String message = null;
        try {
            if (reader != null) {
                message = reader.readLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TCPSocketConnection tcp = new TCPSocketConnection();

        tcp.openConnection();

        tcp.set_SA_P1(5);

        tcp.close();
    }
}
