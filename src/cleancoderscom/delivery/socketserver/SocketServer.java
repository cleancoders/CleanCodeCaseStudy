package cleancoderscom.delivery.socketserver;

import cleancoderscom.adapters.server.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServer {
  private final SocketService service;
  private volatile boolean running;
  private ServerSocket serverSocket;
  private ExecutorService executor;

  public SocketServer(int port, SocketService service) throws Exception {
    this.service = service;
    this.serverSocket = new ServerSocket(port);
    this.executor = Executors.newFixedThreadPool(4);
  }

  public void start() throws Exception {
    Runnable connectionHandler = () -> {
      try {
        while(running) {
          Socket serviceSocket = serverSocket.accept();
          executor.execute(() -> service.serve(serviceSocket));
        }
      } catch(IOException e) {
        if(running)
          e.printStackTrace();
      }
    };
    executor.execute(connectionHandler);

    running = true;
  }

  public void stop() throws Exception {
    running = false;
    serverSocket.close();
    executor.shutdown();
    executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
  }
}
