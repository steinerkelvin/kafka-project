package store

import org.ktorm.database.Database
import org.ktorm.entity.Entity
import org.ktorm.entity.sequenceOf
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.varchar
import org.ktorm.schema.double

interface Vaccine : Entity<Vaccine> {
    companion object : Entity.Factory<Vaccine>()

    val id: Int
    var name: String
    var min_temp: Double
    var max_temp: Double
}

object Vaccines : Table<Vaccine>("t_vaccine") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val min_temp = double("min_temp").bindTo { it.min_temp }
    val max_temp = double("max_temp").bindTo { it.max_temp }
}

val Database.vaccines get() = this.sequenceOf(Vaccines)
