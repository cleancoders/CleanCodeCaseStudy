package cleancoderscom.delivery.view;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViewTemplateTest {

  @Test
  public void noReplacement() throws Exception {
    ViewTemplate template = new ViewTemplate("some static content");
    assertEquals("some static content", template.getContent());
  }

  @Test
  public void simpleReplacement() throws Exception {
    ViewTemplate template = new ViewTemplate("replace ${this}.");
    template.replace("this", "replacement");
    assertEquals("replace replacement.", template.getContent());
  }



}
