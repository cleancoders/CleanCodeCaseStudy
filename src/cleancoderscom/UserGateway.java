package cleancoderscom;

public interface UserGateway {
  User save(User user);

  User findUserByName(String username);
}
