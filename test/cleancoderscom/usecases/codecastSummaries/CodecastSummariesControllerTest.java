package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.TestSetup;
import cleancoderscom.http.ParsedRequest;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodecastSummariesControllerTest {

  @Test
  public void testInputBoundaryInvocation() throws Exception
  {
    TestSetup.setupSampleData();
//    List<CodecastSummaryResponseModel> summaryResponseModels = new ArrayList<>();

    CodecastSummaryInputBoundarySpy codecastSummaryInputBoundary = new CodecastSummaryInputBoundarySpy();
    CodecastSummariesController controller = new CodecastSummariesController(codecastSummaryInputBoundary);

    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(codecastSummaryInputBoundary.summarizeCodecastsWasCalled);
    String loggedInUser = Context.userGateway.findUserByName("Bob").getId();
    assertEquals(loggedInUser, codecastSummaryInputBoundary.requestedUser.getId());
    CodecastSummaryOutputBoundary outputBoundary = codecastSummaryInputBoundary.outputBoundary;
    assertNotNull(outputBoundary);
  }

}
