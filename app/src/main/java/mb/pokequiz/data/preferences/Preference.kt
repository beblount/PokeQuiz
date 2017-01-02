package mb.pokequiz.data.preferences

/**
 * Created by mbpeele on 12/29/16.
 */
interface Preference<Value> {

    fun get(key: String, defaultValue: Value) : Value

    fun set(key: String, value: Value)

    fun isSet(key: String) : Boolean

    fun delete(key: String)
}