/*
 * Input.java
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

/**
 * The module-relative path of the action or other resource to which control should
 * be returned if a validation error is encountered. Valid only when "name" is
 * specified. Required if "name" is specified and the input bean returns
 * validation errors. Optional if "name" is specified and the input bean does
 * not return validation errors.
 * 
 * <p>
 * This annotation has only sense when it's combined with the {@link Action} annotation.
 *
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see Action
 *
 */
public @interface Input {
	
	/**
	 * The value of the input
	 */
	String value() default "";
}
