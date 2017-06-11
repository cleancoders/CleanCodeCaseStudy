package cleancoderscom.adapters.mvc;

public interface View<ViewModel, Output> {
  Output generateView(ViewModel viewModel);
}
