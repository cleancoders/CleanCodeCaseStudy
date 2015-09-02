package cleancoderscom.usecases.codecastSummaries;

public class CodecastSummaryInputBoundarySpy implements CodecastSummaryInputBoundary {
  public boolean summarizeCodecastsWasCalled = false;

  public void summarizeCodecasts() {
    summarizeCodecastsWasCalled = true;
  }
}
