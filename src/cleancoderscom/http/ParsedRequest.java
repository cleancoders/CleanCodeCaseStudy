package cleancoderscom.http;

public class ParsedRequest {
  public String method = "";
  public String path = "";

  public ParsedRequest(String method, String path) {
    this.method = method;
    this.path = path;
  }

  public ParsedRequest() {
  }
}
