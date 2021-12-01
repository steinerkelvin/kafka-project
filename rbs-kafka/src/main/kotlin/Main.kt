import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

fun main(args: Array<String>) {
    println("Hello World!")

    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
    println("Program arguments: ${args.joinToString()}")

    val bootstrap_server = "localhost:9092"

    val prop = Properties()

    prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap_server)
    prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer::class.qualifiedName)
    prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer::class.qualifiedName)

    // Criar um produtor
    val producer: KafkaProducer<String, String> = KafkaProducer<String, String>(prop)

    // Criar um producer record
    val record: ProducerRecord<String, String> = ProducerRecord<String, String>("first-topic", "ola")

    // Enviar data
    producer.send(record)

    producer.flush()

    println("End...")
}
