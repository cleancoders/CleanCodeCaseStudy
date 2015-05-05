package cleancoderscom.http;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {
  private ParsedRequest actualRequest;

  @Test
  public void simplePath() throws Exception {
    Router router = new Router();
    router.addPath("it", new TestController());

    router.route(new ParsedRequest("GET", "it"));

    assertEquals(actualRequest, new ParsedRequest("GET", "it"));
  }

  class TestController implements Controller {

    public void handle(ParsedRequest request) {
      actualRequest = request;
    }
  }
}
