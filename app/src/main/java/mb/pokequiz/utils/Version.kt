package mb.pokequiz.utils

import android.os.Build

/**
 * Created by mbpeele on 12/30/16.
 */
fun beforeMarshmallow(): Boolean = isOlderVersionThen(23)
fun marshmallowOrNewer(): Boolean = isOnVersionOrNewer(23)
fun beforeLollipop(): Boolean = isOlderVersionThen(21)
fun lollipopOrNewer(): Boolean = isOnVersionOrNewer(21)
fun currentVersion(): Int = Build.VERSION.SDK_INT

/*
 * -----------------------------------------------------------------------------
 *  Private methods
 * -----------------------------------------------------------------------------
 */
fun isOlderVersionThen(version: Int) = Build.VERSION.SDK_INT < version

fun isOnVersionOrNewer(version: Int) = Build.VERSION.SDK_INT >= version