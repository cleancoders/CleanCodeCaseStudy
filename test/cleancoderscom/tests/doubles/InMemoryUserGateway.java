package cleancoderscom.tests.doubles;

import cleancoderscom.User;
import cleancoderscom.UserGateway;

public class InMemoryUserGateway extends GatewayUtilities<User> implements UserGateway {
  public User findUserByName(String username) {
    for (User user : getEntities()) {
      if (user.getUserName().equals(username))
        return user;
    }
    return null;
  }
}
