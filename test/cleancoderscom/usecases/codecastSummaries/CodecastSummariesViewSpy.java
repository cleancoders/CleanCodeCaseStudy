package cleancoderscom.usecases.codecastSummaries;

public class CodecastSummariesViewSpy implements CodecastSummariesView
{
  public boolean generateViewWasCalled = false;
  public CodecastSummariesViewModel viewModel;

  public String generateView(CodecastSummariesViewModel viewModel)
  {
    this.viewModel = viewModel;
    generateViewWasCalled = true;
    return null;
  }
}
