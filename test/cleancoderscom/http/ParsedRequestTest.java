package cleancoderscom.http;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParsedRequestTest {

  @Test
  public void emptyRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("");
    assertEquals("", r.method);
    assertEquals("", r.path);
  }

  @Test
  public void nullRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString(null);
    assertEquals("", r.method);
    assertEquals("", r.path);
  }

  @Test
  public void requestNonEmptyRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("GET /foo/bar HTTP/1.1");
    assertEquals("GET", r.method);
    assertEquals("/foo/bar", r.path);
  }

  @Test
  public void partialRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("GET");
    assertEquals("GET", r.method);
    assertEquals("", r.path);
  }
}
