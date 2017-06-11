package cleancoderscom.usecases.gateways;

import cleancoderscom.entities.Codecast;

import java.util.List;

public interface CodecastGateway {
  List<Codecast> findAllCodecastsSortedChronologically();

  void delete(Codecast codecast);

  Codecast save(Codecast codecast);

  Codecast findCodecastByTitle(String codecastTitle);

  Codecast findCodecastByPermalink(String permalink);
}
