package cleancoderscom.fixtures;

import cleancoderscom.PresentCodecastUseCase;
import cleancoderscom.PresentableCodecast;
import cleancoderscom.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//OrderedQuery
public class OfCodeCasts {
  private List<Object> list(Object... objects) {
    return Arrays.asList(objects);
  }

  public List<Object> query() {
    User loggedInUser = CodecastPresentation.gateKeeper.getLoggedInUser();
    PresentCodecastUseCase useCase = new PresentCodecastUseCase();
    List<PresentableCodecast> presentableCodecasts = useCase.presentCodecasts(loggedInUser);
    List<Object> queryResponse = new ArrayList<Object>();
    for (PresentableCodecast pcc : presentableCodecasts)
      queryResponse.add(makeRow(pcc));
    return queryResponse;

  }

  private List<Object> makeRow(PresentableCodecast pc) {
    return list(
      new Object[]{list("title", pc.title),
        list("publication date", pc.publicationDate),
        list("picture", pc.title),
        list("description", pc.title),
        list("viewable", pc.isViewable ? "+" : "-"),
        list("downloadable", false ? "+" : "-")}
    );
  }

}
