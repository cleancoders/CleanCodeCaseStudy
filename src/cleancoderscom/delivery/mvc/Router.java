package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.Request;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Router {
  private static final String NOT_FOUND = "HTTP/1.1 404 OK\n";
  private Map<String, Function<Request, String>> routes = new HashMap<>();

  public void addPath(String path, Function<Request, String> controller) {
    routes.put(path, controller);
  }

  public String route(Request request) {
    if (request.getPath() == null)
      return NOT_FOUND;

    String[] parts = request.getPath().split("/");
    String controllerKey = parts.length > 1 ? parts[1] : "";
    Function<Request, String> controller = routes.getOrDefault(controllerKey, x -> NOT_FOUND);
    return controller.apply(request);
  }
}
