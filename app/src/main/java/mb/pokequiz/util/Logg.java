package mb.pokequiz.util;

import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;

/**
 * Created by milespeele on 7/3/15.
 */
public class Logg {

    private static final String LOG_TAG = "Miles";
    private static final StringBuilder builder = new StringBuilder();

    private static void mainLog(String string) {
        if (string != null) {
            if (!string.isEmpty()) {
                Log.d(LOG_TAG, string);
            } else {
                Log.d(LOG_TAG, "printLn needs a Message");
            }
        } else {
            Log.d(LOG_TAG, "Argument to Logg is null");
        }
        builder.setLength(0);
    }

    public static void log(Double... doubles) {
        for (double doubleVal: doubles) {
            builder.append(doubleVal);
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(double[] doubles) {
        for (double doubleVal: doubles) {
            builder.append(doubleVal);
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(float[] floats) {
        for (float val: floats) {
            builder.append(val);
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(Double value) {
        mainLog(value.toString());
    }

    public static void log(int[] array) {
        for (int integer: array) {
            builder.append(String.valueOf(integer));
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(Long... longs) {
        for (long longVal: longs) {
            builder.append(String.valueOf(longVal));
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(Integer... integers) {
        for (int integer: integers) {
            builder.append(String.valueOf(integer));
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(Float... integers) {
        for (float integer: integers) {
            builder.append(String.valueOf(integer));
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(boolean... bools) {
        for (boolean bool: bools) {
            builder.append(String.valueOf(bool));
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(boolean bool) {
        mainLog(String.valueOf(bool));
    }

    public static void log(float integer) {
        mainLog(String.valueOf(integer));
    }

    public static void log(int integer) {
        mainLog(String.valueOf(integer));
    }

    public static void log(String string) {
        if (checkNull(string)) {
            return;
        }

        mainLog(string);
    }

    public static void log(String string, Throwable throwable) {
        if (checkNull(string, throwable)) {
            return;
        }

        mainLog(string + throwable.getLocalizedMessage());
    }

    public static void log(String... strings) {
        if (checkNull(strings)) {
            return;
        }

        for (String toPrint: strings) {
            builder.append(toPrint);
            builder.append(", ");
        }
        mainLog(builder.toString());
    }

    public static void log(Throwable throwable, String... strings) {
        if (checkNull(throwable, strings)) {
            return;
        }

        for (String toPrint: strings) {
            builder.append(toPrint);
        }
        mainLog(builder.toString() + throwable.getLocalizedMessage());
    }

    public static void log(Throwable throwable) {
        mainLog(throwable.toString());
    }

    public static void log(String string, float... floats) {
        if (checkNull(string)) {
            return;
        }

        builder.append(string);
        builder.append(", ");
        log(floats);
    }

    public static void log(String string, double... doubles) {
        if (checkNull(string)) {
            return;
        }

        builder.append(string);
        builder.append(", ");
        log(doubles);
    }

    public static void log(String string, boolean... bools) {
        if (checkNull(string)) {
            return;
        }

        builder.append(string);
        builder.append(", ");
        log(bools);
    }

    private static boolean checkNull(Object... objects) {
        return objects == null;
    }
}