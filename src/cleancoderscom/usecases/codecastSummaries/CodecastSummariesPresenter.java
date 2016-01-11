package cleancoderscom.usecases.codecastSummaries;

import java.text.SimpleDateFormat;
import static cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewModel.*;

public class CodecastSummariesPresenter implements CodecastSummariesOutputBoundary {

  public static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");
  public CodecastSummariesViewModel viewModel;

  @Override
  public CodecastSummariesViewModel getViewModel() {
    return viewModel;
  }

  @Override
  public void present(CodecastSummariesResponseModel responseModel) {
    viewModel = new CodecastSummariesViewModel();
    for(CodecastSummary codecastSummary : responseModel.getCodecastSummaries()) {
      viewModel.addModel(makeViewable(codecastSummary));
    }
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