package cleancoderscom.adapters.mvc;

import java.util.function.Function;

public interface Controller<Input, Output> extends Function<Input, Output> {
  default Output handle(Input input){
    return this.getHandler().apply(input);
  }

  @Override
  default Output apply(Input input) {
    return this.handle(input);
  }

  Function<Input, Output> getHandler();
}