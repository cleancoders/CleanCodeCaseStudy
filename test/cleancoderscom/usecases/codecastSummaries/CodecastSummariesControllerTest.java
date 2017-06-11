package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.TestSetup;
import cleancoderscom.adapters.mvc.Request;
import cleancoderscom.delivery.mvc.CodecastSummariesMvcController;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;
import cleancoderscom.delivery.mvc.ParsedRequest;
import cleancoderscom.usecases.gateways.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class CodecastSummariesControllerTest {

  public CodecastSummariesInputBoundarySpy useCaseSpy;
  public OutputBoundarySpy presenterSpy;
  public CodecastSummariesViewSpy viewSpy;
  public CodecastSummariesMvcController controller;

  @Before
  public void setUp() {
    TestSetup.setupSampleData();

    useCaseSpy = getCodecastSummariesInputBoundarySpy();
    presenterSpy = new OutputBoundarySpy();
    viewSpy = new CodecastSummariesViewSpy();
    controller = new CodecastSummariesMvcController(useCaseSpy, presenterSpy, viewSpy);
  }

  @Test
  public void testInputBoundaryInvocation() throws Exception {
    Request request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(useCaseSpy.summarizeCodecastsWasCalled);
    String loggedInUser = Context.userGateway.findUserByName("Bob").getId();
    assertEquals(loggedInUser, useCaseSpy.requestedUser.getId());
  }

  @Test
  public void controllerSendsTheViewModelToTheView() throws Exception {
    presenterSpy.viewModel = new CodecastSummariesViewModel();

    Request request = new ParsedRequest("GET", "blah");
    controller.handle(request);

    assertTrue(viewSpy.generateViewWasCalled);
    assertSame(presenterSpy.viewModel, viewSpy.viewModel);
  }

  private CodecastSummariesInputBoundarySpy getCodecastSummariesInputBoundarySpy() {
    return new CodecastSummariesInputBoundarySpy();
  }

}
