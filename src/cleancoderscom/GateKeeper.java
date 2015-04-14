package cleancoderscom;

import cleancoderscom.entities.User;

public class GateKeeper {
  private User loggedInUser;

  public void setLoggedInUser(User loggedInUser) {
    this.loggedInUser = loggedInUser;
  }

  public User getLoggedInUser() {
    return loggedInUser;
  }
}
