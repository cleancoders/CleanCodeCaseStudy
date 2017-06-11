package cleancoderscom.usecases.entities;

import java.util.ArrayList;
import java.util.List;

public class CodecastSummariesResponse {
  private List<CodecastSummary> codecastSummaries;

  public CodecastSummariesResponse() {
    codecastSummaries = new ArrayList<>();
  }

  public List<CodecastSummary> getCodecastSummaries() {
    return codecastSummaries;
  }

  public void addCodecastSummary(CodecastSummary summary) {
    codecastSummaries.add(summary);
  }
}
