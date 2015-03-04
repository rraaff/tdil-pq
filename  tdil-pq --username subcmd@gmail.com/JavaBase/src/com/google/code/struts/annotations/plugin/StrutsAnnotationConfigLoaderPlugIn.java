/*
 * StrutsAnnotationConfigLoaderPugIn.java
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
package com.google.code.struts.annotations.plugin;

import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.PlugIn;
import org.apache.struts.config.ActionConfig;
import org.apache.struts.config.FormBeanConfig;
import org.apache.struts.config.FormPropertyConfig;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.RequestUtils;

import com.google.code.struts.annotations.Action;
import com.google.code.struts.annotations.Dispatch;
import com.google.code.struts.annotations.Form;
import com.google.code.struts.annotations.FormProperties;
import com.google.code.struts.annotations.FormProperty;
import com.google.code.struts.annotations.Forward;
import com.google.code.struts.annotations.Forwards;
import com.google.code.struts.annotations.Input;
import com.google.code.struts.annotations.Parameter;
import com.google.code.struts.annotations.Property;
import com.google.code.struts.annotations.Scope;
import com.google.code.struts.annotations.SetProperties;
import com.google.code.struts.annotations.Validate;
import com.google.code.struts.annotations.utils.PackageIntrospector;
import com.google.code.struts.annotations.utils.Strings;


/**
 * Struts plugin that loads configuration from the classes annotated with {@link Action}
 * and {@link Form} annotations.
 * <p>
 * @author <a href="mailto:tezequiel@gmail.com">Ezequiel Turovetzky</a>
 */
public class StrutsAnnotationConfigLoaderPlugIn implements PlugIn {

	//~ Static fields/initializers ---------------------------------------------

    /** Logger */
    protected static final Log logger = LogFactory.getLog(StrutsAnnotationConfigLoaderPlugIn.class);

    //~ Instance fields --------------------------------------------------------

    /** Root package for all the actions */
    private String annotatedActionsPackages;
    /** Root package for all the form beans */
    private String annotatedFormsPackages;

    //~ Constructors -----------------------------------------------------------

    /**
     * Creates a new object of this class.
     */
    public StrutsAnnotationConfigLoaderPlugIn() {
    	annotatedActionsPackages = "";
    	annotatedFormsPackages = "";
    }

    //~ Methods ----------------------------------------------------------------

    /** @see PlugIn#init(ActionServlet, ModuleConfig) */
    public void init(ActionServlet servlet, ModuleConfig config) throws ServletException {
    	
    	logger.info("Initializing Struts configuration from annotated classes...");
    	
    	try {
    		processAnnotatedForms(config); // Important to process the forms first!
    		processAnnotatedActions(config);
    		
    	} catch(Exception e) {
    		throw new ServletException("Error while loading annotated configuration: " + e.getMessage(), e);
    	}
    }
    
    /**
     * Does nothing.
     * @see PlugIn#destroy()
     */
    public void destroy() {
    	;
    }

    /**
     * Gets the names of all the root packages where the actions beans are.
     * @return The names of all the root packages where the actions beans are
     */
	public String getAnnotatedActionsPackages() {
		return annotatedActionsPackages;
	}

    /**
     * Sets the names of all the root packages where the actions beans are.
     * <p>The given string object it's a comma-separated list of package names.
     * @param annotatedActionsPackages The names of all the root packages where the actions beans are
     */
	public void setAnnotatedActionsPackages(String annotatedActionsPackages) {
		this.annotatedActionsPackages = annotatedActionsPackages;
	}

    /**
     * Gets the names of all the root packages where the form beans are.
     * @return The names of all the root packages where the form beans are
     */
	public String getAnnotatedFormsPackages() {
		return annotatedFormsPackages;
	}

    /**
     * Sets the names of all the root packages where the form beans are.
     * <p>The given string object it's a comma-separated list of package names.
     * @param annotatedFormsPackages The names of all the root packages where the form beans are
     */
	public void setAnnotatedFormsPackages(String annotatedFormsPackages) {
		this.annotatedFormsPackages = annotatedFormsPackages;
	}
	
	/**
	 * Process all the classes within the specified packages that are annotated
	 * with the {@link Action @Action} annotation 
	 * @param moduleConfig The Struts configuration object
	 * @throws Exception On any error
	 */
	private void processAnnotatedActions(ModuleConfig moduleConfig) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(annotatedActionsPackages, ",");
		while(tokenizer.hasMoreTokens()) {
			String pkg = tokenizer.nextToken();
			logger.debug("Processing annotated actions from package: " + pkg);
			List<Class<?>> classes = PackageIntrospector.getClasses(pkg);
			for(Class<?> c : classes) {
				if(c.isAnnotationPresent(Action.class)) {
					Action actionAnnotation = c.getAnnotation(Action.class);
					ActionConfig actionConfig = (ActionConfig)RequestUtils.applicationInstance(moduleConfig.getActionMappingClass());
					// Process and "type" and "path"
					actionConfig.setType(c.getName());
					if(Strings.isEmpty(actionAnnotation.path())) {
						actionConfig.setPath("/" + Strings.lowerCamelCase(c));
					} else if(actionAnnotation.path().startsWith("/")) {
						actionConfig.setPath(actionAnnotation.path());
					} else {
						actionConfig.setPath("/" + actionAnnotation.path());
					}
					if (!Strings.isEmpty(actionAnnotation.parameter())) {
						actionConfig.setParameter(actionAnnotation.parameter());
					}
					
					// Process "name" (the form)
                    if(!actionAnnotation.form().equals(ActionForm.class)) {
                        String form = findFormName(actionAnnotation.form(), moduleConfig);
                        actionConfig.setName(form);
                    }
					// Process "forward" with annotation "@Forward" or "@Forwards". "@Forward has higher priority
					if(c.isAnnotationPresent(Forward.class)) {
						Forward forwardAnnotation = c.getAnnotation(Forward.class);
						ForwardConfig forwardConfig = (ForwardConfig)RequestUtils.applicationInstance(moduleConfig.getActionForwardClass());
						//forwardConfig.setContextRelative(forwardAnnotation.contextRelative());
						forwardConfig.setName(forwardAnnotation.name());
						forwardConfig.setPath(forwardAnnotation.path());
						forwardConfig.setRedirect(forwardAnnotation.redirect());
						actionConfig.addForwardConfig(forwardConfig);
					} else if(c.isAnnotationPresent(Forwards.class)) {
						Forwards forwardsAnnotation = c.getAnnotation(Forwards.class);
						for(Forward forwardAnnotation : forwardsAnnotation.value()) {
							ForwardConfig forwardConfig = (ForwardConfig)RequestUtils.applicationInstance(moduleConfig.getActionForwardClass());
							//forwardConfig.setContextRelative(forwardAnnotation.contextRelative());
							forwardConfig.setName(forwardAnnotation.name());
							forwardConfig.setPath(forwardAnnotation.path());
							forwardConfig.setRedirect(forwardAnnotation.redirect());
							actionConfig.addForwardConfig(forwardConfig);
						}
					}
					// Process "input"
					if(c.isAnnotationPresent(Input.class)) {
						actionConfig.setInput(c.getAnnotation(Input.class).value());
					}
					// Process "scope"
					if(c.isAnnotationPresent(Scope.class)) {
						actionConfig.setScope(c.getAnnotation(Scope.class).value().name().toLowerCase());
					}
					// Process "parameter"
					if(c.isAnnotationPresent(Parameter.class)) {
						actionConfig.setParameter(c.getAnnotation(Parameter.class).value());
					}
					// Process "parameter" with "@Dispath" annotation
					if(c.isAnnotationPresent(Dispatch.class)) {
						actionConfig.setParameter(c.getAnnotation(Dispatch.class).value());
					}
					// Process "set-property
                    if(c.isAnnotationPresent(SetProperties.class)) {
                        SetProperties setPropertiesAnnotation = c.getAnnotation(SetProperties.class);
                        for(Property property : setPropertiesAnnotation.value()) {
                            BeanUtilsBean.getInstance().setProperty(actionConfig, property.property(), property.value());
                        }
                    }
                    // Set "validate"
                    actionConfig.setValidate(c.isAnnotationPresent(Validate.class));
                    
                    moduleConfig.addActionConfig(actionConfig);
                    logger.debug("Added annotated configuration for action: " + c.getName());
				}
			}
		}
	}
	
	/**
	 * Process all the classes within the specified packages that are annotated
	 * with the {@link Form @Form} annotation 
	 * @param moduleConfig The Struts configuration object
	 * @throws Exception On any error
	 */
	private void processAnnotatedForms(ModuleConfig moduleConfig) throws Exception {
		StringTokenizer tokenizer = new StringTokenizer(annotatedFormsPackages, ",");
		while(tokenizer.hasMoreTokens()) {
			String pkg = tokenizer.nextToken();
			logger.debug("Processing annotated forms from package: " + pkg);
			List<Class<?>> classes = PackageIntrospector.getClasses(pkg);
			for(Class<?> c : classes) {
				if(c.isAnnotationPresent(Form.class)) {
                    FormBeanConfig formBeanConfig = (FormBeanConfig)RequestUtils.applicationInstance(moduleConfig.getActionFormBeanClass());
                    // Process "type"
                    formBeanConfig.setType(c.getName());
                    // Process "name"
                    String name = c.getAnnotation(Form.class).name();
                    if(Strings.isEmpty(name)) {
                    	name = Strings.lowerCamelCase(c);
                    }
                    formBeanConfig.setName(name);
                    // Process properties for dynamic forms
                    if(c.isAnnotationPresent(FormProperties.class)) {
                        FormProperties formPropertiesAnnotation = c.getAnnotation(FormProperties.class);
                        for(FormProperty fp : formPropertiesAnnotation.properties()) {
                            FormPropertyConfig formPropertyConfig = new FormPropertyConfig();
                            formPropertyConfig.setName(fp.name());
                            formPropertyConfig.setType(fp.type().getName());
                            formBeanConfig.addFormPropertyConfig(formPropertyConfig);
                        }
                    }
                    moduleConfig.addFormBeanConfig(formBeanConfig); 
                    logger.debug("Added annotated configuration for form: " + c.getName());
				}
			}
		}
	}
	
    /**
     * Looks for the form name given to the form whose class it's the specified.
     * @param c Class of the form
     * @param config The module config
     * @return The name given to the form
     * @throws ServletException If the given form's class doesn't exist 
     */
    @SuppressWarnings("unchecked")
    private String findFormName(Class c, ModuleConfig config) throws ServletException {
        for(FormBeanConfig fbc : config.findFormBeanConfigs()) {
            if(fbc.getType().equals(c.getName())) {
                return fbc.getName();
            }
        }
        throw new ServletException("There's no registered form bean of class: " + c.getName());
    }
}
