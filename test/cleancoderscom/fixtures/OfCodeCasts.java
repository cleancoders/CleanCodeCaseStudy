package cleancoderscom.fixtures;

import cleancoderscom.Context;
import cleancoderscom.usecases.codecastSummaries.CodecastSummariesUseCase;
import cleancoderscom.usecases.codecastSummaries.PresentableCodecastSummary;
import cleancoderscom.entities.User;

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
    List<PresentableCodecastSummary> presentableCodecasts = useCase.presentCodecasts(loggedInUser);
    List<Object> queryResponse = new ArrayList<Object>();
    for (PresentableCodecastSummary pcc : presentableCodecasts)
      queryResponse.add(makeRow(pcc));
    return queryResponse;

  }

  private List<Object> makeRow(PresentableCodecastSummary pc) {
    return list(
      new Object[]{list("title", pc.title),
        list("publication date", pc.publicationDate),
        list("picture", pc.title),
        list("description", pc.title),
        list("viewable", pc.isViewable ? "+" : "-"),
        list("downloadable", pc.isDownloadable ? "+" : "-")}
    );
  }

}
