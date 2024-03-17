package io.crscube.webfluxspring.chap12

import reactor.core.publisher.Flux
import reactor.core.publisher.Hooks

/**
 * Created by itaesu on 2024/03/14.
 *
 * @author Lee Tae Su
 * @version webflux-spring
 * @since webflux-spring
 */
class ComplexCheckpointExample {
    fun process() {
        // Flux.just(2, 4, 6, 8)
        //     .zipWith(Flux.just(1, 2, 3, 0)) { t1, t2 ->
        //         println("123123")
        //         println("t1[$t1] t2[$t2]")
        //         t1 / t2
        //     }
        // .checkpoint("divideSource checkpoint", true)
        //     .subscribe()

        val source = Flux.just(2, 4, 6, 8)
        val other = Flux.just(1, 2, 3, 0)

        val dividedSource = divide(source, other)
            .checkpoint("divideSource checkpoint", true)
        val plusSource = plusTwo(dividedSource)
            .checkpoint("plusSource checkpoint")

        plusSource.subscribe(
            { println("# onNext: $it") },
        ) {
            println("# onError: $it")
        }
    }

    fun divide(source: Flux<Int>, other: Flux<Int>): Flux<Int> {
        return source.zipWith(other) { t1, t2 -> t1 / t2 }
            .checkpoint("divideSource checkpoint", true)
    }

    fun plusTwo(source: Flux<Int>): Flux<Int> {
        return source.map { it + 2 }
    }
}

fun main() {
    Hooks.onOperatorDebug();
    ComplexCheckpointExample().process()
    Thread.sleep(1000)
}
