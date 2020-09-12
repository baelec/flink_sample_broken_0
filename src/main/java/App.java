import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.OutputTag;

public class App {
  public static final OutputTag<String> numberOutputTag = new OutputTag<String>("side-output") {
  };

  public static void main(String[] args) throws Exception {
    StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
    DataStreamSource<String> text = env.fromElements(
      "abc,123"
    );

    // Router will split input on commas and redirect number strings to the side output
    SingleOutputStreamOperator<String> ingestStream = text
      .process(new UppercaseProcessor())
      .process(new RouterProcessor())
      ;

    DataStream<String> numberStream = ingestStream.getSideOutput(numberOutputTag)
      // Prepends a "$" to the values.
      .map(new MoneyMapper());

    numberStream.print();
    ingestStream.print();

    env.execute();
  }
}
