package cleancoderscom.usecases.codecastDetails;

import cleancoderscom.entities.Codecast;
import cleancoderscom.Context;
import cleancoderscom.entities.User;

public class CodecastDetailsUseCase {
  public PresentableCodecastDetails requestCodecastDetails(User loggedInUser, String permalink) {
    PresentableCodecastDetails details = new PresentableCodecastDetails();

    Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);
    details.wasFound = codecast != null;
    return details;
  }
}
