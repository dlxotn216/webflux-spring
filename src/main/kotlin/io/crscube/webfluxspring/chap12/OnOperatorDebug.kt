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
class OnOperatorDebug {
    private val fruits = mapOf(
        "banana" to "바나나",
        "apple" to "사과",
        "pear" to "배",
        "grape" to "포도"
    )

    fun process2() {
        Flux.fromArray(arrayOf("bananas", "apples", "pears", "melons"))
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .map { it.lowercase() }
            .map { it.substring(0, it.lastIndex) }
            .map {
                fruits[it]
            }
            .map { "맛있는 $it" }
            .subscribe(
                log::info
            )
    }

    fun process() {
        Flux.fromArray(arrayOf("bananas", "apples", "pears", "melons"))
            .subscribeOn(Schedulers.boundedElastic())
            .publishOn(Schedulers.parallel())
            .flatMap { translate(Flux.just(it)) }
            // .mapNotNull { fruits[it] }
            .map { "맛있는 $it" }
            .subscribe(
                log::info
            ) {
                log.error("# onError", it)
            }
    }

    fun getId(userkey: Long, userName: String) {
                getId2(userkey, userName)
    }

    fun getId2(userkey: Long, userName: String) {

    }

    fun translate(keywords: Flux<String>): Flux<String> {
        return keywords.map { it.lowercase() }
            .map { it.substring(0, it.lastIndex) }
            .map {
                val fruit = fruits[it]
                // do something
                fruit!!
            }
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }
}

fun main() {
    // Hooks.onOperatorDebug();
    OnOperatorDebug().process()
    OnOperatorDebug().process2()
    Thread.sleep(1000)
}
