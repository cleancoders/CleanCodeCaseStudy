package cleancoderscom.tests.doubles;

import cleancoderscom.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GatewayUtilities<T extends Entity> {
  private List<T> entities;

  public GatewayUtilities() {
    this.entities = new ArrayList<T>();
  }

  protected T establishId(T entity) {
    if(entity.getId() == null)
      entity.setId(UUID.randomUUID().toString());
    return entity;
  }

  @SuppressWarnings("unchecked")
  public List<T> getEntities() {
    List<T> newEntities = new ArrayList<T>();
    for (T entity : entities)
      try {
        newEntities.add((T) entity.clone());
      } catch (CloneNotSupportedException e) {
        throw new UnCloneableEntity();
      }
    return newEntities;
  }

  public T save(T entity) {
    entities.add(establishId(entity));
    return entity;
  }

  public void delete(T entity) {
    entities.remove(entity);
  }

  private static class UnCloneableEntity extends RuntimeException {
  }
}
