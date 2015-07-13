package cleancoderscom.socketserver;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Assert;
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
  private int port = 8042;

  public static abstract class TestSocketService implements SocketService {
    public boolean waiting = false;

    public void serve(Socket s) {
      try {
        doService(s);
        while(!waiting)
          Thread.yield();

        synchronized(this) {
          notify();
          waiting = false;
        }
        s.close();
      } catch(Exception e) {
        e.printStackTrace();
      }
    }


    protected abstract void doService(Socket s) throws IOException;

    public void waitForServiceToComplete() throws InterruptedException {
      synchronized(this) {
        waiting = true;
        wait();
      }
    }
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
      service.waitForServiceToComplete();
      server.stop();

      assertEquals(1, service.connections);
    }

    @Test
    public void acceptsMultipleIncomingConnections() throws Exception {
      // TODO - MDM - Possible Race Condition?  Have seen hanging test.
      server.start();
      new Socket("localhost", port);
      service.waitForServiceToComplete();

      new Socket("localhost", port);
      service.waitForServiceToComplete();
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
      // TODO - MDM - Possible Race Condition?  Have seen hanging test.
      server.start();
      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("hello\n".getBytes());
      readingService.waitForServiceToComplete();
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
      // TODO - MDM - Possible Race Condition?  Have seen hanging test.
      server.start();
      Socket s = new Socket("localhost", port);
      OutputStream os = s.getOutputStream();
      os.write("echo\n".getBytes());
      echoService.waitForServiceToComplete();
      InputStream is = s.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String response = br.readLine();
      assertEquals("echo", response);
    }
  }


}
