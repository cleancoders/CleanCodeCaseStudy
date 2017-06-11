package cleancoderscom.usecases.codecastDetails;

import cleancoderscom.TestSetup;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.gateways.Context;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodecastDetailsUseCaseTest {

  private User user;

  @Before
  public void setup() {
    TestSetup.setupContext();
    user = Context.userGateway.save(new User("User"));
  }

  // TODO: 11/3/15 Start here.  Get this passing.
//  @Test
//  public void createsCodecastDetailsPresentation() throws Exception {
//    Codecast codecast = new Codecast();
//    codecast.setTitle("Codecast");
//    codecast.setPermalink("permalink-a");
//    codecast.setPublicationDate(Presenter.dateFormat.parse("1/2/2015"));
//    Context.codecastGateway.save(codecast);
//
//    CodecastDetailsUseCase userCase = new CodecastDetailsUseCase();
//    PresentableCodecastDetails details = userCase.requestCodecastDetails(user, "permalink-a");
//
//    assertEquals("Codecast", details.title);
//    assertEquals("1/02/2015", details.publicationDate);
//  }

  @Test
  public void doesntCrashOnMissingCodecast() throws Exception {
    CodecastDetailsUseCase userCase = new CodecastDetailsUseCase();
    CodecastDetailsUseCase.Request request = new CodecastDetailsUseCase.Request();
    request.loggedInUser = user;
    request.permalink = "missing";
    CodecastDetailsUseCase.Response response = userCase.apply(request);

    assertEquals(false, response.value);
  }
}
