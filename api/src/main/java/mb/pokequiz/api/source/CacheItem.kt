package mb.pokequiz.api.source

/**
 * Created by mbpeele on 4/15/17.
 */
data class CacheItem<out T>(val item: T?) {

    companion object {
        val NOT_FOUND = CacheItem(null)
    }
}