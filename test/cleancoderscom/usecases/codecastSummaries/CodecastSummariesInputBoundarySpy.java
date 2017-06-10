package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.adapters.mvc.OutputBoundary;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;
import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;

public class CodecastSummariesInputBoundarySpy implements UseCase<User, CodecastSummariesResponse>
{
  public boolean summarizeCodecastsWasCalled = false;
  public User requestedUser;
  public OutputBoundary outputBoundary;

  public void summarizeCodecasts(User loggedInUser, OutputBoundary presenter) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
    outputBoundary = presenter;
  }

  @Override
  public CodecastSummariesResponse apply(User loggedInUser) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
    //outputBoundary = presenter;
    return null;
  }
}
