package brown.system.kryoserver.library;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import brown.mechanism.messages.library.ErrorMessage;
import brown.mechanism.messages.library.RegistrationMessage;
import brown.mechanism.messages.library.StringMessage;
import brown.system.client.library.TPClient;
import brown.system.kryoserver.library.KryoServer;
import brown.system.setup.ISetup;
import brown.system.setup.library.SimpleSetup;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentHashMap;

public class KryoServerTest {
  
  
  private class TestClient extends TPClient {
    public String string;
    public String error;

    private TestClient(String host, Integer port, ISetup setup) {

        super(host, port, setup);
        CLIENT.addListener(new Listener() {
            public void received(Connection connection, Object message) {
                if (message instanceof RegistrationMessage) {
                    onRegistration((RegistrationMessage) message);
                } else if (message instanceof ErrorMessage) {
                    error = ((ErrorMessage) message).error;
                    onErrorMessage((ErrorMessage) message);
                } else if (message instanceof StringMessage) {
                    string = ((StringMessage) message).message;
                }
            }
        });
        CLIENT.sendTCP(new RegistrationMessage(-1, "testing"));
        CLIENT.sendTCP("testing_one"); 
        CLIENT.sendTCP(1010); 
        CLIENT.sendTCP(false); 
    }
  }

  private class TestServer extends KryoServer {
    public String receivedString; 
    public Boolean receivedBoolean; 
    public Integer receivedInteger; 
    public RegistrationMessage receivedMessage; 
  
      public TestServer(int port, ISetup setup) {
          super(port, setup);
              kryoServer.addListener(new Listener() {
              public void received(Connection connection, Object message) {
              if (message instanceof RegistrationMessage) {
                receivedMessage = (RegistrationMessage) message; 
                      Integer theID = 1;
                      connection.sendTCP(15000);
                      connection.setTimeout(60000);
                      kryoServer.sendToTCP(connection.getID(), new RegistrationMessage(theID));
                      kryoServer.sendToTCP(connection.getID(), new ErrorMessage(0, "test error"));
                      kryoServer.sendToTCP(connection.getID(), new StringMessage(0, "test string"));
              } else if (message instanceof String) {
                receivedString = (String) message; 
              } else if (message instanceof Boolean) {
                receivedBoolean = (Boolean) message; 
              } else if (message instanceof Integer) {
                receivedInteger = (Integer) message; 
              }}});
    }
  }
  
  @Test
  public void testConstructor() {
    TestServer testServer = new TestServer(2121, new SimpleSetup()); 
    assertTrue(testServer.PORT == 2121);
    assertEquals(testServer.connections, new ConcurrentHashMap<Connection, Integer>()); 
    assertTrue(testServer != null); 
  }
  
  @Test
  public void testReceiveMessage() throws InterruptedException {
    TestServer testServer = new TestServer(2122, new SimpleSetup()); 
    TestClient testClient = new TestClient("localhost", 2122, new SimpleSetup());
    Thread.sleep(1000);
    assertEquals(testServer.receivedString, "testing_one"); 
    assertEquals(testServer.receivedBoolean, false); 
    assertTrue(testServer.receivedInteger == 1010); 
    assertEquals(testServer.receivedMessage, new RegistrationMessage(-1, "testing"));
  }
  
  @Test public void testSendMessage() throws InterruptedException {
    TestServer testServer = new TestServer(2123, new SimpleSetup());
    Thread.sleep(1000);
    TestClient testClient = new TestClient("localhost", 2123, new SimpleSetup());
    Thread.sleep(1000);
    assertTrue(testClient.ID ==  1);
    assertEquals((testClient).error, "test error");
    assertEquals((testClient).string, "test string");
  }

  public static void main(String[] args) throws InterruptedException {
    KryoServerTest kTest = new KryoServerTest(); 
    kTest.testConstructor(); 
    kTest.testReceiveMessage();
    kTest.testSendMessage();
  }


}