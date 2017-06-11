package cleancoderscom.delivery.mvc.types;

import cleancoderscom.adapters.mvc.MvcControllerHandler;
import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.adapters.mvc.View;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

import java.util.function.Function;

public class CodecastSummariesMvcControllerHandler extends MvcControllerHandler<
  User,
  String,
  CodecastSummariesResponse,
  CodecastSummariesViewModel,
  UseCase<User, CodecastSummariesResponse>,
  OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse>,
  View<CodecastSummariesViewModel, String>> {

  public CodecastSummariesMvcControllerHandler(
    UseCase<User, CodecastSummariesResponse> useCase,
    OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> outputBoundary,
    View<CodecastSummariesViewModel, String> view,
    Function<Request, User> converter) {
    super(useCase, outputBoundary, view, converter);
  }
}