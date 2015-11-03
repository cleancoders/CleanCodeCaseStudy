package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewModel.ViewableCodecastSummary;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class CodecastSummariesPresenterTest {

  @Test
  public void validateViewModel() throws Exception
  {
    CodecastSummariesResponseModel responseModel = new CodecastSummariesResponseModel();
    CodecastSummary summary = new CodecastSummary();
    summary.title = "Title";
    summary.publicationDate = new GregorianCalendar(2015, 10, 3).getTime();
    summary.permalink = "permalink";
    summary.isViewable = true;
    summary.isDownloadable = false;
    responseModel.addCodecastSummary(summary);

    CodecastSummariesPresenter presenter = new CodecastSummariesPresenter();
    presenter.present(responseModel);

    CodecastSummariesViewModel viewModel = presenter.getViewModel();
    ViewableCodecastSummary viewableSummary = viewModel.viewableCodecastSummaries.get(0);
    assertEquals(summary.title, viewableSummary.title);
  }

}
