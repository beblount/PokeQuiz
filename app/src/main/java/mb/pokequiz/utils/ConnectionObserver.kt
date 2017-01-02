package mb.pokequiz.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager



/**
 * Created by mbpeele on 12/30/16.
 */
class ConnectionObserver : BroadcastReceiver {

    enum class ConnectionState {
        NONE,
        INTERNET,
        DATA,
        WIFI
    }

    private var state : ConnectionState = ConnectionState.NONE
    private val context : Context

    constructor(context: Context) {
        this.context = context
        context.registerReceiver(this, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        getState()
    }

    private fun getState() : ConnectionState {
        val manager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val activeNetwork = manager.activeNetworkInfo
        val isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if (isConnected) {
            state = ConnectionState.INTERNET
            if (activeNetwork.type == ConnectivityManager.TYPE_MOBILE) {
                state = ConnectionState.DATA
            } else if (activeNetwork.type == ConnectivityManager.TYPE_WIFI) {
                state = ConnectionState.WIFI
            }
        } else {
            state = ConnectionState.NONE
        }

        return state
    }

    override fun onReceive(context: Context?, intent: Intent?) {
    }

}