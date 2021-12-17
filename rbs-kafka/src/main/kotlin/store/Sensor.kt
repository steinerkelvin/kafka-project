package store

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar

interface Sensor : Entity<Sensor> {
    companion object : Entity.Factory<Sensor>()

    var id: Int
    var sensor_id: String
    var vaccine: Vaccine
}

object Sensors : Table<Sensor>("t_sensor") {
    val id = int("id").bindTo { it.id }
    val sensor_id = varchar("sensor_id").bindTo { it.sensor_id }
    val vaccine = int("vaccine").references(Vaccines) { it.vaccine }
}

val Database.sensors get() = this.sequenceOf(Sensors)
