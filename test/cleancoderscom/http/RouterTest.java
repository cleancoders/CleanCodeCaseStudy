package cleancoderscom.http;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {
  private ParsedRequest actualRequest;
  private Router router;

  @Before
  public void setUp() {
    router = new Router();
  }

  @Test
  public void simplePath() throws Exception {
    router.addPath("it", new TestController());
    ParsedRequest request = new ParsedRequest("GET", "/it");

    router.route(request);

    assertEquals(actualRequest, request);
  }

  @Test
  public void pathWithDynamicData() throws Exception {
    router.addPath("a", new TestController());
    ParsedRequest request = new ParsedRequest("GET", "/a/b/c");

    router.route(request);

    assertEquals(actualRequest, request);
  }

  @Test
  public void rootPath() throws Exception {
    router.addPath("", new TestController());
    ParsedRequest request = new ParsedRequest("GET", "/");

    router.route(request);

    assertEquals(actualRequest, request);
  }

  class TestController implements Controller {
    public String handle(ParsedRequest request) {
      actualRequest = request;
      return "";
    }
  }

  @Test
  public void Four04() throws Exception
  {
    String result = router.route(new ParsedRequest("GET", "/something-missing"));

    assertEquals("HTTP/1.1 404 OK\n", result);
  }
}
