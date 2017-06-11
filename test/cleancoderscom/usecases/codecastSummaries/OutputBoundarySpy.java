package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

public class OutputBoundarySpy implements OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> {
  public CodecastSummariesViewModel viewModel;
  public CodecastSummariesResponse responseModel;

  @Override
  public CodecastSummariesViewModel getViewModel() {
    return viewModel;
  }

  @Override
  public void present(CodecastSummariesResponse responseModel) {
    this.responseModel = responseModel;
  }
}
