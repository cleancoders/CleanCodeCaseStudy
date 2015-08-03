package cleancoderscom.http;


public interface Controller {
  String handle(ParsedRequest request);

  static String makeResponse(String content) {
    return "HTTP/1.1 200 OK\n" +
        "Content-Length: " + content.length() + "\n" +
        "\n" +
        content;
  }
}