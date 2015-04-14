package cleancoderscom;

import cleancoderscom.gateways.CodecastGateway;
import cleancoderscom.gateways.LicenseGateway;
import cleancoderscom.gateways.UserGateway;

public class Context {
  public static UserGateway userGateway;
  public static CodecastGateway codecastGateway;
  public static LicenseGateway licenseGateway;
  public static GateKeeper gateKeeper;
}
