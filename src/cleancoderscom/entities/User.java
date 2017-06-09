package cleancoderscom.entities;

public class User extends Entity {
  private final String userName;

  public User(String userName) {
    this.userName = userName;
  }

  public String getUserName() {
    return userName;
  }

}
