package cleancoderscom.usecases.codecastSummaries;

public class CodecastSummariesViewSpy implements CodecastSummariesView
{
  public boolean generateViewWasCalled = false;
  public CodecastSummariesResponseModel responseModel;

  public String generateView(CodecastSummariesResponseModel responseModel)
  {
    this.responseModel = responseModel;
    generateViewWasCalled = true;
    return null;
  }
}
