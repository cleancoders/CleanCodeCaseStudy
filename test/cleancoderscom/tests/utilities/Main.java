package cleancoderscom.tests.utilities;

import cleancoderscom.Context;
import cleancoderscom.PresentCodecastUseCase;
import cleancoderscom.PresentableCodecast;
import cleancoderscom.User;
import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.tests.TestSetup;
import cleancoderscom.view.ViewTemplate;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class Main {

  public static void main(String[] args) throws Exception {
    TestSetup.setupSampleData();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        String frontPage = getFrontPage();
        String response = makeResponse(frontPage);
        s.getOutputStream().write(response.getBytes());
        s.close();
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

    PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    User bob = Context.userGateway.findUserByName("Bob");
    List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(bob);

    try {
      ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");

      StringBuilder codecastLines = new StringBuilder();
      for(PresentableCodecast presentableCodecast : presentableCodecasts) {

        ViewTemplate codecastTemplate = ViewTemplate.create("html/codecast.html");
        codecastTemplate.replace("title", presentableCodecast.title);
        codecastTemplate.replace("publicationDate", presentableCodecast.publicationDate);

        //staged
        codecastTemplate.replace("thumbnail", "https://d26o5k45lnmm4v.cloudfront.net/YmluYXJ5OjIxNzA1Nw");
        codecastTemplate.replace("author", "Uncle Bob");
        codecastTemplate.replace("duration", "58 min.");
        codecastTemplate.replace("contentActions", "Buying options go here.");

        codecastLines.append(codecastTemplate.getContent());
      }

      frontPageTemplate.replace("codecasts", codecastLines.toString());
      return frontPageTemplate.getContent();
    } catch(IOException e) {
      e.printStackTrace();
      return "Gunk";
    }
  }

}
