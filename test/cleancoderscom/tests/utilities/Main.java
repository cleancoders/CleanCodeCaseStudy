package cleancoderscom.tests.utilities;

import cleancoderscom.PresentCodecastUseCase;
import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.tests.TestSetup;
import cleancoderscom.view.ViewTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Main {

  public static void main(String[] args) throws Exception {
    TestSetup.setupContext();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        String frontPage = getFrontPage();
        String response = makeResponse(frontPage);
        s.getOutputStream().write(response.getBytes());
      } catch(IOException e) {
        e.printStackTrace();
      }
    });
    server.start();
  }

  private static String makeResponse(String content) {
    return "HTTP/1.1 200 OK\n" +
        "Content-Length: " + content.length() + "\n" +

        "\n" +
        content;
  }

  public static String getFrontPage() {

//    PresentCodecastUseCase useCase =
    try {
      ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");
      ViewTemplate codecastTemplate = ViewTemplate.create("html/codecast.html");

      codecastTemplate.replace("title", "Episode 1: The Beginning!");

      String codecastView = codecastTemplate.getContent(); // for the moment
      frontPageTemplate.replace("codecasts", codecastView);
      return frontPageTemplate.getContent();
    } catch(IOException e) {
      e.printStackTrace();
      return "Gunk";
    }
  }

}
