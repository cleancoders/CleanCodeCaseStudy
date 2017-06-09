package cleancoderscom.fixtures;

import cleancoderscom.entities.Codecast;
import cleancoderscom.usecases.gateways.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class GivenCodecasts {
  private String title;
  private String publicationDate;
  private SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
  private String permalink;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPublished(String publicationDate) {
    this.publicationDate = publicationDate;
  }

  public void setPermalink(String permalink) {this.permalink = permalink;}

  public void execute() throws ParseException {
    Codecast codecast = new Codecast();
    codecast.setTitle(title);
    codecast.setPublicationDate(dateFormat.parse(publicationDate));
    codecast.setPermalink(permalink);
    Context.codecastGateway.save(codecast);
  }

}
