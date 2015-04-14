package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.*;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;

public class CodecastSummariesUseCase {
  public static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");

  public List<PresentableCodecastSummary> presentCodecasts(User loggedInUser) {
    ArrayList<PresentableCodecastSummary> presentableCodecasts = new ArrayList<PresentableCodecastSummary>();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    for (Codecast codecast : allCodecasts)
      presentableCodecasts.add(formatCodecast(loggedInUser, codecast));

    return presentableCodecasts;
  }

  private PresentableCodecastSummary formatCodecast(User loggedInUser, Codecast codecast) {
    PresentableCodecastSummary cc = new PresentableCodecastSummary();
    formatSummaryFields(loggedInUser, codecast, cc);
    return cc;
  }

  public static void formatSummaryFields(User loggedInUser, Codecast codecast, PresentableCodecastSummary cc) {
    cc.title = codecast.getTitle();
    cc.publicationDate = dateFormat.format(codecast.getPublicationDate());
    cc.isViewable = isLicensedFor(VIEWING, loggedInUser, codecast);
    cc.isDownloadable = isLicensedFor(DOWNLOADING, loggedInUser, codecast);
  }

  public static boolean isLicensedFor(License.LicenseType licenseType, User user, Codecast codecast) {
    List<License> licenses = Context.licenseGateway.findLicensesForUserAndCodecast(user, codecast);
    for (License l : licenses) {
      if (l.getType() == licenseType)
        return true;
    }
    return false;
  }

}
