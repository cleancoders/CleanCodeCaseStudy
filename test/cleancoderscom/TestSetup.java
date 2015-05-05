package cleancoderscom;

import cleancoderscom.*;
import cleancoderscom.entities.Codecast;
import cleancoderscom.entities.License;
import cleancoderscom.entities.User;
import cleancoderscom.doubles.InMemoryCodecastGateway;
import cleancoderscom.doubles.InMemoryLicenseGateway;
import cleancoderscom.doubles.InMemoryUserGateway;

import java.util.Date;

import static cleancoderscom.entities.License.LicenseType.*;

public class TestSetup {
  public static void setupContext() {
    Context.userGateway = new InMemoryUserGateway();
    Context.licenseGateway = new InMemoryLicenseGateway();
    Context.codecastGateway = new InMemoryCodecastGateway();
    Context.gateKeeper = new GateKeeper();
  }

  public static void setupSampleData() {
    setupContext();

    User bob = new User("Bob");
    User micah = new User("Micah");

    Context.userGateway.save(bob);
    Context.userGateway.save(micah);

    Codecast e1 = new Codecast();
    e1.setTitle("Episode 1 - The Beginning");
    e1.setPublicationDate(new Date());
    e1.setPermalink("e1");

    Codecast e2 = new Codecast();
    e2.setTitle("Episode 2 - The Continuation");
    e2.setPublicationDate(new Date(e1.getPublicationDate().getTime() + 1));
    e2.setPermalink("e2");

    Context.codecastGateway.save(e1);
    Context.codecastGateway.save(e2);

    License bobE1 = new License(VIEWING, bob, e1);
    License bobE2 = new License(VIEWING, bob, e2);

    Context.licenseGateway.save(bobE1);
    Context.licenseGateway.save(bobE2);
  }
}
