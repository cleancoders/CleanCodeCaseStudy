package cleancoderscom.usecases.codecastSummaries;

public class CodecastSummariesOutputBoundarySpy implements CodecastSummariesOutputBoundary
{
  public CodecastSummariesViewModel viewModel;

  @Override
  public CodecastSummariesViewModel getViewModel()
  {
    return viewModel;
  }
}
