package cleancoderscom.tests;

import cleancoderscom.Context;
import cleancoderscom.GateKeeper;
import cleancoderscom.tests.doubles.InMemoryCodecastGateway;
import cleancoderscom.tests.doubles.InMemoryLicenseGateway;
import cleancoderscom.tests.doubles.InMemoryUserGateway;

public class TestSetup {
  public static void setupContext() {
    Context.userGateway = new InMemoryUserGateway();
    Context.licenseGateway = new InMemoryLicenseGateway();
    Context.codecastGateway = new InMemoryCodecastGateway();
    Context.gateKeeper = new GateKeeper();
  }
}
