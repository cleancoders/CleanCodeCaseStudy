package cleancoderscom.usecases.codecastDetails;

import cleancoderscom.entities.User;
import cleancoderscom.usecases.gateways.Context;
import cleancoderscom.usecases.core.UseCase;

public class CodecastDetailsUseCase implements UseCase<CodecastDetailsUseCase.Request, CodecastDetailsUseCase.Response> {

  @Override
  public Response execute(Request request) {
    Response response = new Response();
    response.value = Context.codecastGateway.findCodecastByPermalink(request.permalink) != null;
    return response;
  }

  public static class Request {
    public User loggedInUser;
    public String permalink;
  }

  public static class Response {
    public boolean value;
  }
}
