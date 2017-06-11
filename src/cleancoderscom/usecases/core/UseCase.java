package cleancoderscom.usecases.core;

public interface UseCase<Request, Response> {
  Response apply(Request request);
}
