package cleancoderscom.socketserver;

import java.net.Socket;

public interface SocketService {
  void serve(Socket s);
}
