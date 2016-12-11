package mb.pokequiz.persistence.mapper

import android.support.annotation.NonNull
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
interface Mapper<Model, Entity : RealmObject> {

    @NonNull fun toEntity(@NonNull model: Model, @NonNull mapperFactory: MapperFactory) : Entity

    @NonNull fun toModel(@NonNull entity: Entity, @NonNull mapperFactory: MapperFactory) : Model
}