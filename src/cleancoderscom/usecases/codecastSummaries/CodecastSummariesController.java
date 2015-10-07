package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.entities.User;
import cleancoderscom.http.Controller;
import cleancoderscom.http.ParsedRequest;

public class CodecastSummariesController implements Controller {
  private CodecastSummariesInputBoundary useCase;
  private final CodecastSummariesOutputBoundary presenter;
  private final CodecastSummariesView view;

  public CodecastSummariesController(CodecastSummariesInputBoundary useCase,
                                     CodecastSummariesOutputBoundary presenter,
                                     CodecastSummariesView view) {
    this.useCase = useCase;
    this.presenter = presenter;
    this.view = view;
  }

  public String handle(ParsedRequest request) {
    final User user = Context.gateKeeper.getLoggedInUser();
    useCase.summarizeCodecasts(user, presenter);
    view.generateView(presenter.getResponseModel());
//    User bob = Context.userGateway.findUserByName("Bob");
//    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
//    CodecastSummariesView view = new CodecastSummariesView();
//    final String html = view.toHTML(useCase.presentCodecasts(bob));
//    return Controller.makeResponse(html);
    return null;
  }


}
