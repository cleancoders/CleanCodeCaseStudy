package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.entities.User;
import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;

public class CodecastSummariesController implements Controller {
  private CodecastSummaryInputBoundary useCase;
  private final CodecastSummaryOutputBoundary presenter;

  public CodecastSummariesController(CodecastSummaryInputBoundary useCase, CodecastSummaryOutputBoundary presenter) {
    this.useCase = useCase;
    this.presenter = presenter;
  }

  public String handle(ParsedRequest request) {
    final User user = Context.gateKeeper.getLoggedInUser();
    useCase.summarizeCodecasts(user, presenter);
//    User bob = Context.userGateway.findUserByName("Bob");
//    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
//    CodecastSummariesView view = new CodecastSummariesView();
//    final String html = view.toHTML(useCase.presentCodecasts(bob));
//    return Controller.makeResponse(html);
    return null;
  }


}
