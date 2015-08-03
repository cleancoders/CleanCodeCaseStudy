package cleancoderscom.http;

import java.util.HashMap;
import java.util.Map;

public class Router {
  private Map<String, Controller> routes = new HashMap<>();

  public void addPath(String path, Controller controller) {
    routes.put(path, controller);
  }

  public String route(ParsedRequest request) {
    String[] parts = request.path.split("/");
    String controllerKey = parts.length > 1 ? parts[1] : "";
    Controller controller = routes.get(controllerKey);
    if(controller == null) {
      return "HTTP/1.1 404 OK\n";
    } else {
      return controller.handle(request);
    }
  }
}
