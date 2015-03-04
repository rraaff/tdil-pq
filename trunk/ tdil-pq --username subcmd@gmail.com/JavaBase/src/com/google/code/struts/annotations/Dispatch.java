/*
 * Dispatch.java
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

import org.apache.struts.actions.DispatchAction;

/**
 * Indicates the name of the parameter to be used when invoking
 * a {@link DispatchAction}.
 * <p>
 * This same thing can also be specified with the {@link Parameter} annotation, the
 * purpose of this annotation it's only to clarify the action configuration.
 * <p>
 * This annotation has only sense when it's combined with {@link Action} annotation.
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 *
 */
public @interface Dispatch {

	/**
	 * The name of the paramater that selects the method
	 * to be executed. 
	 */
	String value() default "method";
}
