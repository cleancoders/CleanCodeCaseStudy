package cleancoderscom.usecases.codecastDetails;

import cleancoderscom.entities.Codecast;
import cleancoderscom.Context;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesPresenter;

public class CodecastDetailsUseCase {
  public PresentableCodecastDetails requestCodecastDetails(User loggedInUser, String permalink) {
    PresentableCodecastDetails details = new PresentableCodecastDetails();

    Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);
    if (codecast == null) {
      details.wasFound = false;
      return details;
    }
    else {
      details.wasFound = true;
//      CodecastSummariesPresenter.formatSummaryFields(loggedInUser, codecast, details);
      return details;
    }
  }
}
