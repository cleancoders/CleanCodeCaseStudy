package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.delivery.mvc.Presenter;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel;
import cleancoderscom.delivery.mvc.CodecastSummariesViewModel.ViewableCodecastSummary;
import cleancoderscom.usecases.entities.CodecastSummary;
import org.junit.Test;

import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class CodecastSummariesPresenterTest {

  @Test
  public void validateViewModel() throws Exception {
    CodecastSummariesResponse responseModel = new CodecastSummariesResponse();
    CodecastSummary summary = new CodecastSummary();
    summary.title = "Title";
    summary.publicationDate = new GregorianCalendar(2015, 10, 3).getTime();
    summary.permalink = "permalink";
    summary.isViewable = true;
    summary.isDownloadable = false;
    responseModel.addCodecastSummary(summary);

    Presenter presenter = new Presenter();
    presenter.present(responseModel);

    CodecastSummariesViewModel viewModel = presenter.getViewModel();
    ViewableCodecastSummary viewableSummary = viewModel.viewableCodecastSummaries.get(0);
    assertEquals(summary.title, viewableSummary.title);
  }

}
