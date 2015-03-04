/*
 * SetProperties.java
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
 * Sets properties for a given action mapping.
 * <p>
 * This annotation has only sense when it's combined with the {@link Action} annotation.
 *
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see Action
 * @see Property
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SetProperties {
	
	/**
	 * The properties to set
	 */
	Property[] value();
}
