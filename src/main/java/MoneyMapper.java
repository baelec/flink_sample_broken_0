import org.apache.flink.api.common.functions.MapFunction;

class MoneyMapper implements MapFunction<String, String> {
  @Override
  public String map(String t) throws Exception {
    return "$" + t;
  }
}