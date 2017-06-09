package cleancoderscom.adapters.server;

import java.net.Socket;

@FunctionalInterface
public interface SocketService {
  void serve(Socket s);
}
