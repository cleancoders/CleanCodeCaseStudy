package cleancoderscom.fixtures;

import cleancoderscom.usecases.codecastDetails.CodecastDetailsUseCase;
import cleancoderscom.usecases.gateways.Context;

public class CodecastDetails {

  private CodecastDetailsUseCase useCase = new CodecastDetailsUseCase();

  public boolean requestCodecast(String permalink) {
    CodecastDetailsUseCase.Request request = new CodecastDetailsUseCase.Request();
    request.permalink = permalink;
    request.loggedInUser = Context.gateKeeper.getLoggedInUser();
    useCase.apply(request);

    return useCase.apply(request).value;
  }

  public boolean codecastDetailsOfferPurchaseOf(String licenseType) {
//    return
//      (licenseType.equalsIgnoreCase("viewing") && !details.isViewable) ||
//      (licenseType.equalsIgnoreCase("download") && !details.isDownloadable);
    return false;
  }

  public String codecastDetailsTitle() {
//    return details.title;
    return "tilt";
  }

  public String codecastDetailsDate() {
//    return details.publicationDate;
    return "tilt";
  }
}
