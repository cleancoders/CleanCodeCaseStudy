package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.View;
import cleancoderscom.delivery.view.ViewTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class CodecastSummariesViewImpl implements View<CodecastSummariesViewModel, String> {

  @Override
  public String generateView(CodecastSummariesViewModel viewModel) {
    return toHTML(viewModel.getViewableCodecasts());
  }

  static String toHTML(List<CodecastSummariesViewModel.ViewableCodecastSummary> presentableCodecasts) {
    try {
      ViewTemplate frontPageTemplate = ViewTemplate.create("html/frontpage.html");
      String content = presentableCodecasts.stream()
          .map(viewableCodecastSummary -> createViewTemplate(viewableCodecastSummary).getContent())
          .collect(Collectors.joining());

      frontPageTemplate.replace("codecasts", content);
      return frontPageTemplate.getContent();
    } catch(Exception e) {
      e.printStackTrace();
      return "Gunk";
    }
  }

  private static ViewTemplate createViewTemplate(CodecastSummariesViewModel.ViewableCodecastSummary viewableCodecastSummary) {
    ViewTemplate codecastTemplate;
    try {
      codecastTemplate = ViewTemplate.create("html/codecast.html");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    codecastTemplate.replace("title", viewableCodecastSummary.title);
    codecastTemplate.replace("publicationDate", viewableCodecastSummary.publicationDate);
    codecastTemplate.replace("permalink", viewableCodecastSummary.permalink);

    //staged
    codecastTemplate.replace("thumbnail", "https://d26o5k45lnmm4v.cloudfront.net/YmluYXJ5OjIxNzA1Nw");
    codecastTemplate.replace("author", "Uncle Bob");
    codecastTemplate.replace("duration", "58 min.");
    codecastTemplate.replace("contentActions", "Buying options go here.");
    return codecastTemplate;
  }
}
