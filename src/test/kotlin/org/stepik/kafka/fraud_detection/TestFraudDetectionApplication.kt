package org.stepik.kafka.fraud_detection

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<FraudDetectionApplication>().with(TestcontainersConfiguration::class).run(*args)
}
