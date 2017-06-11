package cleancoderscom.delivery.mvc;

import cleancoderscom.adapters.mvc.Request;

public class ParsedRequest implements Request {
  private String method = "";
  private String path = "";

  public ParsedRequest(String method, String path) {
    this.method = method;
    this.path = path;
  }
  private ParsedRequest() {
  }

  public static ParsedRequest fromRequestString(String requestString) {
    if (requestString == null)
      return new ParsedRequest();

    String[] parts = requestString.split(" ");
    String method = parts.length >= 1 ? parts[0] : "";
    String length = parts.length >= 2 ? parts[1] : "";

    return new ParsedRequest(method, length);
  }

  @Override
  public String getMethod() {
    return method;
  }

  @Override
  public String getPath() {
    return path;
  }
}
