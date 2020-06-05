package com.ratel.hydra.common.utils;

/**
 * @author ratel
 * @date 2020-06-05
 */
public class NumberUtils {

    public static boolean isZero(Object number) {
        if (number == null) {
            return true;
        }
        if (number instanceof Long) {
            return (Long) number == 0L;
        }
        if (number instanceof Integer) {
            return (Integer) number == 0;
        }
        if (number instanceof Byte) {
            return (Byte) number == 0;
        }
        if (number instanceof Short) {
            return (Short) number == 0;
        }
        if (number instanceof Double) {
            return (Double) number == 0;
        }
        if (number instanceof Float) {
            return (Float) number == 0;
        }
        return true;
    }

}
