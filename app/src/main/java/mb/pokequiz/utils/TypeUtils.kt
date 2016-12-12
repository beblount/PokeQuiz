package mb.pokequiz.utils

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by mbpeele on 12/11/16.
 */
open class TypeLiteral<T> {

    val type: Type
        get() = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
}

inline fun <reified T> typeLiteral(): TypeLiteral<T> = object : TypeLiteral<T>() {} // here T is replaced with the actual type
