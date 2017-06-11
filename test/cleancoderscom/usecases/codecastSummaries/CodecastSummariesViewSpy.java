package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.adapters.mvc.View;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;

public class CodecastSummariesViewSpy implements View<CodecastSummariesViewModel, String> {
  public boolean generateViewWasCalled = false;
  public CodecastSummariesViewModel viewModel;

  public String generateView(CodecastSummariesViewModel viewModel) {
    this.viewModel = viewModel;
    generateViewWasCalled = true;
    return null;
  }
}
