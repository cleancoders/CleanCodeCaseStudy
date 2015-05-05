package cleancoderscom.http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {

  @Test
  public void emptyRequest() throws Exception {
    RequestParser parser = new RequestParser();
    ParsedRequest r = parser.parse("");
    assertEquals("", r.method);
    assertEquals("", r.path);
  }

  @Test
  public void nullRequest() throws Exception {
    RequestParser parser = new RequestParser();
    ParsedRequest r = parser.parse(null);
    assertEquals("", r.method);
    assertEquals("", r.path);
  }

  @Test
  public void requestNonEmptyRequest() throws Exception {
    RequestParser parser = new RequestParser();
    ParsedRequest r = parser.parse("GET /foo/bar HTTP/1.1");
    assertEquals("GET", r.method);
    assertEquals("/foo/bar", r.path);
  }

  @Test
  public void partialRequest() throws Exception {
    RequestParser parser = new RequestParser();
    ParsedRequest r = parser.parse("GET");
    assertEquals("GET", r.method);
    assertEquals("", r.path);
  }


}
