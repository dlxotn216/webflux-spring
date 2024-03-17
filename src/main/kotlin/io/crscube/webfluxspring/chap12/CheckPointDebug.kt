package io.crscube.webfluxspring.chap12

import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux

/**
 * Created by itaesu on 2024/03/14.
 *
 * @author Lee Tae Su
 * @version webflux-spring
 * @since webflux-spring
 */
class CheckPointDebug {
    fun process() {
        Flux.just(2, 4, 6, 8)
            .zipWith(Flux.just(1, 2, 3, 0)) { t1, t2 ->
                println("123123")
                println("t1[$t1] t2[$t2]")
                t1 / t2
            }
            .checkpoint()
            .map { it + 2 }
            .subscribe(
                { log.info("# onNext: {}", it) }
            ) {
                log.error("# onError", it)
            }
    }

    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }
}

fun main() {
    CheckPointDebug().process()
    Thread.sleep(1000)
}
