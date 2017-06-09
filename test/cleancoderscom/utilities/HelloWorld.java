package cleancoderscom.utilities;

import cleancoderscom.delivery.socketserver.SocketServer;
import cleancoderscom.adapters.server.SocketService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class HelloWorld implements SocketService {
  public static void main(String[] args) throws Exception {
    SocketServer server = new SocketServer(8080, new HelloWorld());
    server.start();
  }

  @Override
  public void serve(Socket s) {
    try {
      OutputStream os = s.getOutputStream();

      String response = "HTTP/1.1 200 OK\n" +
          "Content-Length: 21\n" +
          "\n" +
          "<h1>Hello, world</h1>";

      os.write(response.getBytes());
    } catch(IOException e) {
      e.printStackTrace();
    }
  }
}
