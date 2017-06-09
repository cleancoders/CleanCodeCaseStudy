package cleancoderscom.doubles;

import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import cleancoderscom.gateways.LicenseGateway;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryLicenseGateway extends GatewayUtilities<License> implements LicenseGateway {
  public List<License> findLicensesForUserAndCodecast(User user, Codecast codecast) {
    return getEntities().stream()
        .filter(license -> isSameUserAndCodecast(user, codecast, license))
        .collect(Collectors.toList());
  }

  private boolean isSameUserAndCodecast(User user, Codecast codecast, License license) {
    return license.getUser().isSame(user) && license.getCodecast().isSame(codecast);
  }
}
