package cleancoderscom.fixtures;

import cleancoderscom.Codecast;
import cleancoderscom.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GivenCodecasts {
  private String title;
  private String publicationDate;
  private static SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPublished(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public void execute() throws ParseException {
    Codecast codecast = new Codecast();
    codecast.setTitle(title);
    System.out.println(publicationDate);
    System.out.println(dateFormat.parse(publicationDate));
    codecast.setPublicationDate(dateFormat.parse(publicationDate));
    Context.gateway.save(codecast);
  }

}
