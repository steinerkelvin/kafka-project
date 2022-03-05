package ser

import com.fasterxml.jackson.annotation.JsonProperty

class VaccineEvent(sensor_id: String, timestamp: Long, pos: Geo) {
    @JsonProperty
    val sensor_id: String = sensor_id
    @JsonProperty
    val timestamp: Long = timestamp
    // temperature
    @JsonProperty
    val pos: Geo = pos
}

class Geo(lat: Double, long: Double) {
    @JsonProperty
    val lat: Double = lat
    @JsonProperty
    val long: Double = long
}
