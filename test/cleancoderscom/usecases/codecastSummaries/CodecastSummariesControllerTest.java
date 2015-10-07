package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.TestSetup;
import cleancoderscom.http.ParsedRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodecastSummariesControllerTest {

  public CodecastSummariesInputBoundarySpy useCaseSpy;
  public CodecastSummariesOutputBoundarySpy presenterSpy;
  public CodecastSummariesViewSpy viewSpy;
  public CodecastSummariesController controller;

  @Before
  public void setUp()
  {
    TestSetup.setupSampleData();

    useCaseSpy = getCodecastSummariesInputBoundarySpy();
    presenterSpy = new CodecastSummariesOutputBoundarySpy();
    viewSpy = new CodecastSummariesViewSpy();
    controller = new CodecastSummariesController(useCaseSpy, presenterSpy, viewSpy);
  }

  @Test
  public void testInputBoundaryInvocation() throws Exception
  {
    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(useCaseSpy.summarizeCodecastsWasCalled);
    String loggedInUser = Context.userGateway.findUserByName("Bob").getId();
    assertEquals(loggedInUser, useCaseSpy.requestedUser.getId());
    assertSame(presenterSpy, useCaseSpy.outputBoundary);
  }

  @Test
  public void controllerSendsTheViewModelToTheView() throws Exception
  {
    presenterSpy.viewModel = new CodecastSummariesViewModel();

    ParsedRequest request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(viewSpy.generateViewWasCalled);
    assertSame(presenterSpy.viewModel, viewSpy.viewModel);
  }

  private CodecastSummariesInputBoundarySpy getCodecastSummariesInputBoundarySpy()
  {
    return new CodecastSummariesInputBoundarySpy();
  }

}
