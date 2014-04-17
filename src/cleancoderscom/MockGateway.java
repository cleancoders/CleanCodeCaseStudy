package cleancoderscom;

import java.util.ArrayList;
import java.util.List;

public class MockGateway implements Gateway {

  private List<Codecast> codecasts;
  private List<User> users;

  public MockGateway() {
    codecasts = new ArrayList<Codecast>();
    users = new ArrayList<User>();
  }

  public List<Codecast> findAllCodecasts() {
    return codecasts;
  }

  public void delete(Codecast codecast) {
    codecasts.remove(codecast);
  }

  public void save(Codecast codecast) {
    codecasts.add(codecast);
  }

  public void save(User user) {
    users.add(user);
  }

  public User findUser(String username) {
    for (User user : users) {
      if (user.getUserName().equals(username))
        return user;
    }
    return null;
  }
}
