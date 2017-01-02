package mb.pokequiz.data.preferences

import android.content.SharedPreferences

/**
 * Created by mbpeele on 12/30/16.
 */
abstract class BasePreference<Value>(val key: String, val defaultValue: Value, val preferences: SharedPreferences) : Preference<Value> {

    override fun isSet(key: String): Boolean {
        return preferences.contains(key)
    }

    override fun delete(key: String) {
        preferences.edit().remove(key).apply()
    }
}