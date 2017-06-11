package cleancoderscom.utilities;

import cleancoderscom.TestSetup;
import cleancoderscom.adapters.server.SocketService;
import cleancoderscom.delivery.mvc.CodecastSummariesMvcController;
import cleancoderscom.delivery.mvc.CodecastSummariesViewImpl;
import cleancoderscom.delivery.mvc.ParsedRequest;
import cleancoderscom.delivery.mvc.Presenter;
import cleancoderscom.delivery.mvc.Router;
import cleancoderscom.delivery.socketserver.SocketServer;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;


public class Main {

  public static void main(String[] args) throws Exception {
    TestSetup.setupSampleData();

    Router router = new Router();
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    Presenter presenter = new Presenter();
    CodecastSummariesViewImpl view = new CodecastSummariesViewImpl();
    CodecastSummariesMvcController controller = new CodecastSummariesMvcController(useCase, presenter, view);
    router.addPath("", controller);
    // TODO - Add this Controller
//    router.addPath("episode", new CodecastDetailsController());

    SocketServer server = new SocketServer(8888, new AppService(router));
    server.start();
  }

  private static void writeHttpResponse(OutputStream os, String response) throws IOException {
    long length = response.length();
    response = String.format("HTTP/1.1 200 OK\nContent-Length: %d\n\n%s", length, response);
    os.write(response.getBytes());
  }

  static class AppService implements SocketService {

    private final Router router;

    AppService(Router router) {
      this.router = router;
    }

    @Override
    public synchronized void serve(Socket s) {
      try (
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        OutputStream os = s.getOutputStream()) {

        String requestString = reader.readLine();
        ParsedRequest request = ParsedRequest.fromRequestString(requestString);

        String response = router.route(request);
        writeHttpResponse(os, response);
      } catch (IOException e) {
        e.printStackTrace();
      } finally {
        try {
          s.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}
