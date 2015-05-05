package cleancoderscom.http;

import java.util.HashMap;
import java.util.Map;

public class Router {
  private Map<String, Controller> routes = new HashMap<>();

  public void addPath(String path, Controller controller) {
    routes.put(path, controller);
  }

  public String route(ParsedRequest request) {
    System.out.println("request.path = " + request.path);
    String[] parts = request.path.split("/");
    String controllerKey =  parts.length > 1 ? parts[1] : "";
    Controller controller = routes.get(controllerKey);
    return controller == null ? null : controller.handle(request);
  }
}
