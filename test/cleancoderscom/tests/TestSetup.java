package cleancoderscom.tests;

import cleancoderscom.Context;
import cleancoderscom.tests.doubles.InMemoryCodecastGateway;
import cleancoderscom.tests.doubles.InMemoryLicenseGateway;
import cleancoderscom.tests.doubles.InMemoryUserGateway;

public class TestSetup {
  public static void addInMemoryGatewaysToContext() {
    Context.userGateway = new InMemoryUserGateway();
    Context.licenseGateway = new InMemoryLicenseGateway();
    Context.codecastGateway = new InMemoryCodecastGateway();
  }
}
