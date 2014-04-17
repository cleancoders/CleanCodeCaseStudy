package cleancoderscom.fixtures;

import java.util.Arrays;
import java.util.List;

//OrderedQuery
public class OfCodeCasts {
  private List<Object> list(Object... objects) {
    return Arrays.asList(objects);
  }

  public List<Object> query() {
    return
      list(
        list(
          //list(name,value)
        )
      );
  }
}
