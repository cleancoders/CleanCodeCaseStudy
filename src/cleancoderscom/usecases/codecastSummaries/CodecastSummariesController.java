package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.entities.User;
import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;
import cleancoderscom.view.ViewTemplate;

import java.io.IOException;
import java.util.List;

public class CodecastSummariesController implements Controller {
  public String handle(ParsedRequest request) {
    return Controller.makeResponse(getFrontPage());
  }

  static String getFrontPage() {
    // TODO - This belongs in the Controller
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    User bob = Context.userGateway.findUserByName("Bob");
    List<PresentableCodecastSummary> presentableCodecasts = useCase.presentCodecasts(bob);

    try {
      // TODO - This is the View
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
