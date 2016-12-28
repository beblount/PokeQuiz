package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.ProfileEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.ProfileMapper
import mb.pokequiz.data.model.Profile

/**
 * Created by mbpeele on 12/27/16.
 */
class ProfileMapperTest : MapperTest<Profile, ProfileEntity>() {

    override fun createMapper(): Mapper<Profile, ProfileEntity> {
        return ProfileMapper()
    }

    override fun createModel(): Profile {
        return Profile(randomString(), randomString())
    }

    override fun createEntity(): ProfileEntity {
        val entity = ProfileEntity()
        entity.name = randomString()
        entity.avatarUrl = randomString()
        return entity
    }
}