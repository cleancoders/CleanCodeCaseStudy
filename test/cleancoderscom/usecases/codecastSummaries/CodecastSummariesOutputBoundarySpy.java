package cleancoderscom.usecases.codecastSummaries;

public class CodecastSummariesOutputBoundarySpy implements CodecastSummariesOutputBoundary
{
  public CodecastSummariesResponseModel responseModel;

  @Override
  public CodecastSummariesResponseModel getResponseModel()
  {
    return responseModel;
  }
}
