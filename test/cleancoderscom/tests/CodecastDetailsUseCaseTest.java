package cleancoderscom.tests;

import cleancoderscom.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class CodecastDetailsUseCaseTest {

  private User user;

  @Before
  public void setup()
  {
    TestSetup.setupContext();
    user = Context.userGateway.save(new User("User"));
  }

  @Test
  public void createsCodecastDetailsPresentation() throws Exception {
    Codecast codecast = new Codecast();
    codecast.setTitle("Codecast");
    codecast.setPermalink("permalink-a");
    codecast.setPublicationDate(PresentCodecastUseCase.dateFormat.parse("1/2/2015"));
    Context.codecastGateway.save(codecast);

    CodecastDetailsUseCase userCase = new CodecastDetailsUseCase();
    PresentableCodecastDetails details = userCase.requestCodecastDetails(user, "permalink-a");

    assertEquals("Codecast", details.title);
    assertEquals("1/2/2015", details.publicationDate);
  }
}
