package cleancoderscom.utilities;

import cleancoderscom.http.ParsedRequest;
import cleancoderscom.http.RequestParser;
import cleancoderscom.http.Router;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesController;
import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.TestSetup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

  public static void main(String[] args) throws Exception {
    Router router = new Router();
    router.addPath("", new CodecastSummariesController(null, null));
    // TODO - Add this Controller
//    router.addPath("episode", new CodecastDetailsController());


    TestSetup.setupSampleData();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ParsedRequest request = new RequestParser().parse(reader.readLine());
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
