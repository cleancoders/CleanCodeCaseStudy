package cleancoderscom.delivery.http;

import cleancoderscom.delivery.mvc.ParsedRequest;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParsedRequestTest {

  @Test
  public void emptyRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("");
    assertEquals("", r.getMethod());
    assertEquals("", r.getPath());
  }

  @Test
  public void nullRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString(null);
    assertEquals("", r.getMethod());
    assertEquals("", r.getPath());
  }

  @Test
  public void requestNonEmptyRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("GET /foo/bar HTTP/1.1");
    assertEquals("GET", r.getMethod());
    assertEquals("/foo/bar", r.getPath());
  }

  @Test
  public void partialRequest() throws Exception {
    ParsedRequest r = ParsedRequest.fromRequestString("GET");
    assertEquals("GET", r.getMethod());
    assertEquals("", r.getPath());
  }
}
