import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import java.util.Properties;

@Slf4j
public class SimpleProducer {

    private static final String TOPIC_NAME = "test";
    private static final String BOOTSTRAP_SERVERS = "localhost:9092";
    int partitionNo = 0;

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        String messageValue = "testMessage";
        //ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, messageValue);
        ProducerRecord<String, String> record = new ProducerRecord<>("test", 2, "Pangyo", "23");

        producer.send(record); // 배치전송을 해서 보낸다.
        log.info("{}", record);
        producer.flush();
        producer.close();
    }
}
