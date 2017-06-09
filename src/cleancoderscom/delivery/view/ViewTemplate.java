package cleancoderscom.delivery.view;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ViewTemplate {
  private String template;

  public static ViewTemplate create(String templateResource) throws IOException {
    URL url = ClassLoader.getSystemResource(templateResource);
    byte[] bytes = Files.readAllBytes(Paths.get(url.getPath()));
    return new ViewTemplate(new String(bytes));
  }

  public ViewTemplate(String template) {

    this.template = template;
  }

  public void replace(String tagName, String content) {
    template = template.replace("${" + tagName + "}", content);
  }

  public String getContent() {
    return template;
  }
}
