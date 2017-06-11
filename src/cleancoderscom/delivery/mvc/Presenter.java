package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.usecases.entities.CodecastSummary;

import java.time.format.DateTimeFormatter;

public class Presenter implements OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> {

  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/dd/yyyy");

  private CodecastSummariesViewModel viewModel;

  @Override
  public CodecastSummariesViewModel getViewModel() {
    return viewModel;
  }

  @Override
  public void present(CodecastSummariesResponse responseModel) {
    viewModel = new CodecastSummariesViewModel();
    responseModel.getCodecastSummaries().stream()
      .map(this::makeViewable)
      .forEach(viewable -> viewModel.addModel(viewable));
  }

  private CodecastSummariesViewModel.ViewableCodecastSummary makeViewable(CodecastSummary codecastSummary) {
    final CodecastSummariesViewModel.ViewableCodecastSummary summary = new CodecastSummariesViewModel.ViewableCodecastSummary();
    summary.title = codecastSummary.title;
    summary.permalink = codecastSummary.permalink;
    summary.publicationDate = codecastSummary.publicationDate.format(dateFormatter);
    summary.isDownloadable = codecastSummary.isDownloadable;
    summary.isViewable = codecastSummary.isViewable;
    return summary;
  }
}