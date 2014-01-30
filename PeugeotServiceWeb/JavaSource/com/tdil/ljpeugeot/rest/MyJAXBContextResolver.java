package com.tdil.ljpeugeot.rest;


/*@Provider
public class MyJAXBContextResolver implements ContextResolver<JAXBContext> {

    private JAXBContext context;
    private Class[] types = {RESTResponse.class};

    public MyJAXBContextResolver() throws Exception {
        this.context = new JSONJAXBContext(
                JSONConfiguration.mapped()
                                   .rootUnwrapping(true)
                                   //.arrays("jobs")
                                   .nonStrings("ok")
                                   .build(),
                types);
    }

    public JAXBContext getContext(Class<?> objectType) {
        return (types[0].equals(objectType)) ? context : null;
    }
}*/