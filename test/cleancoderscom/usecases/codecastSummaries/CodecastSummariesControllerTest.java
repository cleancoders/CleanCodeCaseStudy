package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.TestSetup;
import cleancoderscom.http.ParsedRequest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

public class CodecastSummariesControllerTest {

  @Test
  public void testFrontPage() throws Exception
  {
    TestSetup.setupContext();
//    List<CodecastSummaryResponseModel> summaryResponseModels = new ArrayList<>();

    CodecastSummaryInputBoundarySpy codecastSummaryInputBoundary = new CodecastSummaryInputBoundarySpy();
    CodecastSummariesController controller = new CodecastSummariesController(codecastSummaryInputBoundary);

    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(codecastSummaryInputBoundary.summarizeCodecastsWasCalled);
//    assertNotNull(codecastSummaryInputBoundary.requestModel);
  }

}
