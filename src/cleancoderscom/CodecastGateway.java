package cleancoderscom;

import java.util.List;

public interface CodecastGateway {
  List<Codecast> findAllCodecastsSortedChronologically();

  void delete(Codecast codecast);

  Codecast save(Codecast codecast);

  Codecast findCodecastByTitle(String codecastTitle);
}
