package cleancoderscom.usecases.gateways;

import cleancoderscom.entities.User;

public interface GateKeeper {
    void setLoggedInUser(User loggedInUser);

    User getLoggedInUser();
}
