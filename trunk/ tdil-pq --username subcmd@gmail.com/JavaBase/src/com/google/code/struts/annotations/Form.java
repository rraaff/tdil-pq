/*
 * Form.java
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
 * Indicates that a class it's a Struts' form bean.
 * <p>Example 1:
 * <pre>
 * 		@Form
 * 		public class MovieDetailsForm extends ActionForm {
 * 		...
 * 		}
 * </pre>
 * By not specifying the name of the form bean, the annotation
 * processor will use the name of the annotated class as the name
 * of the form bean. By this, the name of the above example will
 * be <code>movieDetailsForm</code>.
 * <p>
 * Dynamic form beans are also supported, just by annotating the form bean
 * with the {@link FormProperties} annotation (along with <i>this</i> annotation, of course)
 * <p>
 * Example 2:
 * <pre>
 * 		@Form
 * 		@FormProperties(properties={
 * 			@FormProperty(name="name", type=String.class)
 * 		})
 * 		public class MovieDetailsDynamicForm extends MovieDetailsForm {
 * 		....
 * 		}
 * </pre>
 * 
 * 
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 * 
 * @see FormProperties
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Form {
	
	/**
	 * The name of the form bean.
	 * <br>If no specified the name of the annotated form will be used.
	 */
	String name() default "";
}