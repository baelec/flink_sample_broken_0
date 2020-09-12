import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

class RouterProcessor extends ProcessFunction<String, String> {
  @Override
  public void processElement(String value, Context ctx, Collector<String> out) throws Exception {
    String[] tokens = value.split(",");

    for (String token : tokens) {
      if (token.matches("[0-9]+")) {
        ctx.output(App.numberOutputTag, token);
      } else {
        out.collect(token);
      }
    }
  }
}