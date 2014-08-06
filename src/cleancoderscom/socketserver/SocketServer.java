package cleancoderscom.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
  private final int port;
  private final SocketService service;
  private boolean running;

  public SocketServer(int port, SocketService service) {
    this.port = port;
    this.service = service;
  }

  public int getPort() {
    return port;
  }

  public SocketService getService() {
    return service;
  }

  public void start() throws Exception {
    ServerSocket ss = new ServerSocket(port);
    run(ss);
    running = true;
  }

  private void run(ServerSocket ss) throws IOException {
    Socket serviceSocket = ss.accept();
  }

  public boolean isRunning() {
    return running;
  }

  public void stop() {
    running = false;
  }
}
