import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.util.Collector;

class UppercaseProcessor extends ProcessFunction<String, String> {
  @Override
  public void processElement(String value, Context ctx, Collector<String> out) throws Exception {
    out.collect(value.toUpperCase());
  }
}