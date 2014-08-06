package cleancoderscom.tests.socketserver;

import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.socketserver.SocketService;
import org.junit.Before;
import org.junit.Test;

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


  public static class FakeSocketService implements SocketService {
    public int connections;

    public void serve(Socket s) {
      connections++;
    }
  }
}
