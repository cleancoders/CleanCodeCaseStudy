package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;

public class CodecastSummaryInputBoundarySpy implements CodecastSummaryInputBoundary {
  public boolean summarizeCodecastsWasCalled = false;
  public User requestedUser;
  public CodecastSummaryOutputBoundary outputBoundary;

  public void summarizeCodecasts(User loggedInUser, CodecastSummaryOutputBoundary presenter) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
    outputBoundary = presenter;
  }
}
