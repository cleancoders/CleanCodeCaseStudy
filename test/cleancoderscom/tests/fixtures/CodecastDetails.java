package cleancoderscom.tests.fixtures;

import cleancoderscom.CodecastDetailsUseCase;
import cleancoderscom.Context;
import cleancoderscom.PresentableCodecastDetails;

public class CodecastDetails {

  private CodecastDetailsUseCase useCase = new CodecastDetailsUseCase();
  private PresentableCodecastDetails details;

  public boolean requestCodecast(String permalink)
  {
    details = useCase.requestCodecastDetails(Context.gateKeeper.getLoggedInUser(), permalink);
    return details != null;
  }

  public boolean codecastDetailsOfferPurchaseOf(String licenseType)
  {
    return false;
  }

  public String codecastDetailsTitle() {
    return details.title;
  }

  public String codecastDetailsDate() {
    return details.publicationDate;
  }
}
