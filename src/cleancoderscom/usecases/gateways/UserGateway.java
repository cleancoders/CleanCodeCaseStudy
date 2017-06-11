package cleancoderscom.usecases.gateways;

import cleancoderscom.entities.User;

public interface UserGateway {
  User save(User user);

  User findUserByName(String username);
}
