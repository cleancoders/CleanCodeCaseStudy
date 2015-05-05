package cleancoderscom.http;

public class RequestParser {


  public ParsedRequest parse(String requestString) {
    ParsedRequest request = new ParsedRequest();
    if(requestString != null) {
      String[] parts = requestString.split(" ");
      if (parts.length >= 1)
        request.method = parts[0];
      if (parts.length >= 2)
        request.path = parts[1];
    }
    return request;
  }
}
