package cleancoderscom.entities;

import java.time.LocalDate;

public class Codecast extends Entity {
  private String title;
  private LocalDate publicationDate = LocalDate.now();
  private String permalink;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  public String getPermalink() {
    return permalink;
  }
}
