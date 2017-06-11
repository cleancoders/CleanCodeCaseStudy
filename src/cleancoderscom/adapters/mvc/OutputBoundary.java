package cleancoderscom.adapters.mvc;

public interface OutputBoundary<ViewModel, ResponseModel> {
  ViewModel getViewModel();

  void present(ResponseModel responseModel);
}
