package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;

public class CodecastSummariesInputBoundarySpy implements CodecastSummariesInputBoundary
{
  public boolean summarizeCodecastsWasCalled = false;
  public User requestedUser;
  public CodecastSummariesOutputBoundary outputBoundary;

  public void summarizeCodecasts(User loggedInUser, CodecastSummariesOutputBoundary presenter) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
    outputBoundary = presenter;
  }
}
