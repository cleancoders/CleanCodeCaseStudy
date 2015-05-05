package cleancoderscom.utilities;

import cleancoderscom.Context;
import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;
import cleancoderscom.http.RequestParser;
import cleancoderscom.http.Router;
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
    Router router = new Router();
    router.addPath("", new CodecastSummariesController());
//    router.addPath("episode", new CodecastDetailsController());


    TestSetup.setupSampleData();
    SocketServer server = new SocketServer(8080, s -> {
      try {
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        ParsedRequest request = new RequestParser().parse(reader.readLine());
        String response = router.route(request);
        if (response != null)
          s.getOutputStream().write(response.getBytes());
        else
          s.getOutputStream().write("HTTP/1.1 404 OK\n".getBytes());
        s.close();
      } catch(IOException e) {
        e.printStackTrace();
      }
    });
    server.start();
  }

  static class CodecastSummariesController implements Controller {
    public String handle(ParsedRequest request) {
      String frontPage = getFrontPage();
      return makeResponse(frontPage);
    }
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
