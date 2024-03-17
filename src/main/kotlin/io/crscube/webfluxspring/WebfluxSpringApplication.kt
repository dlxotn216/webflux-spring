package io.crscube.webfluxspring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WebfluxSpringApplication

fun main(args: Array<String>) {
    runApplication<WebfluxSpringApplication>(*args)
}
