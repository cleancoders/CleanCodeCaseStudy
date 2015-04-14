package cleancoderscom.doubles;

import cleancoderscom.entities.Codecast;
import cleancoderscom.gateways.CodecastGateway;

import java.util.*;

public class InMemoryCodecastGateway extends GatewayUtilities<Codecast> implements CodecastGateway {
  public List<Codecast> findAllCodecastsSortedChronologically()
  {
    List<Codecast> sortedCodecasts = new ArrayList<Codecast>(getEntities());
    Collections.sort(sortedCodecasts, new Comparator<Codecast>() {
      public int compare(Codecast o1, Codecast o2) {
        return o1.getPublicationDate().compareTo(o2.getPublicationDate());
      }
    });
    return sortedCodecasts;
  }

  public Codecast findCodecastByTitle(String codecastTitle) {
    for (Codecast codecast : getEntities())
      if (codecast.getTitle().equals(codecastTitle))
        return codecast;
    return null;
  }

  @Override
  public Codecast findCodecastByPermalink(String permalink) {
    for (Codecast codecast : getEntities())
      if (Objects.equals(codecast.getPermalink(), permalink))
        return codecast;
    return null;
  }
}
