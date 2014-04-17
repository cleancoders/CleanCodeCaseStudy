package cleancoderscom;

import java.util.List;

public interface Gateway {
  List<Codecast> findAllCodecasts();

  void delete(Codecast codecast);

  void save(Codecast codecast);

  void save(User user);

  User findUser(String username);
}
