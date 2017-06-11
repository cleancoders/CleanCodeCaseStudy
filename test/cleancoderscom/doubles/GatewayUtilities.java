package cleancoderscom.doubles;

import cleancoderscom.entities.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class GatewayUtilities<T extends Entity> {
  private HashMap<String, T> entities;

  public GatewayUtilities() {
    this.entities = new HashMap<>();
  }

  public List<T> getEntities() {
    return entities.values().stream()
      .map(this::clone)
      .collect(Collectors.toList());
  }

  @SuppressWarnings("unchecked")
  private T clone(T entity) {
    try {
      return (T) entity.clone();
    } catch (CloneNotSupportedException e) {
      throw new UnCloneableEntity();
    }
  }

  public T save(T entity) {
    if (entity.getId() == null)
      entity.setId(UUID.randomUUID().toString());
    String id = entity.getId();
    saveCloneInMap(id, entity);
    return entity;
  }

  @SuppressWarnings("unchecked")
  private void saveCloneInMap(String id, T entity) {
    try {
      entities.put(id, (T) entity.clone());
    } catch (CloneNotSupportedException e) {
      throw new UnCloneableEntity();
    }
  }

  public void delete(T entity) {
    entities.remove(entity.getId());
  }

  private static class UnCloneableEntity extends RuntimeException {
  }
}
