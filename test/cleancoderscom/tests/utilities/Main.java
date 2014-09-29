package cleancoderscom.tests.utilities;

import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.tests.TestSetup;
import java.io.IOException;


public class Main {

  public static void main(String[] args) throws Exception {
    TestSetup.setupContext();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        String frontPage = getFrontPage();
        String response = makeResponse(frontPage);
        s.getOutputStream().write(response.getBytes());
      } catch(IOException e) {
        e.printStackTrace();
      }
    });
    server.start();
  }

  private static String makeResponse(String content) {
    return "HTTP/1.1 200 OK\n" +
        "Content-Length: " + content.length() + "\n" +

        "\n" +
        content;
  }

  public static String getFrontPage() {
    return "Gunk";
  }
}
