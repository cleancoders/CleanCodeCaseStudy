package cleancoderscom;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PresentCodecastUseCase {
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/dd/YYYY");

  public List<PresentableCodecast> presentCodecasts(User loggedInUser) {
    ArrayList<PresentableCodecast> presentableCodecasts = new ArrayList<PresentableCodecast>();
    List<Codecast> allCodecasts = Context.gateway.findAllCodecastsSortedChronologically();
    for (Codecast codecast : allCodecasts) {

      PresentableCodecast cc = new PresentableCodecast();
      cc.title = codecast.getTitle();
      cc.publicationDate = dateFormat.format(codecast.getPublicationDate());
      cc.isViewable = isLicensedToViewCodecast(loggedInUser, codecast);
      presentableCodecasts.add(cc);
    }
    return presentableCodecasts;
  }

  public boolean isLicensedToViewCodecast(User user, Codecast codecast) {
    List<License> licenses = Context.gateway.findLicensesForUserAndCodecast(user, codecast);
    return !licenses.isEmpty();
  }
}
