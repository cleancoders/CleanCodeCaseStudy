package cleancoderscom.adapters.mvc;

@FunctionalInterface
public interface View<ViewModel, Output> {
  Output generateView(ViewModel viewModel);
}
