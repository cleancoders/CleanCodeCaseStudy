package cleancoderscom;

import java.util.List;

public interface LicenseGateway {
  License save(License license);

  List<License> findLicensesForUserAndCodecast(User user, Codecast codecast);
}
