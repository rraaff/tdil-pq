/*
 * Forward.java
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

import javax.servlet.RequestDispatcher;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * The "forward" element describes an ActionForward that is to be made available
 * to an {@link Action} as a return value. An {@link ActionForward} is referenced by a logical
 * name and encapsulates a URI. A "forward" element may be used to describe both
 * global and local ActionForwards. Global forwards are available to all the
 * {@link Action} objects in the application module. Local forwards can be nested within
 * an <code>&lt;action&gt;</code> element and only available to an {@link Action} object when it is invoked
 * through that {@link ActionMapping}.
 * 
 * An {@link Action} can have multiple forwards. In those cases, actions
 * should be annotated with {@link Forwards} annotation instead.
 * If both annotations are used ({@link Forwards} and {@link Forward}), 
 * {@link Forward} has higher priority.
 * 
 * <p>
 * This annotation has only sense when it's combined with the {@link Action} or {@link Forwards} annotations.
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see Action
 * @see Forwards
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Forward {

	/**
	 * The unique identifier for this forward. Referenced by the {@link Action}
	 * object at runtime to select - by its logical name - the resource that
	 * should complete the request/response.
	 */
	String name();

	/**
	 * The module-relative or context-relative path to the resources that is
	 * encapsulated by the logical name of this {@link ActionForward}. If the
	 * path is to be considered context-relative when used in a modular
	 * application, then the contextRelative attribute should be set to
	 * <code>true</code>. This value should begin with a slash ("/") character.
	 */
	String path();

	/**
	 * Set to <code>true</code> if a redirect instruction should be issued to
	 * the user-agent so that a new request is issued for this forward's
	 * resource. If <code>true</code>, {@link RequestDispatcher}.Redirect is
	 * called. If <code>false</code>,
	 * {@link RequestDispatcher#forward(javax.servlet.ServletRequest, javax.servlet.ServletResponse)}
	 * is called instead.
	 */
	boolean redirect() default false;

	/**
	 * Set this to <code>true</code> if, in a modular application, the path
	 * attribute starts with a slash "/" and should be considered relative to
	 * the entire web application rather than the module.
	 */
	boolean contextRelative() default false;

	/**
	 * The class of {@link ActionForward} subclass to use.
	 */
	Class<? extends ActionForward> className() default ActionForward.class;
}
