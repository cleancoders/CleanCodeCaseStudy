package cleancoderscom;

import java.util.Objects;

public class Entity {
  private String id;

  public boolean isSame(Entity entity) {
    return id != null && Objects.equals(id, entity.id);
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }
}
