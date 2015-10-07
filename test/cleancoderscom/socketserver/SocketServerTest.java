package cleancoderscom.socketserver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    Thread.sleep(10);
    Socket s = new Socket("localhost", port);
    OutputStream os = s.getOutputStream();
    os.write("echo\n".getBytes());
    String response = new BufferedReader(new InputStreamReader(s.getInputStream())).readLine();
    assertEquals("echo", response);
  }

  @Test
  public void multipleEchos() throws Exception {
    server.start();
    Thread.sleep(10);

    Socket s1 = new Socket("localhost", port);
    Socket s2 = new Socket("localhost", port);

    s1.getOutputStream().write("echo1\n".getBytes());
    s2.getOutputStream().write("echo2\n".getBytes());

    String response1 = new BufferedReader(new InputStreamReader(s1.getInputStream())).readLine();
    String response2 = new BufferedReader(new InputStreamReader(s2.getInputStream())).readLine();

    assertEquals("echo1", response1);
    assertEquals("echo2", response2);
  }
}


