package cleancoderscom.fixtures;

import cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewModel.ViewableCodecastSummary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//OrderedQuery
public class OfCodeCasts {
  private List<Object> list(Object... objects) {
    return Arrays.asList(objects);
  }

  public List<Object> query() {
    final List<ViewableCodecastSummary> viewableCodecasts = CodecastPresentation.loadViewableCodecasts();
    List<Object> queryResponse = new ArrayList<Object>();
    for (ViewableCodecastSummary summary : viewableCodecasts)
      queryResponse.add(makeRow(summary));
    return queryResponse;

  }

  private List<Object> makeRow(ViewableCodecastSummary summary) {
    return list(
        list("title", summary.title),
        list("publication date", summary.publicationDate),
        list("picture", summary.title),
        list("description", summary.title),
        list("viewable", summary.isViewable ? "+" : "-"),
        list("downloadable", summary.isDownloadable ? "+" : "-"));
  }

}
