package com.zero9vn.utils;

import com.zero9vn.constants.SimpleDateFormatConstants;

import java.util.*;
import java.util.stream.Collectors;

public class StringUtils {
    public static void main(String[] args) {

    }

    /**
     * @param input
     * @return
     */
    public static boolean isEmpty(Object input) {
        if (null == input) {
            return true;
        } else if ("ArrayList".equals(input.getClass().getSimpleName())) {
            return ((Collection) input).isEmpty();
        } else {
            return input.toString().trim().isEmpty() || "null".equals(input.toString());
        }
    }

    /**
     * @param input
     * @return
     */
    public static boolean isNotEmpty(Object input) {
        return !isEmpty(input);
    }

    /**
     * @param input
     * @param <T>
     * @return
     */
    public static <T> String nullToBlank(T input) {
        if (null == input) {
            return "";
        } else {
            return String.valueOf(input).trim();
        }
    }

    /**
     * @param input
     * @param <T>
     * @return
     */
    public static <T> Object nullToMinus1(T input) {
        if (null == input) {
            return -1;
        } else {
            return input;
        }
    }

    /**
     * @param input
     * @return
     */
    public static String valueOf(Date input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        } else {
            return SimpleDateFormatConstants.formatteryyyyMMddHHmmss.format(input);
        }
    }

    // Capitalizing
    //-----------------------------------------------------------------------

    /**
     * <p>Capitalizes all the whitespace separated words in a String.
     * Only the first character of each word is changed. To convert the
     * rest of each word to lowercase at the same time,
     * use {@link #capitalizeFully(String)}.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A {@code null} input String returns {@code null}.
     * Capitalization uses the Unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalize(null)        = null
     * WordUtils.capitalize("")          = ""
     * WordUtils.capitalize("i am FINE") = "I Am FINE"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return capitalized String, {@code null} if null String input
     * @see #uncapitalize(String)
     * @see #capitalizeFully(String)
     */
    public static String capitalize(final String str) {
        return capitalize(str, null);
    }

    /**
     * <p>Capitalizes all the delimiter separated words in a String.
     * Only the first character of each word is changed. To convert the
     * rest of each word to lowercase at the same time,
     * use {@link #capitalizeFully(String, char[])}.</p>
     *
     * <p>The delimiters represent a set of characters understood to separate words.
     * The first string character and the first non-delimiter character after a
     * delimiter will be capitalized. </p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * Capitalization uses the Unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalize(null, *)            = null
     * WordUtils.capitalize("", *)              = ""
     * WordUtils.capitalize(*, new char[0])     = *
     * WordUtils.capitalize("i am fine", null)  = "I Am Fine"
     * WordUtils.capitalize("i aM.fine", {'.'}) = "I aM.Fine"
     * </pre>
     *
     * @param str        the String to capitalize, may be null
     * @param delimiters set of characters to determine capitalization, null means whitespace
     * @return capitalized String, {@code null} if null String input
     * @see #uncapitalize(String)
     * @see #capitalizeFully(String)
     * @since 2.1
     */
    public static String capitalize(final String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        boolean capitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch, delimiters)) {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                buffer[i] = Character.toTitleCase(ch);
                capitalizeNext = false;
            }
        }
        return new String(buffer);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Converts all the whitespace separated words in a String into capitalized words,
     * that is each word is made up of a titlecase character and then a series of
     * lowercase characters.  </p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A {@code null} input String returns {@code null}.
     * Capitalization uses the Unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalizeFully(null)        = null
     * WordUtils.capitalizeFully("")          = ""
     * WordUtils.capitalizeFully("i am FINE") = "I Am Fine"
     * </pre>
     *
     * @param str the String to capitalize, may be null
     * @return capitalized String, {@code null} if null String input
     */
    public static String capitalizeFully(final String str) {
        return capitalizeFully(str, null);
    }

    /**
     * <p>Converts all the delimiter separated words in a String into capitalized words,
     * that is each word is made up of a titlecase character and then a series of
     * lowercase characters. </p>
     *
     * <p>The delimiters represent a set of characters understood to separate words.
     * The first string character and the first non-delimiter character after a
     * delimiter will be capitalized. </p>
     *
     * <p>A {@code null} input String returns {@code null}.
     * Capitalization uses the Unicode title case, normally equivalent to
     * upper case.</p>
     *
     * <pre>
     * WordUtils.capitalizeFully(null, *)            = null
     * WordUtils.capitalizeFully("", *)              = ""
     * WordUtils.capitalizeFully(*, null)            = *
     * WordUtils.capitalizeFully(*, new char[0])     = *
     * WordUtils.capitalizeFully("i aM.fine", {'.'}) = "I am.Fine"
     * </pre>
     *
     * @param str        the String to capitalize, may be null
     * @param delimiters set of characters to determine capitalization, null means whitespace
     * @return capitalized String, {@code null} if null String input
     * @since 2.1
     */
    public static String capitalizeFully(String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        str = str.toLowerCase();
        return capitalize(str, delimiters);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Uncapitalizes all the whitespace separated words in a String.
     * Only the first character of each word is changed.</p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * WordUtils.uncapitalize(null)        = null
     * WordUtils.uncapitalize("")          = ""
     * WordUtils.uncapitalize("I Am FINE") = "i am fINE"
     * </pre>
     *
     * @param str the String to uncapitalize, may be null
     * @return uncapitalized String, {@code null} if null String input
     * @see #capitalize(String)
     */
    public static String uncapitalize(final String str) {
        return uncapitalize(str, null);
    }

    /**
     * <p>Uncapitalizes all the whitespace separated words in a String.
     * Only the first character of each word is changed.</p>
     *
     * <p>The delimiters represent a set of characters understood to separate words.
     * The first string character and the first non-delimiter character after a
     * delimiter will be uncapitalized. </p>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * WordUtils.uncapitalize(null, *)            = null
     * WordUtils.uncapitalize("", *)              = ""
     * WordUtils.uncapitalize(*, null)            = *
     * WordUtils.uncapitalize(*, new char[0])     = *
     * WordUtils.uncapitalize("I AM.FINE", {'.'}) = "i AM.fINE"
     * </pre>
     *
     * @param str        the String to uncapitalize, may be null
     * @param delimiters set of characters to determine uncapitalization, null means whitespace
     * @return uncapitalized String, {@code null} if null String input
     * @see #capitalize(String)
     * @since 2.1
     */
    public static String uncapitalize(final String str, final char... delimiters) {
        final int delimLen = delimiters == null ? -1 : delimiters.length;
        if (StringUtils.isEmpty(str) || delimLen == 0) {
            return str;
        }
        final char[] buffer = str.toCharArray();
        boolean uncapitalizeNext = true;
        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (isDelimiter(ch, delimiters)) {
                uncapitalizeNext = true;
            } else if (uncapitalizeNext) {
                buffer[i] = Character.toLowerCase(ch);
                uncapitalizeNext = false;
            }
        }
        return new String(buffer);
    }

    //-----------------------------------------------------------------------

    /**
     * <p>Swaps the case of a String using a word based algorithm.</p>
     *
     * <ul>
     *  <li>Upper case character converts to Lower case</li>
     *  <li>Title case character converts to Lower case</li>
     *  <li>Lower case character after Whitespace or at start converts to Title case</li>
     *  <li>Other Lower case character converts to Upper case</li>
     * </ul>
     *
     * <p>Whitespace is defined by {@link Character#isWhitespace(char)}.
     * A {@code null} input String returns {@code null}.</p>
     *
     * <pre>
     * com.zero9vn.utils.StringUtils.swapCase(null)                 = null
     * com.zero9vn.utils.StringUtils.swapCase("")                   = ""
     * com.zero9vn.utils.StringUtils.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     *
     * @param str the String to swap case, may be null
     * @return the changed String, {@code null} if null String input
     */
    public static String swapCase(final String str) {
        if (StringUtils.isEmpty(str)) {
            return str;
        }
        final char[] buffer = str.toCharArray();

        boolean whitespace = true;

        for (int i = 0; i < buffer.length; i++) {
            final char ch = buffer[i];
            if (Character.isUpperCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isTitleCase(ch)) {
                buffer[i] = Character.toLowerCase(ch);
                whitespace = false;
            } else if (Character.isLowerCase(ch)) {
                if (whitespace) {
                    buffer[i] = Character.toTitleCase(ch);
                    whitespace = false;
                } else {
                    buffer[i] = Character.toUpperCase(ch);
                }
            } else {
                whitespace = Character.isWhitespace(ch);
            }
        }
        return new String(buffer);
    }

    //-----------------------------------------------------------------------

    /**
     * Is the character a delimiter.
     *
     * @param ch         the character to check
     * @param delimiters the delimiters
     * @return true if it is a delimiter
     */
    private static boolean isDelimiter(final char ch, final char[] delimiters) {
        if (delimiters == null) {
            return Character.isWhitespace(ch);
        }
        for (final char delimiter : delimiters) {
            if (ch == delimiter) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String leftPad(final String str, final int size, final char padChar) {
        if (str.length() >= size) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size - str.length()) {
            sb.append(padChar);
        }
        sb.append(str);
        return sb.toString();
    }

    /**
     * @param str
     * @param size
     * @param padChar
     * @return
     */
    public static String rightPad(final String str, final int size, final char padChar) {
        if (str.length() >= size) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        while (sb.length() < size - str.length()) {
            sb.append(padChar);
        }
        sb.insert(0, str);
        return sb.toString();
    }

}
