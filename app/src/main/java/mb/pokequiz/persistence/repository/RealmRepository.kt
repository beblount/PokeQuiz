package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
class RealmRepository @Inject constructor(var realm: Realm) : DatabaseRepository {

    override fun <Entity : RealmObject> create(entity: Entity): Single<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Entity : RealmObject> update(entity: Entity): Observable<Entity> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Entity : RealmObject> delete(entity: Entity): Single<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
