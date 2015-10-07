package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.view.ViewTemplate;

import java.io.IOException;
import java.util.List;

public class CodecastSummariesViewImpl implements CodecastSummariesView
{
  static String toHTML(List<CodecastSummariesViewModel> presentableCodecasts) {

    try {
      ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");

      StringBuilder codecastLines = new StringBuilder();
      for(CodecastSummariesViewModel presentableCodecast : presentableCodecasts) {

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

  public String generateView(CodecastSummariesResponseModel responseModel)
  {
    return null;
  }
}
