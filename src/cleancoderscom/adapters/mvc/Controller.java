package cleancoderscom.adapters.mvc;

import java.util.function.Function;

public interface Controller<Input, Output> {
  default Output handle(Input input) {
    return this.getHandler().apply(input);
  }

  Function<Input, Output> getHandler();
}