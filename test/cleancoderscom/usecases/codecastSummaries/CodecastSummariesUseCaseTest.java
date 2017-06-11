package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.usecases.entities.CodecastSummary;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.TestSetup;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.time.LocalDate;

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
    public void noneAreReturned() throws Exception {
      CodecastSummariesResponse response = useCase.apply(user);

      assertEquals(0, response.getCodecastSummaries().size());
    }
  }

  public class GivenOneCodecast {
    private Codecast codecast;

    @Before
    public void setupCodecast() {
      codecast = Context.codecastGateway.save(new Codecast());
    }

    @Test
    public void oneIsReturned() throws Exception {
      codecast.setTitle("Some Title");
      LocalDate now = LocalDate.of(2014, 4, 19);
      codecast.setPublicationDate(now);
      codecast.setPermalink("permalink");
      Context.codecastGateway.save(codecast);
      CodecastSummariesResponse codecastSummaryResponse = useCase.apply(user);

      CodecastSummary codecastSummary = codecastSummaryResponse.getCodecastSummaries().get(0);
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
      public void returnedCodecastShowsNotViewable() throws Exception {
        CodecastSummariesResponse response = useCase.apply(user);
        assertFalse(response.getCodecastSummaries().get(0).isViewable);
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
      public void returnedCodecastIsViewable() throws Exception {
        Context.licenseGateway.save(new License(VIEWING, user, codecast));
        CodecastSummariesResponse response = useCase.apply(user);
        CodecastSummary summary = response.getCodecastSummaries().get(0);
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
      public void returnedCodecastIsDownloadable() throws Exception {
        CodecastSummariesResponse response = useCase.apply(user);
        CodecastSummary summary = response.getCodecastSummaries().get(0);
        assertTrue(summary.isDownloadable);
        assertFalse(summary.isViewable);
      }
    }
  }
}
