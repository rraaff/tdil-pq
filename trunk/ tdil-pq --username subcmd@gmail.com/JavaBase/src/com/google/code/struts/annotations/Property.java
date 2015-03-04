/*
 * Property.java
 * Copyright (C) 2008 by Ezequiel Turovetzky
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
package com.google.code.struts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the method name and initial value of an additional JavaBean
 * configuration property. When the object representing the surrounding element
 * is instantiated, the accessor for the indicated property is called and passed
 * the indicated value.
 * 
 * <p>
 * This annotation has only sense when it's combined with the {@link SetProperties} annotation.
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see SetProperties
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Property {

	/**
	 * Name of the JavaBeans property whose setter method will be called
	 */
	String property();

	/**
	 * String representation of the value to which this property will be set,
	 * after suitable type conversion
	 */
	String value();
}
