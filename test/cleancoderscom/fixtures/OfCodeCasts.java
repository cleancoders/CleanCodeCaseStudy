package cleancoderscom.fixtures;

import cleancoderscom.usecases.codecastSummaries.CodecastSummariesViewModel.ViewableCodecastSummary;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//OrderedQuery
public class OfCodeCasts {
  private List<Object> list(Object... objects) {
    return Arrays.asList(objects);
  }

  public List<Object> query() {
    return CodecastPresentation.loadViewableCodecasts().stream()
        .map(this::makeRow)
        .collect(Collectors.toList());

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
