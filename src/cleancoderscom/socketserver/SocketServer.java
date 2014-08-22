package cleancoderscom.socketserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SocketServer {
  private final int port;
  private final SocketService service;
  private boolean running;
  private ServerSocket serverSocket;
  private ExecutorService executor;

  public SocketServer(int port, SocketService service) throws Exception {
    this.port = port;
    this.service = service;
    serverSocket = new ServerSocket(port);
    executor = Executors.newFixedThreadPool(4);
  }

  public int getPort() {
    return port;
  }

  public SocketService getService() {
    return service;
  }

  public void start() throws Exception {
    Runnable connectionHandler = new Runnable() {
      public void run() {
        try {
          while(running) {
            Socket serviceSocket = serverSocket.accept();
            executor.execute(new Runnable() {
              @Override
              public void run() {
                service.serve(serviceSocket);
              }
            });
          }
        } catch(IOException e) {
          if(running)
            e.printStackTrace();
        }
      }
    };
    executor.execute(connectionHandler);

    running = true;
  }

  public boolean isRunning() {
    return running;
  }

  public void stop() throws Exception {
    running = false;
    serverSocket.close();
    executor.shutdown();
    executor.awaitTermination(1000, TimeUnit.MILLISECONDS);
  }
}
