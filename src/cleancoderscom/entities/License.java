package cleancoderscom.entities;

public class License extends Entity {
  public enum LicenseType {DOWNLOADING, VIEWING}
  private final LicenseType type;
  private final User user;
  private final Codecast codecast;

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
