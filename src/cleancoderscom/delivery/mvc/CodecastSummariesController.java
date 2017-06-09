package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.Controller;
import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.adapters.mvc.View;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

public class CodecastSummariesController implements Controller {
  private final UseCase<User, CodecastSummariesResponse> useCase;
  private final OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> presenter;
  private final View<CodecastSummariesViewModel, String> view;

  public CodecastSummariesController(UseCase<User, CodecastSummariesResponse> useCase,
                                     OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> presenter,
                                     View<CodecastSummariesViewModel, String> view) {
    this.useCase = useCase;
    this.presenter = presenter;
    this.view = view;
  }

  @Override
  public String handle(Request request) {
    final User user = Context.gateKeeper.getLoggedInUser();
    presenter.present(useCase.execute(user));
    return view.generateView(presenter.getViewModel());
  }
}
