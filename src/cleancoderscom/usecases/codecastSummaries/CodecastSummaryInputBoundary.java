package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;

public interface CodecastSummaryInputBoundary {
  void summarizeCodecasts(User loggedInUser, CodecastSummaryOutputBoundary presenter);
}
