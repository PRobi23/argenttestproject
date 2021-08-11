package com.test.argenttestproject.robertpapp

import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.processors.FlowableProcessor

/**
 * Wraps the [FlowableProcessor] object into a [Flowable], which
 * emits the same sequence as the original processor.
 * <br/>
 * Although [FlowableProcessor] already extends [Flowable], it is
 * not possible to properly use [Flowable.share] on it.
 *
 * @param T the item value type
 * @return the processor wrapped in a [Flowable]
 */
@JvmName("wrapInFlowable")
fun <T : Any> FlowableProcessor<T>.wrap(): Flowable<T> = Flowable.create({ source ->
    val disposable = subscribe(source::onNext, source::onError)

    source.setCancellable(disposable::dispose)
}, BackpressureStrategy.BUFFER)
