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

    CodecastSummaryInputBoundarySpy useCaseSpy = new CodecastSummaryInputBoundarySpy();
    CodecastSummaryOutputBoundarySpy presenterSpy = new CodecastSummaryOutputBoundarySpy();
    CodecastSummariesController controller = new CodecastSummariesController(useCaseSpy, presenterSpy);

    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(useCaseSpy.summarizeCodecastsWasCalled);
    String loggedInUser = Context.userGateway.findUserByName("Bob").getId();
    assertEquals(loggedInUser, useCaseSpy.requestedUser.getId());
    assertSame(presenterSpy, useCaseSpy.outputBoundary);
  }

  @Test
  public void controllerSendsTheResponseModelToTheView() throws Exception
  {
    TestSetup.setupSampleData();

    CodecastSummaryInputBoundarySpy useCaseSpy = new CodecastSummaryInputBoundarySpy();
    CodecastSummaryOutputBoundarySpy presenterSpy = new CodecastSummaryOutputBoundarySpy();
    CodecastSummaryViewSpy viewSpy = new CodecastSummaryViewSpy();
    CodecastSummariesController controller = new CodecastSummariesController(useCaseSpy, presenterSpy, viewSpy);
    presenterSpy.responseModel = new CodecastSummaryResponseModel();

    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(viewSpy.generateViewWasCalled);
    assertSame(presenterSpy.responseModel, viewSpy.responseModel);
  }

}
