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
      queryResponse.add(makeRow(pcc.title, pcc.title, pcc.title, pcc.isViewable, false));
    return queryResponse;

  }

  private List<Object> makeRow(String title, String picture, String description, boolean viewable, boolean downloadable) {
    return list(
      new Object[]{list("title", title),
        list("picture", picture),
        list("description", description),
        list("viewable", viewable ? "+" : "-"),
        list("downloadable", downloadable ? "+" : "-")}
    );
  }

}
