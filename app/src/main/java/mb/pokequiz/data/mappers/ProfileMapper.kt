package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.ProfileEntity
import mb.pokequiz.data.model.Profile

/**
 * Created by mbpeele on 12/27/16.
 */
class ProfileMapper : Mapper<Profile, ProfileEntity> {

    override fun toModel(entity: ProfileEntity, factory: MapperFactory): Profile {
        return Profile(entity.name!!, entity.avatarUrl!!)
    }

    override fun toEntity(model: Profile, factory: MapperFactory): ProfileEntity {
        val entity = ProfileEntity()
        entity.name = model.name
        entity.avatarUrl = entity.avatarUrl
        return entity
    }
}