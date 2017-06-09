package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.TestSetup;
import cleancoderscom.delivery.mvc.CodecastSummariesMvcController;
import cleancoderscom.delivery.mvc.ParsedRequest;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodecastSummariesControllerTest {

  public CodecastSummariesInputBoundarySpy useCaseSpy;
  public OutputBoundarySpy presenterSpy;
  public CodecastSummariesViewSpy viewSpy;
  public CodecastSummariesMvcController controller;

  @Before
  public void setUp()
  {
    TestSetup.setupSampleData();

    useCaseSpy = getCodecastSummariesInputBoundarySpy();
    presenterSpy = new OutputBoundarySpy();
    viewSpy = new CodecastSummariesViewSpy();
    controller = new CodecastSummariesMvcController(useCaseSpy, presenterSpy, viewSpy);
  }

  @Test
  @Ignore
  public void testInputBoundaryInvocation() throws Exception
  {
    Request request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(useCaseSpy.summarizeCodecastsWasCalled);
    String loggedInUser = Context.userGateway.findUserByName("Bob").getId();
    assertEquals(loggedInUser, useCaseSpy.requestedUser.getId());
    assertSame(presenterSpy, useCaseSpy.outputBoundary);
  }

  @Test
  @Ignore
  public void controllerSendsTheViewModelToTheView() throws Exception
  {
    presenterSpy.viewModel = new CodecastSummariesViewModel();

    Request request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(viewSpy.generateViewWasCalled);
    assertSame(presenterSpy.viewModel, viewSpy.viewModel);
  }

  private CodecastSummariesInputBoundarySpy getCodecastSummariesInputBoundarySpy()
  {
    return new CodecastSummariesInputBoundarySpy();
  }

}
