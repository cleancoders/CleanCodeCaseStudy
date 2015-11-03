package cleancoderscom.usecases.codecastSummaries;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummariesResponseModel
{
  private List<CodecastSummary> codecastSummaries;
  public boolean isViewable;
  public String title;
  public String publicationDate;
  public boolean isDownloadable;
  public String permalink;

  public CodecastSummariesResponseModel() {
    codecastSummaries = new ArrayList<>();
  }

  public List<CodecastSummary> getCodecastSummaries() {
    return codecastSummaries;
  }

  public void addCodecastSummary(CodecastSummary summary) {
    codecastSummaries.add(summary);
  }
}
