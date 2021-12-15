import model.sensors
import java.util.*
import java.time.Duration
import org.slf4j.Logger
import org.slf4j.LoggerFactory

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer

import org.ktorm.database.Database

data class SensorSettings(
    val min_temp: Float,
    val max_temp: Float,
)

fun main(args: Array<String>) {
    val logger: Logger = LoggerFactory.getLogger("ANALYZER")

    // Class.forName("org.postgresql.Driver")
    val db_host = System.getenv("DB_HOST")
    val db_user = "postgres"
    val db_pass = System.getenv("DB_PASS")
    val database = Database.connect(
        "jdbc:postgresql://${db_host}/vaccinator",
        user = db_user, password = db_pass,
    )
    val bootstrap_server = "localhost:9092"

//    val sensors: MutableMap<String, SensorSettings> = HashMap()
//    sensors.set("test-sensor-1234", SensorSettings(25.0f, 29.0f))

    val sensors = database.sensors

    val producer_props = Properties()
    producer_props.setProperty(
        ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrap_server
    )
    producer_props.setProperty(
        ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer::class.qualifiedName
    )
    producer_props.setProperty(
        ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer::class.qualifiedName
    )

    val consumer_props = Properties()
    consumer_props.setProperty(
        ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
        bootstrap_server
    )
    consumer_props.setProperty(
        ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer::class.qualifiedName
    )
    consumer_props.setProperty(
        ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer::class.qualifiedName
    )
    consumer_props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "analyzers")
    consumer_props.setProperty(
        ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
        "earliest"
    )

    val producer: KafkaProducer<String, String> =
        KafkaProducer<String, String>(producer_props)
    // val record: ProducerRecord<String, String> = ProducerRecord<String, String>("first-topic", "ola")
    // producer.send(record)

    val consumer: KafkaConsumer<String, String> =
        KafkaConsumer<String, String>(consumer_props)
    consumer.subscribe(Arrays.asList<String>("storage_sensor"))

    // TODO: JSON

    while (true) {
        val records = consumer.poll(Duration.ofMillis(100))
        for (record in records) {
            logger.info("Key: " + record.key() + " Value: " + record.value() + " Partition : " + record.partition())
        }
    }

    producer.flush()
}
