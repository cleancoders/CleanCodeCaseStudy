package cleancoderscom.usecases.codecastSummaries;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CodecastSummariesPresenterTest {

  @Test
  public void presenterIsAnOutputBoundary() throws Exception
  {
    assertTrue(new CodecastSummariesPresenter() instanceof CodecastSummariesOutputBoundary);
  }

}
