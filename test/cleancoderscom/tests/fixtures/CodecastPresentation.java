package cleancoderscom.tests.fixtures;

import cleancoderscom.*;
import cleancoderscom.tests.TestSetup;

import java.util.ArrayList;
import java.util.List;

import static cleancoderscom.License.LicenseType.DOWNLOADING;
import static cleancoderscom.License.LicenseType.VIEWING;

public class CodecastPresentation {
  private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
  public static GateKeeper gateKeeper = new GateKeeper();

  public CodecastPresentation() {
    TestSetup.addInMemoryGatewaysToContext();
  }

  public boolean addUser(String username) {
    Context.userGateway.save(new User(username));
    return true;
  }

  public boolean loginUser(String username) {
    User user = Context.userGateway.findUserByName(username);
    if (user != null) {
      gateKeeper.setLoggedInUser(user);
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
    return gateKeeper.getLoggedInUser().getUserName();
  }

  public boolean clearCodecasts() {
    List<Codecast> codecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();
    for (Codecast codecast : new ArrayList<Codecast>(codecasts)) {
      Context.codecastGateway.delete(codecast);
    }
    return Context.codecastGateway.findAllCodecastsSortedChronologically().size() == 0;
  }

  public int countOfCodecastsPresented() {
    List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
    return presentations.size();
  }
}
