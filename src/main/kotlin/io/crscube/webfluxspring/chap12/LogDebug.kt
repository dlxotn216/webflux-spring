package io.crscube.webfluxspring.chap12

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

/**
 * Created by itaesu on 2024/03/14.
 *
 * @author Lee Tae Su
 * @version webflux-spring
 * @since webflux-spring
 */
class LogDebug {
    private val fruits = mapOf(
        "banana" to "바나나",
        "apple" to "사과",
        "pear" to "배",
        "grape" to "포도"
    )

    fun process() {
        Flux.fromArray(arrayOf("bananas", "apples", "pears", "melons"))
            .subscribeOn(Schedulers.boundedElastic())
            .log()
            .publishOn(Schedulers.parallel())
            .log()
            .map { it.lowercase() }
            .log()
            .map { it.substring(0, it.lastIndex) }
            .log()
            .map { fruits[it] }
            .log()
            .map { "맛있는 $it" }
            .subscribe(
                log::info
            ) {
                log.error("# onError", it)
            }

    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }
}

fun main() {
    LogDebug().process()
    Thread.sleep(1000)
}
