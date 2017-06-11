package cleancoderscom.doubles;

import cleancoderscom.entities.Codecast;
import cleancoderscom.usecases.gateways.CodecastGateway;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InMemoryCodecastGateway extends GatewayUtilities<Codecast> implements CodecastGateway {
  public List<Codecast> findAllCodecastsSortedChronologically() {
    return getEntities().stream()
      .sorted(Comparator.comparing(Codecast::getPublicationDate))
      .collect(Collectors.toList());
  }

  public Codecast findCodecastByTitle(String codecastTitle) {
    return getEntities().stream().filter(codecast -> codecast.getTitle().equals(codecastTitle)).findFirst().orElse(null);
  }

  @Override
  public Codecast findCodecastByPermalink(String permalink) {
    return getEntities().stream().filter(codecast -> Objects.equals(codecast.getPermalink(), permalink)).findFirst().orElse(null);
  }
}
