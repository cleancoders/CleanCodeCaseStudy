package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.Context;
import cleancoderscom.TestSetup;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Date;
import java.util.GregorianCalendar;

import static cleancoderscom.entities.License.LicenseType.DOWNLOADING;
import static cleancoderscom.entities.License.LicenseType.VIEWING;
import static org.junit.Assert.*;

@RunWith(HierarchicalContextRunner.class)
public class CodecastSummariesUseCaseTest {
  private User user;
  private CodecastSummariesUseCase useCase;
  private CodecastSummariesOutputBoundarySpy presenterSpy;

  @Before
  public void setUp() {
    TestSetup.setupContext();
    user = Context.userGateway.save(new User("User"));
    useCase = new CodecastSummariesUseCase();
    presenterSpy = new CodecastSummariesOutputBoundarySpy();

  }

  @Test
  public void useCaseWiring() throws Exception
  {
    useCase.summarizeCodecasts(user, presenterSpy);
    assertNotNull(presenterSpy.responseModel);
  }

  public class GivenNoCodecasts {
    @Test
    public void noneArePresented() throws Exception {
      useCase.summarizeCodecasts(user, presenterSpy);

      assertEquals(0, presenterSpy.responseModel.getCodecastSummaries().size());
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
      presenterSpy = new CodecastSummariesOutputBoundarySpy();

      useCase.summarizeCodecasts(user, presenterSpy);

      assertEquals(1, presenterSpy.responseModel.getCodecastSummaries().size());
      CodecastSummary codecastSummary = presenterSpy.responseModel.getCodecastSummaries().get(0);
      assertEquals("Some Title", codecastSummary.title);
      assertEquals(now, codecastSummary.publicationDate);
      assertEquals("permalink", codecastSummary.permalink);
    }

    public class GivenNoLicenses {
      @Test
      public void userCannotViewCodecast() throws Exception {
        assertFalse(CodecastSummariesUseCase.isLicensedFor(VIEWING, user, codecast));
      }

      @Test
      public void presentedCodecastShowsNotViewable() throws Exception {
        useCase.summarizeCodecasts(user, presenterSpy);
        CodecastSummary summary = presenterSpy.responseModel.getCodecastSummaries().get(0);
        assertFalse(summary.isViewable);
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
        assertTrue(CodecastSummariesUseCase.isLicensedFor(VIEWING, user, codecast));
      }

      @Test
      public void unlicensedUserCannotViewOtherUsersCodecast() throws Exception {
        User otherUser = new User("otherUser");
        Context.userGateway.save(otherUser);
        assertFalse(CodecastSummariesUseCase.isLicensedFor(VIEWING, otherUser, codecast));
      }

      @Test
      public void presentedCodecastIsViewable() throws Exception {
        Context.licenseGateway.save(new License(VIEWING, user, codecast));
        useCase.summarizeCodecasts(user, presenterSpy);
        CodecastSummary summary = presenterSpy.responseModel.getCodecastSummaries().get(0);
        assertTrue(summary.isViewable);
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
        useCase.summarizeCodecasts(user, presenterSpy);
        CodecastSummary summary = presenterSpy.responseModel.getCodecastSummaries().get(0);
        assertTrue(summary.isDownloadable);
        assertFalse(summary.isViewable);
      }
    }
  }
}
