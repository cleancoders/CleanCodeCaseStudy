package cleancoderscom.usecases.codecastSummaries;

public interface CodecastSummariesOutputBoundary
{
  CodecastSummariesViewModel getViewModel();

  void present(CodecastSummariesResponseModel responseModel);
}
