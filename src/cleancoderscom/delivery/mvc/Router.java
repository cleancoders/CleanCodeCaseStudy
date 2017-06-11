package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.Controller;
import cleancoderscom.adapters.mvc.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {
  private static final String NOT_FOUND = "HTTP/1.1 404 OK\n";
  private Map<String, Controller<Request, String>> routes = new HashMap<>();

  public void addPath(String path, Controller<Request, String> controller) {
    routes.put(path, controller);
  }

  public String route(Request request) {
    String[] parts = request.getPath().split("/");
    String controllerKey = parts.length > 1 ? parts[1] : "";
    Controller<Request, String> controller = routes.getOrDefault(controllerKey, notFoundController());
    return controller.handle(request);
  }

  private Controller<Request, String> notFoundController() {
    return () -> x -> NOT_FOUND;
  }
}
