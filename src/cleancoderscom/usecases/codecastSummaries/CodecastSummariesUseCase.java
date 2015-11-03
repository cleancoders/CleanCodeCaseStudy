package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.*;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummariesUseCase implements CodecastSummariesInputBoundary {

  public List<CodecastSummariesResponseModel> presentCodecasts(User loggedInUser) {
    ArrayList<CodecastSummariesResponseModel> presentableCodecasts = new ArrayList<CodecastSummariesResponseModel>();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    for (Codecast codecast : allCodecasts)
      presentableCodecasts.add(CodecastSummariesPresenter.formatCodecast(loggedInUser, codecast));

    return presentableCodecasts;
  }


  public static boolean isLicensedFor(License.LicenseType licenseType, User user, Codecast codecast) {
    List<License> licenses = Context.licenseGateway.findLicensesForUserAndCodecast(user, codecast);
    for (License l : licenses) {
      if (l.getType() == licenseType)
        return true;
    }
    return false;
  }

  @Override
  public void summarizeCodecasts(User loggedInUser, CodecastSummariesOutputBoundary presenter)
  {
    CodecastSummariesResponseModel responseModel = new CodecastSummariesResponseModel();
    List<Codecast> allCodecasts = Context.codecastGateway.findAllCodecastsSortedChronologically();

    for (Codecast codecast : allCodecasts)
      responseModel.addCodecastSummary(summarizeCodecast(codecast));
    presenter.present(responseModel);
  }

  private CodecastSummary summarizeCodecast(Codecast codecast) {
    CodecastSummary summary = new CodecastSummary();
    summary.title = codecast.getTitle();
    return summary;
  }

}

