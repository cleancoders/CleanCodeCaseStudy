package cleancoderscom.http;


public interface Controller {
  String handle(ParsedRequest request);
}