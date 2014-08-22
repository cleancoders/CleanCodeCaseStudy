package cleancoderscom.tests.socketserver;

import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.socketserver.SocketService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.net.Socket;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class SocketServerTest {
  private FakeSocketService service;
  private SocketServer server;
  private int port;

  @Before
  public void setUp() throws Exception {
    service = new FakeSocketService();
    port = 8042;
    server = new SocketServer(port, service);
  }

  @After
  public void tearDown() throws Exception {
    server.stop();
  }

  @Test
  public void instantiate() throws Exception {
    assertEquals(port, server.getPort());
    assertEquals(service, server.getService());
  }

  @Test
  public void canStartAndStopServer() throws Exception {
    server.start();
    assertTrue(server.isRunning());
    server.stop();
    assertFalse(server.isRunning());
  }

  @Test
  public void acceptsAnIncomingConnection() throws Exception {
    server.start();
    new Socket("localhost", port);
    server.stop();

    assertEquals(1, service.connections);
  }

  @Test
  public void canSendAndReceiveData() throws Exception {
    server.start();
    Socket s = new Socket("localhost", port);
    OutputStream os = s.getOutputStream();
    os.write("hello\n".getBytes());
    service.readMessage();
    server.stop();

    assertEquals("hello", service.message);
  }

  public static class FakeSocketService implements SocketService {
    public int connections;
    public String message;
    Socket socket;

    public void serve(Socket s) {
      socket = s;
      connections++;
      try {
        s.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

    public void readMessage() throws IOException {
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);

      message = br.readLine();
    }
  }
}
