/*
 * Copyright 2015 Jiesoft Consulting.
 * All Rights Reserved.
 *
 * All information contained herein is, and remains the property
 * of Jiesoft Consulting. The intellectual and technical concepts 
 * contained herein are proprietary to Jiesoft and are protected by 
 * trade secret or copyright law. Dissemination of this information 
 * or reproduction of this material is strictly forbidden unless 
 * prior written permission is obtained from Jiesoft Consulting.
 *
 *      http://www.jiesoft.com
 */
package com.jiesoft.mitrac.common;

import java.io.File;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.MathContext;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * The Class StringUtilsExt.
 *
 * @author Ray Shi
 */

public final class StringUtilsExt {

    /** Should we show null values, within fields, for an object, when running toString(). */
    private static final boolean TO_STRING_SHOW_NULL_FIELD_VALUES = false;

    /**
     * The line separator for this operating system.
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** The Constant MAXIMUM_TO_STRING. */
    private static final int MAXIMUM_TO_STRING = 4000;

    /** The Constant MAXIMUM_LENGTH_STRING_TO_STRING. */
    private static final int MAXIMUM_LENGTH_STRING_TO_STRING = 50;

    /**
     * Constructor made private to enforce the Utility design pattern.
     */
    private StringUtilsExt() {
        // nothing to do
    }
    
    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    /**
     * Converts a String object to a double. If an error occurs, a message is output to the error
     * stream, and the given default value is returned.
     * @param toConvert The String to be converted.
     * @param defaultValue The default value to be returned if an error occurs.
     * @return The converted double, or the given default value if an error occurred.
     */
    public static double stringToDouble(final String toConvert, final double defaultValue) {
        if (isEmpty(toConvert)) {
            return defaultValue;
        }

        try {
            return Double.parseDouble(toConvert);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Converts a String object to a Long. If an error occurs, a message is output to the error
     * stream, and the given default value is returned.
     * @param toConvert The String to be converted.
     * @param defaultValue The default value to be returned if an error occurs.
     * @return The converted double, or the given default value if an error occured.
     */

    public static Long stringToLong(final String toConvert, final Long defaultValue) {
        if (isEmpty(toConvert)) {
            return defaultValue;
        }

        try {
            return Long.valueOf(toConvert);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * Format a number into a percentage value.
     * @param in The number to be formatted.
     * @return The formatted percentage.
     */
    public static String toPercentNumericString(final float in) {
        final NumberFormat nf = NumberFormat.getInstance();
        if (nf instanceof DecimalFormat) {
            final DecimalFormat df = (DecimalFormat) nf;
            df.setDecimalSeparatorAlwaysShown(false);
            df.applyPattern("##0.##");
        }
        return nf.format(in);
    }

    /**
     * Generate a string representation of the specified object.
     * @param o The object to be processed.
     * @return The string representation of the specified object.
     */
    public static String toString(final Object o) {
        String result = "[Not possible to stringise this object]";
        final StringBuffer diagBuf = new StringBuffer(500);
        try {
            final List<Object> allObjectsDumpedList = new ArrayList<Object>();
            extractStringFromObject(o, (o == null) ? null : o.getClass(), allObjectsDumpedList, diagBuf);
            result = diagBuf.toString();
        } catch (final Exception ex) {
            // don't care that this doesn't work, as it is just trying to add to debugging
            // messages. We will have to do the best we can without it. But log it anyway.
            System.err.println(ex.getLocalizedMessage() + ", for [" + diagBuf.toString() + "]");
        }
        return result;
    }

    // ========================================================================================
    // WARNING - DO NOT TRY TO SPLIT THE FOLLOWING METHOD INTO SMALLER METHODS
    // ========================================================================================

    /**
     * Generate a string representation of the specified object.
     * @param o The object to be processed.
     * @param clazz The class of the object to be processed.
     * @param allObjectsDumpedList The list of objects we have already processed.
     * @param diagBuf The StringBuffer we are building up of the toString() result.
     */
    @SuppressWarnings("unused")
	private static void extractStringFromObject(final Object o,
        final Class<?> clazz,
        final List<Object> allObjectsDumpedList,
        final StringBuffer diagBuf) {

        // have we already processed this object?
        final int allObjectsDumpedListSize = allObjectsDumpedList.size();
        for (int i = 0; i < allObjectsDumpedListSize; i++) {
            if (allObjectsDumpedList.get(i) == o) {
                return;
            }
        }

        // now get the string value
        if (diagBuf.length() > MAXIMUM_TO_STRING) {
            // handle when the string gets too long
            diagBuf.append(" ... ");
            return;
        }
        if (o == null) {
            // handle null values
            diagBuf.append("null");
            return;
        }

        // get the simple class name
        String simpleClassName = (clazz == null) ? null : clazz.getSimpleName();
        if (simpleClassName == null) {
            simpleClassName = "{Anonymous class}";
        }

        if (o instanceof String) {
            final String stringValue = (String) o;
            diagBuf.append("\"");
            if (stringValue.length() > MAXIMUM_LENGTH_STRING_TO_STRING) {
                diagBuf.append(stringValue.substring(0, MAXIMUM_LENGTH_STRING_TO_STRING));
                diagBuf.append("...");
            } else {
                diagBuf.append(stringValue);
            }
            diagBuf.append("\"");
            return;
        }
        if (o instanceof Character) {
            diagBuf.append("\'");
            diagBuf.append(((Character) o).charValue());
            diagBuf.append("\'");
            return;
        }
        if ((o instanceof Number) || (o instanceof Boolean) || (o instanceof java.util.Date) || (o instanceof java.sql.Date) ||
                (o instanceof java.sql.Timestamp) || ((clazz != null) && (clazz.isPrimitive()))) {
            diagBuf.append(o.toString());
            return;
        }
        if (o instanceof List<?>) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            // handle lists
            final List<?> theList = (List<?>) o;
            diagBuf.append(simpleClassName);
            diagBuf.append("=[");
            for (int i = 0; (diagBuf.length() <= MAXIMUM_TO_STRING) && (i < theList.size()); i++) {
                if (i > 0) {
                    diagBuf.append(", ");
                }
                final Object listObject = theList.get(i);
                if (listObject == null) {
                    diagBuf.append("null");
                } else {
                    extractStringFromObject(listObject, listObject.getClass(), allObjectsDumpedList, diagBuf);
                }
            }
            if (diagBuf.length() >= MAXIMUM_TO_STRING) {
                diagBuf.append(", ... ");
            }
            diagBuf.append("]");
            return;
        }
        if (o instanceof Map<?, ?>) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            // handle maps
            final Map<?, ?> theMap = (Map<?, ?>) o;
            diagBuf.append(simpleClassName);
            diagBuf.append("=[");
            Set<?> keySet = null;
            try {
                // some Maps return UnsupportedOperationExceptions (eg. org.hibernate.util.IdentityMap)
                keySet = theMap.keySet();

                // add to the logging
                final Iterator<?> keyIterator = keySet.iterator();
                if (keyIterator != null) {
                    boolean isFirstMapEntry = true;
                    Object nextKey = null;
                    Object nextValue = null;
                    while ((keyIterator.hasNext()) && (diagBuf.length() <= MAXIMUM_TO_STRING)) {
                        nextKey = keyIterator.next();
                        nextValue = theMap.get(nextKey);
                        if (isFirstMapEntry == false) {
                            diagBuf.append(", ");
                        }
                        extractStringFromObject(nextKey, nextKey.getClass(), allObjectsDumpedList, diagBuf);
                        diagBuf.append("=[");
                        if (nextValue == null) {
                            diagBuf.append("null");
                        } else {
                            extractStringFromObject(nextValue, nextValue.getClass(), allObjectsDumpedList, diagBuf);
                        }
                        diagBuf.append("]");
                        isFirstMapEntry = false;
                    }
                }
            } catch (final UnsupportedOperationException ex) {
                // nothing to do
                Set<?> entrySet = null;
                try {
                    // some Maps may return UnsupportedOperationExceptions
                    entrySet = theMap.entrySet();
                    final Iterator<?> entryIterator = entrySet.iterator();
                    if (entryIterator != null) {
                        boolean isFirstMapEntry = true;
                        Map.Entry<?, ?> nextEntry = null;
                        Object nextKey = null;
                        Object nextValue = null;
                        while ((entryIterator.hasNext()) && (diagBuf.length() <= MAXIMUM_TO_STRING)) {
                            nextEntry = (Entry<?, ?>) entryIterator.next();
                            nextKey = nextEntry.getKey();
                            nextValue = nextEntry.getValue();
                            if (isFirstMapEntry == false) {
                                diagBuf.append(", ");
                            }
                            extractStringFromObject(nextKey, nextKey.getClass(), allObjectsDumpedList, diagBuf);
                            diagBuf.append("=[");
                            if (nextValue == null) {
                                diagBuf.append("null");
                            } else {
                                extractStringFromObject(nextValue, nextValue.getClass(), allObjectsDumpedList, diagBuf);
                            }
                            diagBuf.append("]");
                            isFirstMapEntry = false;
                        }
                    }
                } catch (final UnsupportedOperationException ex2) {
                    diagBuf.append("Not possible to stringise this object");
                }
            }
            if (diagBuf.length() >= MAXIMUM_TO_STRING) {
                diagBuf.append(", ... ");
            }
            diagBuf.append("]");
            return;
        }
        if (o instanceof boolean[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final boolean[] booleanArray = (boolean[]) o;
            diagnoseArray(simpleClassName, booleanArray, diagBuf);
            return;
        }
        if (o instanceof char[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final char[] charArray = (char[]) o;
            diagnoseArray(simpleClassName, charArray, diagBuf);
            return;
        }
        if (o instanceof byte[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final byte[] byteArray = (byte[]) o;
            diagnoseArray(simpleClassName, byteArray, diagBuf);
            return;
        }
        if (o instanceof short[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final short[] shortArray = (short[]) o;
            diagnoseArray(simpleClassName, shortArray, diagBuf);
            return;
        }
        if (o instanceof int[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final int[] intArray = (int[]) o;
            diagnoseArray(simpleClassName, intArray, diagBuf);
            return;
        }
        if (o instanceof long[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final long[] longArray = (long[]) o;
            diagnoseArray(simpleClassName, longArray, diagBuf);
            return;
        }
        if (o instanceof float[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final float[] floatArray = (float[]) o;
            diagnoseArray(simpleClassName, floatArray, diagBuf);
            return;
        }
        if (o instanceof double[]) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            final double[] doubleArray = (double[]) o;
            diagnoseArray(simpleClassName, doubleArray, diagBuf);
            return;
        }
        if ((clazz != null) && (clazz.isArray())) {
            // add this object to the list we have already processed
            allObjectsDumpedList.add(o);
            diagBuf.append(simpleClassName);
            diagBuf.append("=");
            diagBuf.append("[");
            try {
                // handle arrays
                final Object[] objectArray = (Object[]) o;
                for (int i = 0; (i < objectArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
                    if (i > 0) {
                        diagBuf.append(", ");
                    }
                    extractStringFromObject(objectArray[i],
                        (objectArray[i] == null) ? null : objectArray[i].getClass(),
                        allObjectsDumpedList,
                        diagBuf);
                }
            } catch (final ClassCastException ex) {
                diagBuf.append(o);
            }
            if (diagBuf.length() >= MAXIMUM_TO_STRING) {
                diagBuf.append(", ... ");
            }
            diagBuf.append("]");
            return;
        }

        // add this object to the list we have already processed
        allObjectsDumpedList.add(o);
        // go through all of the fields of this class and dump out their contents
        final Field[] fieldsArray = (clazz == null) ? null : clazz.getDeclaredFields();
        if (fieldsArray != null) {
            AccessibleObject.setAccessible(fieldsArray, true);
            boolean isAnythingAdded = false;
            for (int i = 0; (diagBuf.length() <= MAXIMUM_TO_STRING) && (i < fieldsArray.length); i++) {
                if (Modifier.isStatic(fieldsArray[i].getModifiers()) == false) {
                    Object fieldValue = null;
                    try {
                        fieldValue = fieldsArray[i].get(o);
                    } catch (final IllegalAccessException e) {
                        fieldValue = "[Exception: " + e.getLocalizedMessage() + "]";
                    }
                    if (fieldValue == null) {
                        if (TO_STRING_SHOW_NULL_FIELD_VALUES == true) {
                            if (isAnythingAdded) {
                                diagBuf.append(", ");
                            }
                            diagBuf.append(fieldsArray[i].getName());
                            diagBuf.append("=null");
                            isAnythingAdded = true;
                        }
                    } else {
                        final Class<?> fieldValueClass = fieldValue.getClass();
                        if ((Modifier.isFinal(fieldsArray[i].getModifiers()) == false) ||
                                ((fieldValueClass.isPrimitive() == false) && (isImmutable(fieldValue, fieldValueClass) == false))) {
                            if (isAnythingAdded) {
                                diagBuf.append(", ");
                            }
                            diagBuf.append(fieldsArray[i].getName());
                            diagBuf.append("=[");
                            extractStringFromObject(fieldValue, fieldValueClass, allObjectsDumpedList, diagBuf);
                            diagBuf.append("]");
                            isAnythingAdded = true;
                        }
                    }
                }
            }
            if (diagBuf.length() >= MAXIMUM_TO_STRING) {
                diagBuf.append(", ... ");
            }
        }
        // pass the call on down to the superclass
        final Class<?> superClass = (clazz == null) ? null : clazz.getSuperclass();
        if ((superClass != null) && (superClass.getSuperclass() != null) && (diagBuf.length() <= MAXIMUM_TO_STRING)) {
            diagBuf.append(", Super=");
            diagBuf.append(simpleClassName);
            diagBuf.append("=[");
            extractStringFromObject(o, superClass, allObjectsDumpedList, diagBuf);
            diagBuf.append("]");
        }
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param doubleArray the double array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final double[] doubleArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < doubleArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Double.toString(doubleArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param floatArray the float array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final float[] floatArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < floatArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Float.toString(floatArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param longArray the long array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final long[] longArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < longArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Long.toString(longArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param intArray the int array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final int[] intArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < intArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Integer.toString(intArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param shortArray the short array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final short[] shortArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < shortArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Short.toString(shortArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param byteArray the byte array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final byte[] byteArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        final StringBuffer stringBufferValueOfBytes = new StringBuffer(new String(byteArray));
        if (stringBufferValueOfBytes.length() > MAXIMUM_LENGTH_STRING_TO_STRING) {
            stringBufferValueOfBytes.delete(MAXIMUM_LENGTH_STRING_TO_STRING, stringBufferValueOfBytes.length());
            stringBufferValueOfBytes.append("...");
        }
        char nextChar = ' ';
        for (int i = (stringBufferValueOfBytes.length() - 1); i >= 0; i--) {
            nextChar = stringBufferValueOfBytes.charAt(i);
            if ((Character.isWhitespace(nextChar) == false) && (Character.isLetterOrDigit(nextChar) == false)) {
                stringBufferValueOfBytes.setCharAt(i, '.');
            }
        }
        diagBuf.append(stringBufferValueOfBytes.toString());
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param charArray the char array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final char[] charArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < charArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            diagBuf.append(charArray[i]);
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    /**
     * Diagnose array.
     *
     * @param simpleClassName the simple class name
     * @param booleanArray the boolean array
     * @param diagBuf the diag buf
     */
    private static void diagnoseArray(final String simpleClassName, final boolean[] booleanArray, final StringBuffer diagBuf) {
        diagBuf.append(simpleClassName);
        diagBuf.append("=");
        diagBuf.append("[");
        for (int i = 0; (i < booleanArray.length) && (diagBuf.length() <= MAXIMUM_TO_STRING); i++) {
            if (i > 0) {
                diagBuf.append(", ");
            }
            diagBuf.append(Boolean.toString(booleanArray[i]));
        }
        if (diagBuf.length() >= MAXIMUM_TO_STRING) {
            diagBuf.append(", ... ");
        }
        diagBuf.append("]");
    }

    // ========================================================================================
    // WARNING - DO NOT TRY TO SPLIT THE PREVIOUS METHOD INTO SMALLER METHODS
    // ========================================================================================

    /**
     * Determine if the specified class is immutable.
     * @param fieldValue The field value. 
     * @param fieldValueClass The class.
     * @return True, immutable. False, mutable.
     */
    private static boolean isImmutable(final Object fieldValue, final Class<?> fieldValueClass) {
        boolean result = false;
        if ((fieldValue != null) && (fieldValueClass != null)) {
            result =
                    (fieldValue instanceof String) || (fieldValue instanceof Character) || (fieldValue instanceof Boolean) ||
                            (fieldValue instanceof Byte) || (fieldValue instanceof Short) || (fieldValue instanceof Integer) ||
                            (fieldValue instanceof Long) || (fieldValue instanceof Float) || (fieldValue instanceof Double) ||
                            (fieldValue instanceof Enum<?>) || (fieldValueClass == BigDecimal.class) ||
                            (fieldValueClass == MathContext.class) || (fieldValueClass == InetAddress.class) ||
                            (fieldValueClass == Inet4Address.class) || (fieldValueClass == Inet6Address.class) ||
                            (fieldValueClass == File.class) || (fieldValueClass == URI.class) || (fieldValueClass == URL.class);
        }
        return result;
    }

    /**
     * Translate any wildcard text ('*' on screen) into the database wildcard text ('%' for the database).
     * @param dataEnteredSearchText The data that was entered on the screen.
     * @return The database wildcard text.
     */
    public static String translateWildcardLikeSearchText(final String dataEnteredSearchText) {
        String result = null;
        if (dataEnteredSearchText != null) {
            result = dataEnteredSearchText.replaceAll("\\*", "%");
            result = result.replaceAll("%%", "%");
        }
        return result;
    }

    /**
     * Determine if search text contains a wildcard character.
     * @param dataEnteredSearchText The search text.
     * @return True, contains a wildcard character. False, doesn't.
     */
    public static boolean searchTextContainsWildcardCharacter(final String dataEnteredSearchText) {
        boolean containsWildcardCharacter = false;
        if (dataEnteredSearchText != null) {
            containsWildcardCharacter = (dataEnteredSearchText.indexOf('*') >= 0) || (dataEnteredSearchText.indexOf('%') >= 0);
        }
        return containsWildcardCharacter;
    }

    /**
     * Null safe check whether two strings are equal.
     * @param value1
     * @param value2
     * @return true if the two strings are equal, otherwise false
     */
    public static boolean equal(final String value1, final String value2) {
    	if (value1 == null) {
    		if (value2 == null) {
    			return true;
    		} else {
    			return false;
    		}
    	}
    	
    	return value1.equals(value2);
    }
    
}
