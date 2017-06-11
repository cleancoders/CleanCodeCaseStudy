package cleancoderscom.usecases.codecastSummaries;

import cleancoderscom.entities.User;
import cleancoderscom.usecases.core.UseCase;
import cleancoderscom.usecases.entities.CodecastSummariesResponse;

public class CodecastSummariesInputBoundarySpy implements UseCase<User, CodecastSummariesResponse> {
  public boolean summarizeCodecastsWasCalled = false;
  public User requestedUser;

  @Override
  public CodecastSummariesResponse apply(User loggedInUser) {
    summarizeCodecastsWasCalled = true;
    requestedUser = loggedInUser;
    return null;
  }
}
