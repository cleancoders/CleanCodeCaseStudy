package cleancoderscom.usecases.gateways;

import cleancoderscom.entities.User;

public interface GateKeeper {
  User getLoggedInUser();

  void setLoggedInUser(User loggedInUser);
}
