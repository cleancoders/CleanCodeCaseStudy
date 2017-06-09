package cleancoderscom.usecases.codecastSummaries;

import java.text.SimpleDateFormat;
import static cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewModel.*;

public class CodecastSummariesPresenter implements CodecastSummariesOutputBoundary {

  private final SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
  private CodecastSummariesViewModel viewModel;

  @Override
  public CodecastSummariesViewModel getViewModel() {
    return viewModel;
  }

  @Override
  public void present(CodecastSummariesResponseModel responseModel) {
    viewModel = new CodecastSummariesViewModel();
    responseModel.getCodecastSummaries().stream()
        .map(this::makeViewable)
        .forEach(viewable -> viewModel.addModel(viewable));
  }

  private ViewableCodecastSummary makeViewable(CodecastSummary codecastSummary) {
    final ViewableCodecastSummary summary = new ViewableCodecastSummary();
    summary.title = codecastSummary.title;
    summary.permalink = codecastSummary.permalink;
    summary.publicationDate = dateFormat.format(codecastSummary.publicationDate);
    summary.isDownloadable = codecastSummary.isDownloadable;
    summary.isViewable = codecastSummary.isViewable;
    return summary;
  }
}