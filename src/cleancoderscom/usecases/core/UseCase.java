package cleancoderscom.usecases.core;

@FunctionalInterface
public interface UseCase<Request, Response> {
    Response execute(Request request);
}
