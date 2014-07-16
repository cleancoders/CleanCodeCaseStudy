package cleancoderscom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static cleancoderscom.License.LicenseType.DOWNLOADING;
import static cleancoderscom.License.LicenseType.VIEWING;

public class PresentCodecastUseCase {
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/yyyy");

  public List<PresentableCodecast> presentCodecasts(User loggedInUser) {
    ArrayList<PresentableCodecast> presentableCodecasts = new ArrayList<PresentableCodecast>();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    for (Codecast codecast : allCodecasts)
      presentableCodecasts.add(formatCodecast(loggedInUser, codecast));

    return presentableCodecasts;
  }

  private PresentableCodecast formatCodecast(User loggedInUser, Codecast codecast) {
    PresentableCodecast cc = new PresentableCodecast();
    cc.title = codecast.getTitle();
    cc.publicationDate = dateFormat.format(codecast.getPublicationDate());
    cc.isViewable = isLicensedFor(VIEWING, loggedInUser, codecast);
    cc.isDownloadable = isLicensedFor(DOWNLOADING, loggedInUser, codecast);
    return cc;
  }

  public boolean isLicensedFor(License.LicenseType licenseType, User user, Codecast codecast) {
    List<License> licenses = Context.licenseGateway.findLicensesForUserAndCodecast(user, codecast);
    for (License l : licenses) {
      if (l.getType() == licenseType)
        return true;
    }
    return false;
  }

}
