/*
 * Action.java
 * Copyright (c) Ezequiel Turovetzky 2008
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.google.code.struts.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.struts.action.ActionForm;

/**
 * Indicates that a class it's a Struts' action.
 * 
 * <p>This annotation can be combined with:
 * 
 * <ul>
 * <li> {@link Input} </li>
 * <li> {@link SetProperties}</li>
 * <li> {@link Validate} </li>
 * </ul>
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {
	
	/**
	 * Context-relative path of the submitted request, starting with a slash
	 * ("/") character, and omitting any filename extension if extension mapping
	 * is being used. <br>
	 * If no specified, the path will be the name of the annotated action.
	 */
	String path() default "";	

	/**
	 * Name of the form bean associated with the annotated Action.
	 */
	Class<? extends ActionForm> form() default ActionForm.class;
	
	String parameter() default "";
}