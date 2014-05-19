package cleancoderscom;

public class License extends Entity {
  public enum LicenseType {DOWNLOADING, VIEWING;}
  private LicenseType type;

  private User user;
  private Codecast codecast;
  public License(LicenseType type, User user, Codecast codecast) {
    this.type = type;
    this.user = user;
    this.codecast = codecast;
  }

  public LicenseType getType() {
    return type;
  }

  public User getUser() {
    return user;
  }

  public Codecast getCodecast() {
    return codecast;
  }
}
