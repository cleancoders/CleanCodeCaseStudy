package cleancoderscom.tests.socketserver;

import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.socketserver.SocketService;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class SocketServerTest {
  private ClosingSocketService service;
  private SocketServer server;
  private int port;

  @Before
  public void setup() {
    port = 8042;
  }

  public static abstract class TestSocketService implements SocketService {
    public void serve(Socket s) {
      try {
        doService(s);
        synchronized(this) { notify(); }
        s.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

    protected abstract void doService(Socket s) throws IOException;
  }

  public static class ClosingSocketService extends TestSocketService {
    public int connections;

    protected void doService(Socket s) throws IOException {
      connections++;
    }
  }

  public class WithClosingSocketService {
    @Before
    public void setUp() throws Exception {
      service = new ClosingSocketService();
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
      // TODO - MDM - Possible Race Condition?  Have seen hanging test.
      server.start();
      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }
      server.stop();

      assertEquals(1, service.connections);
    }

    @Test
    public void acceptsMultipleIncomingConnections() throws Exception {
      server.start();
      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }
      new Socket("localhost", port);
      synchronized(service) {
        service.wait();
      }
      server.stop();

      assertEquals(2, service.connections);
    }
  }

  public static class ReadingSocketService extends TestSocketService {
    public String message;

    protected void doService(Socket s) throws IOException {
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      message = br.readLine();
    }
  }

  public class WithReadingSocketService {
    private ReadingSocketService readingService;

    @Before
    public void setup() throws Exception {
      readingService = new ReadingSocketService();
      server = new SocketServer(port, readingService);
    }

    @After
    public void tearDown() throws Exception {
      server.stop();
    }

    @Test
    public void canSendAndReceiveData() throws Exception {
      server.start();
      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("hello\n".getBytes());
      synchronized(readingService) {
        readingService.wait();
      }
      server.stop();

      assertEquals("hello", readingService.message);
    }
  }

  public static class EchoSocketService extends TestSocketService {

    protected void doService(Socket s) throws IOException {
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String message = br.readLine();
      OutputStream os = s.getOutputStream();
      os.write(message.getBytes());
      os.flush();
    }
  }

  public class WithEchoSocketService {
    private EchoSocketService echoService;

    @Before
    public void setup() throws Exception {
      echoService = new EchoSocketService();
      server = new SocketServer(port, echoService);
    }

    @After
    public void tearDown() throws Exception {
      server.stop();
    }

    @Test
    public void canEcho() throws Exception {
      server.start();
      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("echo\n".getBytes());
      synchronized(echoService) {
        echoService.wait();
      }
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String response = br.readLine();
      assertEquals("echo", response);
    }
  }


}
