/*
 * Strings.java
 * Copyright (c) 2008
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see <http://www.gnu.org/licenses/>.
 */
package com.google.code.struts.annotations.utils;


/**
 * Simple utility class for making some common operations on
 * {@link String}s objects.
 *
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 */
public class Strings {

	//~ Constructors -----------------------------------------------------------

    /**
     * This class should not be instantiated.
     */
    private Strings() {
    }

    //~ Methods ----------------------------------------------------------------

    /**
     * Tests if a {@link String} object it's empty.
     * @param str The {@link String} to test
     * @return <code>true</code> if the {@link String} object it's empty (or <code>null</code>),
     *         <code>false</code> otherwise.
     */
    public static boolean isEmpty(String str) {
        return ((str == null) || (str.trim().length() == 0));
    }

    /**
     * Lower-camel-cases the simple name of a class.
     * <p>Example:<p>
     * <code>Strings.capitalize(MySuperSuperSuperClass.class)</code>
     * <p>Will return: <p> <code>mySuperSuperSuperClass</code>
     * @param c The class whose name lower case
     * @return The simple name of the class, lower-camel-cased
     */
    public static String lowerCamelCase(Class<?> c) {
        StringBuffer buff = new StringBuffer(c.getSimpleName());
        Character first = Character.toLowerCase(buff.charAt(0));
        buff.setCharAt(0, first);
        return buff.toString();
    }
}
