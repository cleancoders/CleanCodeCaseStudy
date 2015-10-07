package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.*;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import cleancoderscom.TestSetup;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;
import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class CodecastSummariesUseCaseTest {
  private User user;
  private CodecastSummariesUseCase useCase;

  @Before
  public void setUp() {
    TestSetup.setupContext();
    user = Context.userGateway.save(new User("User"));
    useCase = new CodecastSummariesUseCase();
  }

  public class GivenNoCodecasts {
    @Test
    public void noneArePresented() throws Exception {
      List<CodecastSummariesViewModel> presentableCodecasts = useCase.presentCodecasts(user);

      assertEquals(0, presentableCodecasts.size());
    }
  }

  public class GivenOneCodecast {
    private Codecast codecast;

    @Before
    public void setupCodecast() {
      codecast = Context.codecastGateway.save(new Codecast());
    }

    @Test
    public void oneIsPresented() throws Exception {
      codecast.setTitle("Some Title");
      Date now = new GregorianCalendar(2014, 4, 19).getTime();
      codecast.setPublicationDate(now);
      codecast.setPermalink("permalink");
      Context.codecastGateway.save(codecast);

      List<CodecastSummariesViewModel> presentableCodecasts = useCase.presentCodecasts(user);

      assertEquals(1, presentableCodecasts.size());
      CodecastSummariesViewModel presentableCodecast = presentableCodecasts.get(0);
      assertEquals("Some Title", presentableCodecast.title);
      assertEquals("5/19/2014", presentableCodecast.publicationDate);
      assertEquals("permalink", presentableCodecast.permalink);
    }

    public class GivenNoLicenses {
      @Test
      public void userCannotViewCodecast() throws Exception {
        assertFalse(useCase.isLicensedFor(VIEWING, user, codecast));
      }

      @Test
      public void presentedCodecastShowsNotViewable() throws Exception {
        List<CodecastSummariesViewModel> presentableCodecasts = useCase.presentCodecasts(user);
        CodecastSummariesViewModel presentableCodecast = presentableCodecasts.get(0);
        assertFalse(presentableCodecast.isViewable);
      }
    }

    public class GivenOneViewingLicenseForTheUser {
      private License viewLicense;

      @Before
      public void setupLicense() {
        viewLicense = new License(VIEWING, user, codecast);
        Context.licenseGateway.save(viewLicense);
      }

      @Test
      public void userCanViewCodecast() throws Exception {
        assertTrue(useCase.isLicensedFor(VIEWING, user, codecast));
      }

      @Test
      public void unlicensedUserCannotViewOtherUsersCodecast() throws Exception {
        User otherUser = new User("otherUser");
        Context.userGateway.save(otherUser);
        assertFalse(useCase.isLicensedFor(VIEWING, otherUser, codecast));
      }

      @Test
      public void presentedCodecastIsViewable() throws Exception {
        Context.licenseGateway.save(new License(VIEWING, user, codecast));
        List<CodecastSummariesViewModel> presentableCodecasts = useCase.presentCodecasts(user);
        CodecastSummariesViewModel presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isViewable);
      }
    }

    public class GivenOneDownloadLicenseForTheUser {
      private License downloadLicense;

      @Before
      public void setupDownloadLicense() {
        downloadLicense = new License(DOWNLOADING, user, codecast);
        Context.licenseGateway.save(downloadLicense);
      }

      @Test
      public void presentedCodecastIsDownloadable() throws Exception {
        List<CodecastSummariesViewModel> presentableCodecasts = useCase.presentCodecasts(user);
        CodecastSummariesViewModel presentableCodecast = presentableCodecasts.get(0);
        assertTrue(presentableCodecast.isDownloadable);
        assertFalse(presentableCodecast.isViewable);
      }
    }
  }
}
