package cleancoderscom.doubles;

import cleancoderscom.entities.User;
import cleancoderscom.usecases.gateways.UserGateway;

public class InMemoryUserGateway extends GatewayUtilities<User> implements UserGateway {
  public User findUserByName(String username) {
    return getEntities().stream()
        .filter(user -> user.getUserName().equals(username))
        .findFirst()
        .orElse(null);
  }
}
