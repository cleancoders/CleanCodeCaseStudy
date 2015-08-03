package cleancoderscom.socketserver;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.*;
import java.net.Socket;

import static org.junit.Assert.*;

public class SocketServerTest {
  private SocketServer server;
  private EchoSocketService echoService;
  private int port;

  @Before
  public void setup() throws Exception {
    port = 8042;
    echoService = new EchoSocketService();
    server = new SocketServer(port, echoService);
  }

  @After
  public void tearDown() throws Exception {
    server.stop();
  }

  public static abstract class TestSocketService implements SocketService {
    public void serve(Socket s) {
      try {
        doService(s);
        synchronized(this) {
          notify();
        }
        s.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

    protected abstract void doService(Socket s) throws IOException;
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

  @Test
  public void canEcho() throws Exception {
    server.start();
    Thread.yield();
    Socket s = new Socket("localhost", port);
    OutputStream os = s.getOutputStream();
    os.write("echo\n".getBytes());
    InputStream is = s.getInputStream();
    InputStreamReader isr = new InputStreamReader(is);
    BufferedReader br = new BufferedReader(isr);
    String response = br.readLine();
    assertEquals("echo", response);
  }

  @Test
  public void multipleEchos() throws Exception
  {
    
  }
}


