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

}
