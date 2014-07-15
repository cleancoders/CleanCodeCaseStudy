package cleancoderscom.fixtures;

import cleancoderscom.*;

import java.util.ArrayList;
import java.util.List;

import static cleancoderscom.License.LicenseType.DOWNLOADING;
import static cleancoderscom.License.LicenseType.VIEWING;

public class CodecastPresentation {
  private PresentCodecastUseCase useCase = new PresentCodecastUseCase();
  public static GateKeeper gateKeeper = new GateKeeper();

  public CodecastPresentation() {
    Context.gateway = new MockGateway();
  }

  public boolean addUser(String username) {
    Context.gateway.save(new User(username));
    return true;
  }

  public boolean loginUser(String username) {
    User user = Context.gateway.findUserByName(username);
    if (user != null) {
      gateKeeper.setLoggedInUser(user);
      return true;
    } else {
      return false;
    }
  }

  public boolean createLicenseForViewing(String username, String codecastTitle) {
    User user = Context.gateway.findUserByName(username);
    Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
    License license = new License(VIEWING, user, codecast);
    Context.gateway.save(license);
    return useCase.isLicensedFor(VIEWING, user, codecast);
  }

  public boolean createLicenseForDownloading(String username, String codecastTitle) {
    User user = Context.gateway.findUserByName(username);
    Codecast codecast = Context.gateway.findCodecastByTitle(codecastTitle);
    License license = new License(DOWNLOADING, user, codecast);
    Context.gateway.save(license);
    return useCase.isLicensedFor(DOWNLOADING, user, codecast);
  }

  public String presentationUser() {
    return gateKeeper.getLoggedInUser().getUserName();
  }

  public boolean clearCodecasts() {
    List<Codecast> codecasts = Context.gateway.findAllCodecastsSortedChronologically();
    for (Codecast codecast : new ArrayList<Codecast>(codecasts)) {
      Context.gateway.delete(codecast);
    }
    return Context.gateway.findAllCodecastsSortedChronologically().size() == 0;
  }

  public int countOfCodecastsPresented() {
    List<PresentableCodecast> presentations = useCase.presentCodecasts(gateKeeper.getLoggedInUser());
    return presentations.size();
  }
}
