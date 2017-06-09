package cleancoderscom.usecases.gateways;

import cleancoderscom.entities.User;

/**
 * Created by alex on 10/06/2017.
 */
public interface GateKeeper {
    void setLoggedInUser(User loggedInUser);

    User getLoggedInUser();
}
