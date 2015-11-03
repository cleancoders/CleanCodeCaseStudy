package cleancoderscom.usecases.codecastSummaries;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummariesViewModel
{

  public ArrayList<ViewableCodecastSummary> viewableCodecastSummaries = new ArrayList<>();

  public void addModel(ViewableCodecastSummary viewableCodecastSummary) {
    viewableCodecastSummaries.add(viewableCodecastSummary);
  }

  public List<ViewableCodecastSummary> getViewableCodecasts() {
    return viewableCodecastSummaries;
  }

  public static class ViewableCodecastSummary {
    public String title;
    public String permalink;
    public String publicationDate;
    public boolean isDownloadable;
    public boolean isViewable;
  }
}
