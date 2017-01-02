package mb.pokequiz.data.preferences

import android.content.SharedPreferences

/**
 * Created by mbpeele on 12/29/16.
 */

open class IntPreference(key: String, defaultValue: Int, preferences: SharedPreferences) : BasePreference<Int>(key, defaultValue, preferences) {

    override fun get(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    override fun set(key: String, value: Int) {
        return preferences.edit().putInt(key, value).apply()
    }

}
