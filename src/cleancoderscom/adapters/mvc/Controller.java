package cleancoderscom.adapters.mvc;

@FunctionalInterface
public interface Controller {
  String handle(Request request);
}