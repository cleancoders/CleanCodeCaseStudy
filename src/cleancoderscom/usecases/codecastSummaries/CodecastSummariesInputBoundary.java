package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;

public interface CodecastSummariesInputBoundary
{
  void summarizeCodecasts(User loggedInUser, CodecastSummariesOutputBoundary presenter);
}
