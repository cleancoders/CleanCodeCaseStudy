package cleancoderscom.utilities;

import cleancoderscom.delivery.mvc.ParsedRequest;
import cleancoderscom.delivery.mvc.Router;
import cleancoderscom.delivery.mvc.CodecastSummariesController;
import cleancoderscom.delivery.mvc.Presenter;
import cleancoderscom.delivery.socketserver.SocketServer;
import cleancoderscom.TestSetup;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;
import cleancoderscom.delivery.mvc.CodecastSummariesViewImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

  public static void main(String[] args) throws Exception {
    Router router = new Router();
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    Presenter presenter = new Presenter();
    CodecastSummariesViewImpl view = new CodecastSummariesViewImpl();
    router.addPath("", new CodecastSummariesController(useCase, presenter, view));
    // TODO - Add this Controller
//    router.addPath("episode", new CodecastDetailsController());


    TestSetup.setupSampleData();
    SocketServer server = new SocketServer(8888, s -> {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ParsedRequest request = ParsedRequest.fromRequestString(reader.readLine());
        String response = router.route(request);

        s.getOutputStream().write(response.getBytes());

        s.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    });
    server.start();
  }


}
