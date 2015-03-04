/*
 * FormProperties.java
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

/**
 * Annotation used to group all the properties to be added to a
 * dynamic form bean.
 * <br>By combining this annotation with the {@link Form} annotation, the annotaion
 * processor understands that the form bean it's a dynamic form bean.
 * 
 * <p>
 * This annotation has only sense when it's combined with the {@link Form} annotation.
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see Form
 * @see FormProperty
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FormProperties {
	
	/**
	 * The properties to add to the dynamic form bean.
	 */
	FormProperty[] properties();
}
