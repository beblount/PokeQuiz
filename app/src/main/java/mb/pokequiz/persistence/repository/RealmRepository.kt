package mb.pokequiz.persistence.repository

import io.reactivex.Single
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */

interface RealmRepository {

    fun create(entity : RealmObject) : Single<RealmObject>
}
