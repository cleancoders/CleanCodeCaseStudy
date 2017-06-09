package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.*;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import java.util.List;
import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;

public class CodecastSummariesUseCase implements CodecastSummariesInputBoundary {
  public static boolean isLicensedFor(License.LicenseType licenseType, User user, Codecast codecast) {
    List<License> licenses = Context.licenseGateway.findLicensesForUserAndCodecast(user, codecast);
    return licenses.stream()
        .anyMatch(l -> l.getType() == licenseType);
  }

  @Override
  public void summarizeCodecasts(User loggedInUser, CodecastSummariesOutputBoundary presenter) {
    CodecastSummariesResponseModel responseModel = new CodecastSummariesResponseModel();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    allCodecasts
        .stream()
        .map(codecast -> summarizeCodecast(codecast, loggedInUser))
        .forEach(responseModel::addCodecastSummary);

    presenter.present(responseModel);
  }

  private CodecastSummary summarizeCodecast(Codecast codecast, User user) {
    CodecastSummary summary = new CodecastSummary();
    summary.title = codecast.getTitle();
    summary.publicationDate = codecast.getPublicationDate();
    summary.permalink = codecast.getPermalink();
    summary.isViewable = isLicensedFor(VIEWING, user, codecast);
    summary.isDownloadable = isLicensedFor(DOWNLOADING, user, codecast);
    return summary;
  }

}

