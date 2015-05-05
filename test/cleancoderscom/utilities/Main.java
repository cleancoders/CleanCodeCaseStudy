package cleancoderscom.utilities;

import cleancoderscom.Context;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;
import cleancoderscom.usecases.codecastSummaries.PresentableCodecastSummary;
import cleancoderscom.entities.User;
import cleancoderscom.socketserver.SocketServer;
import cleancoderscom.TestSetup;
import cleancoderscom.view.ViewTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public class Main {

  public static void main(String[] args) throws Exception {
    TestSetup.setupSampleData();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println(reader.readLine());
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

    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    User bob = Context.userGateway.findUserByName("Bob");
    List<PresentableCodecastSummary> presentableCodecasts = useCase.presentCodecasts(bob);

    try {
      ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");

      StringBuilder codecastLines = new StringBuilder();
      for(PresentableCodecastSummary presentableCodecast : presentableCodecasts) {

        ViewTemplate codecastTemplate = ViewTemplate.create("html/codecast.html");
        codecastTemplate.replace("title", presentableCodecast.title);
        codecastTemplate.replace("publicationDate", presentableCodecast.publicationDate);
        codecastTemplate.replace("permalink", presentableCodecast.permalink);

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
