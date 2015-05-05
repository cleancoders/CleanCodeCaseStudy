package cleancoderscom.http;

import java.util.HashMap;
import java.util.Map;

public class Router {
  private Map<String, Controller> routes = new HashMap<String, Controller>;

  public void addPath(String path, Controller controller) {
    routes.put(path, controller);
  }
}
