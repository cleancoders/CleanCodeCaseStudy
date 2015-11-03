package cleancoderscom.fixtures;

import cleancoderscom.Context;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesOutputBoundarySpy;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesResponseModel;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.codecastSummaries.CodecastSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//OrderedQuery
public class OfCodeCasts {
  private List<Object> list(Object... objects) {
    return Arrays.asList(objects);
  }

  public List<Object> query() {
    User loggedInUser = Context.gateKeeper.getLoggedInUser();
    CodecastSummariesUseCase useCase = new CodecastSummariesUseCase();
    final CodecastSummariesOutputBoundarySpy presenter = new CodecastSummariesOutputBoundarySpy();
    useCase.summarizeCodecasts(loggedInUser, presenter);
    List<Object> queryResponse = new ArrayList<Object>();
    for (CodecastSummary summary : presenter.responseModel.getCodecastSummaries())
      queryResponse.add(makeRow(summary));
    return queryResponse;

  }

  private List<Object> makeRow(CodecastSummary summary) {
    return list(
        list("title", summary.title),
//        list("publication date", summary.publicationDate),
        list("picture", summary.title),
        list("description", summary.title),
        list("viewable", summary.isViewable ? "+" : "-"),
        list("downloadable", summary.isDownloadable ? "+" : "-"));
  }

}
