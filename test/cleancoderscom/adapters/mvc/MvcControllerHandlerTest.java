package cleancoderscom.adapters.mvc;

import cleancoderscom.delivery.mvc.ParsedRequest;
import cleancoderscom.usecases.core.UseCase;
import org.junit.Before;
import org.junit.Test;

import java.util.function.Function;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class MvcControllerHandlerTest {

  private ViewModel viewModel;
  private UseCaseSpy useCaseSpy;
  private OutputBoundarySpy outputBoundarySpy;
  private ViewSpy viewSpy;
  private RequestModelMapperSpy requestModelMapperSpy;

  private MvcControllerHandler<
    RequestModel,
    String,
    ResponseModel,
    ViewModel,
    UseCase<RequestModel, ResponseModel>,
    OutputBoundary<ViewModel, ResponseModel>,
    View<ViewModel, String>> mvcControllerHandler;

  @Before
  public void setUp() {
    viewModel = new ViewModel();
    useCaseSpy = new UseCaseSpy();
    outputBoundarySpy = new OutputBoundarySpy(viewModel);
    viewSpy = new ViewSpy();
    requestModelMapperSpy = new RequestModelMapperSpy();
    mvcControllerHandler = new MvcControllerHandler<>(useCaseSpy, outputBoundarySpy, viewSpy, requestModelMapperSpy);
  }

  @Test
  public void testRequestModelMapperInvocation() throws Exception {
    Request request = new ParsedRequest("", "path");
    mvcControllerHandler.apply(request);

    assertTrue(requestModelMapperSpy.called);
    assertSame(request, requestModelMapperSpy.arg);
  }

  @Test
  public void testUseCaseInvocation() throws Exception {
    Request request = new ParsedRequest("", "path");
    mvcControllerHandler.apply(request);

    assertTrue(useCaseSpy.called);
    assertEquals(request.getPath() + "applied", useCaseSpy.arg.value);
  }

  @Test
  public void testOutputBoundaryInvocation() throws Exception {
    Request request = new ParsedRequest("", "path");
    mvcControllerHandler.apply(request);

    assertTrue(outputBoundarySpy.presentCalled);
    assertTrue(outputBoundarySpy.getViewModelCalled);
    assertEquals(outputBoundarySpy.viewModel.value, useCaseSpy.arg.value.toUpperCase());
  }

  @Test
  public void testViewInvocation() throws Exception {
    Request request = new ParsedRequest("", "path");
    String output = mvcControllerHandler.apply(request);

    assertTrue(viewSpy.called);
    assertSame(viewModel, viewSpy.arg);
    assertEquals("PATHAPPLIED", output);
  }


  static class UseCaseSpy implements UseCase<RequestModel, ResponseModel> {
    boolean called = false;
    RequestModel arg;

    @Override
    public ResponseModel apply(RequestModel requestModel) {
      arg = requestModel;
      called = true;
      ResponseModel responseModel = new ResponseModel();
      responseModel.value = requestModel.value.toUpperCase();
      return responseModel;
    }
  }

  static class ViewModel {
    String value;
  }

  static class RequestModel {
    String value;
  }

  static class ResponseModel {
    String value;
  }

  static class OutputBoundarySpy implements OutputBoundary<ViewModel, ResponseModel> {
    boolean getViewModelCalled = false;
    boolean presentCalled = false;

    final ViewModel viewModel;

    OutputBoundarySpy(ViewModel viewModel) {
      this.viewModel = viewModel;
    }


    @Override
    public ViewModel getViewModel() {
      getViewModelCalled = true;
      return viewModel;
    }

    @Override
    public void present(ResponseModel responseModel) {
      viewModel.value = responseModel.value;
      presentCalled = true;
    }
  }

  static class ViewSpy implements View<ViewModel, String> {
    boolean called = false;
    ViewModel arg;

    @Override
    public String generateView(ViewModel viewModel) {
      called = true;
      arg = viewModel;
      return viewModel.value;
    }
  }

  static class RequestModelMapperSpy implements Function<Request, RequestModel> {
    boolean called = false;
    Request arg;

    @Override
    public RequestModel apply(Request request) {
      arg = request;
      called = true;
      RequestModel requestModel = new RequestModel();
      requestModel.value = request.getPath() + "applied";
      return requestModel;
    }
  }

}