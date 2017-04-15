package mb.pokequiz.db.repository.realm

import io.reactivex.Single
import io.reactivex.disposables.Disposables
import io.realm.RealmChangeListener
import io.realm.RealmObject


/**
 * Created by mbpeele on 4/15/17.
 */
internal object RxRealm {

    fun <T : RealmObject> single(t: T) : Single<T> {
        return Single.create {
            val emitter = it

            val changeListener = RealmChangeListener<T> { element ->
                if (element.isLoaded) {
                    emitter.onSuccess(element)
                }
            }

            RealmObject.addChangeListener(t, changeListener)

            emitter.setDisposable(Disposables.fromRunnable {
                RealmObject.removeChangeListener(t, changeListener)
            })
        }
    }
}