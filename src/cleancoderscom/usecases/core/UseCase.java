package cleancoderscom.usecases.core;

import java.util.function.Function;

public interface UseCase<Request, Response> extends Function<Request, Response> {
  Response apply(Request request);
}
