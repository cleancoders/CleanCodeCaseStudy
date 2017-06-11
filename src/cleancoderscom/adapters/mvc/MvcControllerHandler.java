package cleancoderscom.adapters.mvc;

import cleancoderscom.usecases.core.UseCase;

import java.util.function.Function;

public class MvcControllerHandler<
  RequestModel,
  Output,
  ResponseModel,
  ViewModel,
  INPUT_BOUNDARY extends UseCase<RequestModel, ResponseModel>,
  OUTPUT_BOUNDARY extends OutputBoundary<ViewModel, ResponseModel>,
  VIEW extends View<ViewModel, Output>>
  implements Function<Request, Output> {

  private final INPUT_BOUNDARY useCase;
  private final VIEW view;
  private final OUTPUT_BOUNDARY outputBoundary;
  private final Function<Request, RequestModel> converter;

  public MvcControllerHandler(INPUT_BOUNDARY useCase, OUTPUT_BOUNDARY outputBoundary, VIEW view, Function<Request, RequestModel> converter) {
    this.useCase = useCase;
    this.view = view;
    this.outputBoundary = outputBoundary;
    this.converter = converter;
  }

  @Override
  public Output apply(Request request) {
    ResponseModel responseModel = converter
      .andThen(useCase)
      .apply(request);
    outputBoundary.present(responseModel);
    return view.generateView(outputBoundary.getViewModel());
  }

}

