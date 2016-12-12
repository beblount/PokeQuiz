package mb.pokequiz.persistence.repository

import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */

class RealmDatabase(val realm: Realm) : RealmRepository {

    override fun create(entity: RealmObject): Single<RealmObject> {
        return Single.create { subscriber ->
            executeTransaction(object : Command {
                override fun execute(realm: Realm) {
                    realm.insert(entity)
                }
            }, object : Callback {
                override fun onError(error: Throwable) {
                    subscriber.onError(error)
                }

                override fun onSuccess() {
                    subscriber.onSuccess(entity)
                }
            })
        }
    }

    internal fun executeTransaction(command: Command, callback: Callback) {
        realm.executeTransactionAsync(
                { realm -> command.execute(realm) },
                { callback.onSuccess() },
                { error -> callback.onError(error) }
        )
    }

    internal interface Command {

        fun execute(realm : Realm)
    }

    internal interface Callback {

        fun onSuccess()

        fun onError(error: Throwable)
    }

}
