package cleancoderscom.doubles;

import cleancoderscom.entities.User;
import cleancoderscom.usecases.gateways.GateKeeper;

public class InMemoryGateKeeper implements GateKeeper {
  private User loggedInUser;

  @Override
  public User getLoggedInUser() {
    return loggedInUser;
  }

  @Override
  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }
}
