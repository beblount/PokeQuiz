package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
interface DatabaseRepository {

    fun <Entity : RealmObject> create(entity: Entity): Single<Entity>

    fun <Entity : RealmObject> update(entity: Entity) : Observable<Entity>

    fun <Entity : RealmObject> delete(entity: Entity): Single<Entity>
}