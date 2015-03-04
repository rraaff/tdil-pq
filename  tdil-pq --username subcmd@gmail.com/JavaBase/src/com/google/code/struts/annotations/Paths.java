/*
 * Paths.java
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
package com.google.code.struts.annotations;

/**
 * Specified all the paths that executes the annotated
 * action.
 * This annotation is usefull when an action it's a shared action
 * (for example, a pagination action) and various paths are needed.
 * 
 * This annotation it's processed when the {@link Action} annotation
 * has no specified {@link Action#path()}.
 * 
 * @see Path
 * 
 */
public @interface Paths {
	
	/**
	 * Paths that executes the action
	 * @return The paths that executes the action
	 */
	Path[] value();
}
