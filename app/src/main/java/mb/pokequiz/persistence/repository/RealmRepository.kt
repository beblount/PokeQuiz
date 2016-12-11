package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import io.realm.Realm
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
class RealmRepository(var realm: Realm) : DatabaseRepository {

    override fun <Entity : RealmObject> create(entity: Entity): Single<Entity> {
        val subscriber = SingleOnSubscribe<Entity> { e ->
            transaction(object : Batcher {
                override fun run(realm: Realm) {
                    realm.insertOrUpdate(entity)
                }
            }, object : RealmCallback {
                override fun onError(throwable: Throwable) {
                    e.onError(throwable)
                }

                override fun onSuccess() {
                    e.onSuccess(entity)
                }
            })
        }

        return Single.create(subscriber)
    }

    override fun <Entity : RealmObject> update(entity: Entity): Observable<Entity> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Entity : RealmObject> delete(entity: Entity): Single<Entity> {
        val subscriber = SingleOnSubscribe<Entity> { e ->
            transaction(object : Batcher {
                override fun run(realm: Realm) {
                    entity.deleteFromRealm()
                }
            }, object : RealmCallback {
                override fun onError(throwable: Throwable) {
                    e.onError(throwable)
                }

                override fun onSuccess() {
                    e.onSuccess(entity)
                }
            })
        }

        return Single.create(subscriber)
    }

    fun transaction(batcher: Batcher, callback: RealmCallback) {
        realm.executeTransactionAsync(
                (Realm.Transaction { realm -> batcher.run(realm) }),
                (Realm.Transaction.OnSuccess { callback.onSuccess() }),
                (Realm.Transaction.OnError { error -> callback.onError(error) }))
    }

    interface Batcher {

        fun run(realm: Realm)

    }

    interface RealmCallback {
        fun onSuccess()

        fun onError(throwable: Throwable)
    }

}
