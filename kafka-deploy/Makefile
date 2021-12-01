all:
	@echo

get-kafka:
	mkdir -p ./opt

	cd ./opt  && \
		wget https://dlcdn.apache.org/kafka/3.0.0/kafka_2.13-3.0.0.tgz  && \
		tar -xzf kafka_2.13-3.0.0.tgz

start-zookeeper:
	./opt/kafka_2.13-3.0.0/bin/zookeeper-server-start.sh config/zookeeper.properties

start-kafka:
	./opt/kafka_2.13-3.0.0/bin/kafka-server-start.sh config/server.properties
