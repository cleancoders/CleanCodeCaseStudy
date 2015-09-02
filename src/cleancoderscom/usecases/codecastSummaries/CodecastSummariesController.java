package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;

public class CodecastSummariesController implements Controller {
  private CodecastSummaryInputBoundary codecastSummaryInputBoundary;

  public CodecastSummariesController(CodecastSummaryInputBoundary codecastSummaryInputBoundary) {
    this.codecastSummaryInputBoundary = codecastSummaryInputBoundary;
  }

  public String handle(ParsedRequest request) {
    codecastSummaryInputBoundary.summarizeCodecasts();

//    User bob = Context.userGateway.findUserByName("Bob");
//    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
//    CodecastSummariesView view = new CodecastSummariesView();
//    final String html = view.toHTML(useCase.presentCodecasts(bob));
//    return Controller.makeResponse(html);
    return null;
  }


}
