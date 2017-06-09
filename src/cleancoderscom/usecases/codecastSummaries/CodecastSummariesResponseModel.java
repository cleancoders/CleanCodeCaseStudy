package cleancoderscom.usecases.codecastSummaries;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummariesResponseModel {
  private List<CodecastSummary> codecastSummaries;

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
