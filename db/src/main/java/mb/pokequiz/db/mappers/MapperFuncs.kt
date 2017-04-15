package mb.pokequiz.db.mappers

import io.realm.RealmList
import io.realm.RealmModel
import java.util.*

/**
 * Created by mbpeele on 1/27/17.
 */
internal object MapperFuncs {

    fun <E, T> modelList(list: List<T>?, mapper: (T) -> E): List<E> {
        if (list == null) {
            return ArrayList()
        } else {
            return list.map { mapper.invoke(it) }
        }
    }

    fun <E, T : RealmModel?> entityList(list: List<E>?, mapper: (E) -> T): RealmList<T> {
        if (list == null) {
            return RealmList()
        } else {
            val realmList = RealmList<T>()
            val entities = list.map { mapper.invoke(it) }
            realmList.addAll(entities)
            return realmList
        }
    }

}