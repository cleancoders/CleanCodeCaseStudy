package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.Controller;
import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.adapters.mvc.View;
import cleancoderscom.delivery.mvc.types.CodecastSummariesMvcControllerHandler;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.usecases.gateways.Context;

import java.util.Objects;
import java.util.function.Function;

public class CodecastSummariesMvcController implements Controller<Request, String> {

  private final CodecastSummariesMvcControllerHandler handler;

  public CodecastSummariesMvcController(
    UseCase<User, CodecastSummariesResponse> useCase,
    OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> outputBoundary,
    View<CodecastSummariesViewModel, String> view) {
    Objects.requireNonNull(useCase);
    Objects.requireNonNull(outputBoundary);
    Objects.requireNonNull(view);
    Objects.requireNonNull(Context.gateKeeper);
    this.handler = new CodecastSummariesMvcControllerHandler(useCase, outputBoundary, view, request -> Context.gateKeeper.getLoggedInUser());
  }

  @Override
  public Function<Request, String> getHandler() {
    return this.handler;
  }
}
