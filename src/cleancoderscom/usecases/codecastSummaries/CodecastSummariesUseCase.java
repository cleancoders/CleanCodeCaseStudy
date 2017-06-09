package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.usecases.entities.CodecastSummary;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import java.util.List;
import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;

public class CodecastSummariesUseCase implements UseCase<User, CodecastSummariesResponse> {

  @Override
  public CodecastSummariesResponse execute(User loggedInUser) {
    CodecastSummariesResponse responseModel = new CodecastSummariesResponse();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    allCodecasts
        .stream()
        .map(codecast -> summarizeCodecast(codecast, loggedInUser))
        .forEach(responseModel::addCodecastSummary);

    return responseModel;
  }

  public static boolean isLicensedFor(License.LicenseType licenseType, User user, Codecast codecast) {
    List<License> licenses = Context.licenseGateway.findLicensesForUserAndCodecast(user, codecast);
    return licenses.stream()
        .anyMatch(l -> l.getType() == licenseType);
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

