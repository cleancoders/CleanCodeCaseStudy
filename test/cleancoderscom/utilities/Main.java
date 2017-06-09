package cleancoderscom.utilities;

import cleancoderscom.http.ParsedRequest;
import cleancoderscom.http.Router;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesController;
import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.TestSetup;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesPresenter;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

  public static void main(String[] args) throws Exception {
    Router router = new Router();
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    CodecastSummariesPresenter presenter = new CodecastSummariesPresenter();
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
