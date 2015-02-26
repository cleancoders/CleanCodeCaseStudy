package cleancoderscom;

public class CodecastDetailsUseCase {
  public PresentableCodecastDetails requestCodecastDetails(User loggedInUser, String permalink) {
    PresentableCodecastDetails details = new PresentableCodecastDetails();

    Codecast codecast = Context.codecastGateway.findCodecastByPermalink(permalink);
    //TODO - MDM - add test for null codecast

    details.title = codecast.getTitle();
    details.publicationDate = PresentCodecastUseCase.dateFormat.format(codecast.getPublicationDate());
    return details ;
  }
}
