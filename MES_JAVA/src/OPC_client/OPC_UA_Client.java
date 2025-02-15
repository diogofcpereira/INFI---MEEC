package OPC_client;

import ch.qos.logback.classic.util.ContextSelectorStaticBinder;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.stack.client.UaStackClient;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.StaticLoggerBinder;

public class OPC_UA_Client {
    private static OpcUaClient client;

    public OPC_UA_Client(String endpointUrl) throws Exception {
        // Create a new OPC UA client
        client = OpcUaClient.create(endpointUrl);

        // Connect to the OPC UA server
        client.connect().get();

        System.out.println("Connected");
    }

    public void disconnect() throws Exception {
        // Disconnect from the OPC UA server
        client.disconnect().get();
        System.out.println("Disconnected");
    }

    //public static Variant read(String nodeIdIdentifier) throws Exception {
        // Define the node you want to read
      //  NodeId nodeId = new NodeId(4, nodeIdIdentifier);

        // Read the current value of the node
        //DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();

        // Return the current value
        //return dataValue.getValue();
    //}

    public static Variant read(String nodeIdIdentifier) throws Exception {
        NodeId nodeId = new NodeId(4, nodeIdIdentifier);
        DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();

        assert dataValue.getStatusCode() != null;
        if (dataValue.getStatusCode().isGood()) {
            return dataValue.getValue();
        } else {
            System.err.println("Failed to read value for node: " + nodeIdIdentifier);
            return null;
        }
    }

    public static void write(String nodeIdIdentifier, Variant newValue) throws Exception {
        // Define the node you want to write
        NodeId nodeId = new NodeId(4, nodeIdIdentifier);

        // Create a new value to write to the node
        DataValue dataValue = new DataValue(newValue);

        // Write the new value to the node
        StatusCode statusCode = client.writeValue(nodeId, dataValue).get();

        if (statusCode.isGood()) {
            //System.out.println("Write successful");
        } else {
            System.err.println("Write failed: " + statusCode);
            System.err.println("NodeId: " + nodeIdIdentifier);
            System.err.println("Status Code: " + statusCode.getClass());
        }
    }

    public static void main(String[] args) throws Exception {

        System.setProperty("org.slf4j.simpleLogger.defaultLogback", "true");
        Logger logger = LoggerFactory.getLogger(OPC_UA_Client.class);
        logger.info("This message will not cause SLF4J binding messages to be printed.");

        OPC_UA_Client opc = new OPC_UA_Client("opc.tcp://localhost:4840");

        Thread.sleep(5000);

        //UShort wordValue = UShort.valueOf((short) 4);// Adjust the data type here
        Variant P4 = new Variant(UShort.valueOf((short) 4));
        OPC_UA_Client.write("|var|CODESYS Control Win V3 x64.Application.MES.AAA_Piece_tp_1", P4);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        opc.disconnect();
    }
}
