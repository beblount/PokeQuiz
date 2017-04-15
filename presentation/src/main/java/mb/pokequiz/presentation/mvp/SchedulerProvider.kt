package mb.pokequiz.presentation.mvp

import io.reactivex.Scheduler

/**
 * Created by mbpeele on 4/15/17.
 */
interface SchedulerProvider {

    fun ui(): Scheduler

    fun computation(): Scheduler

    fun trampoline(): Scheduler

    fun newThread(): Scheduler

    fun io(): Scheduler

}