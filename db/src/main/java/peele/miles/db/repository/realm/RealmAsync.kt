package peele.miles.db.repository.realm

import io.reactivex.Completable
import io.realm.Realm

/**
 * Created by mbpeele on 4/15/17.
 */
internal object RealmAsync {

    fun completableTransaction(realm: Realm, function: (realm: Realm) -> Unit) : Completable {
        return Completable.create {
            val subscriber = it

            realm.executeTransactionAsync({
                function.invoke(it)
            }, {
                subscriber.onComplete()
            }, {
                subscriber.onError(it)
            })
        }
    }

}