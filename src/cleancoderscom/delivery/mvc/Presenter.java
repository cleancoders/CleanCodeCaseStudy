package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.usecases.entities.CodecastSummary;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

import java.text.SimpleDateFormat;

public class Presenter implements OutputBoundary<CodecastSummariesViewModel, CodecastSummariesResponse> {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
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
    summary.publicationDate = dateFormat.format(codecastSummary.publicationDate);
    summary.isDownloadable = codecastSummary.isDownloadable;
    summary.isViewable = codecastSummary.isViewable;
    return summary;
  }
}