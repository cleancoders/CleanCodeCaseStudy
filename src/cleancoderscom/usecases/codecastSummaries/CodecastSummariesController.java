package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.entities.User;
import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;

public class CodecastSummariesController implements Controller {
  public String handle(ParsedRequest request) {
    User bob = Context.userGateway.findUserByName("Bob");
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    CodecastSummariesView view = new CodecastSummariesView();
    final String html = view.toHTML(useCase.presentCodecasts(bob));
    return Controller.makeResponse(html);
  }


}
