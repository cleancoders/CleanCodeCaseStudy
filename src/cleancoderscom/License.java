package cleancoderscom;

public class License extends Entity {
  private User user;
  private Codecast codecast;

  public License(User user, Codecast codecast) {

    this.user = user;
    this.codecast = codecast;
  }

  public User getUser() {
    return user;
  }

  public Codecast getCodecast() {
    return codecast;
  }
}
