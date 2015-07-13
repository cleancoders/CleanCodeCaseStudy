package cleancoderscom.socketserver;

import org.junit.runner.Computer;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.List;

public class RaceConditionTester {

  public static void main(String[] args) {
    Computer computer = new Computer();
    JUnitCore jUnitCore = new JUnitCore();
    for(int i = 0; i < 1000; i++)
      runIt(computer, jUnitCore);
  }

  private static void runIt(Computer computer, JUnitCore jUnitCore) {
    final Result run = jUnitCore.run(computer, SocketServerTest.class);
    if(run.wasSuccessful())
      System.out.print(".");
    else {
      System.err.println("F");
      final List<Failure> failures = run.getFailures();
      for(Failure failure : failures) {
        System.err.println(failure.toString());
      }
      System.exit(-1);
    }
  }
}
