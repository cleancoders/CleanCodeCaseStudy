package cleancoderscom.tests.fixtures;

import cleancoderscom.*;
import cleancoderscom.tests.TestSetup;

import java.util.ArrayList;
import java.util.List;

import static cleancoderscom.License.LicenseType.DOWNLOADING;
import static cleancoderscom.License.LicenseType.VIEWING;

public class CodecastPresentation {
  private CodecastSummaryUseCase useCase = new CodecastSummaryUseCase();

  public CodecastPresentation() {
    TestSetup.setupContext();
  }

  public boolean addUser(String username) {
    Context.userGateway.save(new User(username));
    return true;
  }

  public boolean loginUser(String username) {
    User user = Context.userGateway.findUserByName(username);
    if (user != null) {
      Context.gateKeeper.setLoggedInUser(user);
      return true;
    } else {
      return false;
    }
  }

  public boolean createLicenseForViewing(String username, String codecastTitle) {
    User user = Context.userGateway.findUserByName(username);
    Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
    License license = new License(VIEWING, user, codecast);
    Context.licenseGateway.save(license);
    return useCase.isLicensedFor(VIEWING, user, codecast);
  }

  public boolean createLicenseForDownloading(String username, String codecastTitle) {
    User user = Context.userGateway.findUserByName(username);
    Codecast codecast = Context.codecastGateway.findCodecastByTitle(codecastTitle);
    License license = new License(DOWNLOADING, user, codecast);
    Context.licenseGateway.save(license);
    return useCase.isLicensedFor(DOWNLOADING, user, codecast);
  }

  public String presentationUser() {
    return Context.gateKeeper.getLoggedInUser().getUserName();
  }

  public boolean clearCodecasts() {
    List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();
    for (Codecast codecast : new ArrayList<Codecast>(codecasts)) {
      Context.codecastGateway.delete(codecast);
    }
    return Context.codecastGateway.findAllCodecastsSortedChronologically().size() == 0;
  }

  public int countOfCodecastsPresented() {
    List<PresentableCodecastSummary> presentations = useCase.presentCodecasts(Context.gateKeeper.getLoggedInUser());
    return presentations.size();
  }
}
