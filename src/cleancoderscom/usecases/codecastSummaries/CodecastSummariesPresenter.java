package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.User;

import java.text.SimpleDateFormat;

import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;

public class CodecastSummariesPresenter {

  public static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");

  public static void formatSummaryFields(User loggedInUser, Codecast codecast, PresentableCodecastSummary cc) {
    cc.title = codecast.getTitle();
    cc.publicationDate = dateFormat.format(codecast.getPublicationDate());
    cc.isViewable = CodecastSummariesUseCase.isLicensedFor(VIEWING, loggedInUser, codecast);
    cc.isDownloadable = CodecastSummariesUseCase.isLicensedFor(DOWNLOADING, loggedInUser, codecast);
    cc.permalink = codecast.getPermalink();
  }


  public static PresentableCodecastSummary formatCodecast(User loggedInUser, Codecast codecast) {
    PresentableCodecastSummary cc = new PresentableCodecastSummary();
    CodecastSummariesPresenter.formatSummaryFields(loggedInUser, codecast, cc);
    return cc;
  }

}