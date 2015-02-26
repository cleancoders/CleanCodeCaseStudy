package cleancoderscom.tests.fixtures;

import cleancoderscom.CodecastDetailsUseCase;
import cleancoderscom.Context;

public class CodecastDetails {

  private CodecastDetailsUseCase useCase = new CodecastDetailsUseCase();

  public boolean requestCodecast(String permalink)
  {
    useCase.requestCodecastDetails(Context.gateKeeper.getLoggedInUser(), permalink);
    return false;
  }

  public boolean codecastDetailsOfferPurchaseOf(String licenseType)
  {
    return false;
  }

  public String codecastDetailsTitle() {
    return "TILT";
  }

  public String codecastDetailsDate() {
    return "TILT";
  }
}
