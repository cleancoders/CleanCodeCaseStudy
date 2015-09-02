package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;

public class CodecastSummaryInputBoundarySpy implements CodecastSummaryInputBoundary {
  public boolean summarizeCodecastsWasCalled = false;
  public User requestedUser;

  public void summarizeCodecasts(User loggedInUser) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
  }
}
