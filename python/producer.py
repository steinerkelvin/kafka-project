from kafka import KafkaProducer
import json
import time

from perlin_gen import get_perlin_arr

def gen_arrays():
        # Cria arr de perlin para temperatura
    temp_arr = get_perlin_arr(seed=12, n=45000)

    # Ajusta para o intervalo das vacinas
    for i in range(len(temp_arr)):
        temp_arr[i] = (temp_arr[i] * 6) + 5


    # Cria arrays de perlin para localização
    lat_arr = get_perlin_arr(seed=41, n=45000)
    long_arr = get_perlin_arr(seed=19, n=45000)

    # Ajusta o intervalo de latitudes e longitudes
    for i in range(len(lat_arr)):
        lat_arr[i] = ((lat_arr[i] * 2.5) + 2.5 - 2032.00) / 100

    for i in range(len(long_arr)):
        long_arr[i] = ((long_arr[i] * 2) + 2 - 4033.00) / 100

    # -20.270000, -40.330000       -20.270000, -40.290000
    # -20.317000, -40.330000       -20.317000, -40.290000

    return temp_arr, lat_arr, long_arr



if __name__ == "__main__":
    bs_server = "localhost:9092"
    topic = "first-topic"
    time_interval = 1

    temp_arr, lat_arr, long_arr = gen_arrays()

    producer = KafkaProducer(bootstrap_servers=bs_server, value_serializer=lambda v: json.dumps(v).encode('utf-8'))
    for i in range(100):
        producer.send(topic, {
            'lat': lat_arr[i],
            'long': long_arr[i],
            'temp': temp_arr[i],
            })
        time.sleep(time_interval)