package org.stepik.kafka.fraud_detection.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

private val CONFIG_REQUEST =
    mapOf(
        "name" to "transaction-connector",
        "config" to
            mapOf(
                "connector.class" to "io.debezium.connector.postgresql.PostgresConnector",
                "database.hostname" to "postgres",
                "database.port" to "5432",
                "database.user" to "debezium",
                "database.password" to "dbz",
                "database.dbname" to "transactions_db",
                "table.include.list" to "public.transactions",
                "tombstones.on.delete" to "false",
                "topic.prefix" to "transactions",
                "plugin.name" to "pgoutput",
                "topic.creation.enable" to "true",
                "topic.creation.default.replication.factor" to "1",
                "topic.creation.default.partitions" to "5",
            ),
    )

private val LOGGER = org.slf4j.LoggerFactory.getLogger(DebeziumSetupService::class.java)

@Service
class DebeziumSetupService(@Value("\${app.debezium-url}") val url: String) {

  @EventListener
  fun onStart(event: ContextRefreshedEvent) {
    val client = RestClient.create(url)

    LOGGER.info("Creating connector")

    val response =
        client.post().uri("/connectors").body(CONFIG_REQUEST).retrieve().toBodilessEntity()
    if (!response.statusCode.is2xxSuccessful) {
      throw RuntimeException("Failed to create connector")
    }
  }
}
