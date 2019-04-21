// ******************************************************************************
// Copyright (C) 2017, All Rights Reserved.
// ******************************************************************************
package com.sunlong.cloud.eurekaclient1.auth.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.config.Ini;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.util.Nameable;
import org.apache.shiro.web.config.IniFilterChainResolverFactory;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class ShiroTokenFilterFactoryBean implements FactoryBean, BeanPostProcessor {

    private static transient final Logger log = LoggerFactory.getLogger(ShiroTokenFilterFactoryBean.class);

    private SecurityManager securityManager;

    private Map<String, Filter> filters;

    private Map<String, String> filterChainDefinitionMap; //urlPathExpression_to_comma-delimited-filter-chain-definition

    private AbstractTokenShiroFilter instance;

    public ShiroTokenFilterFactoryBean() {
        this.filters = new LinkedHashMap<String, Filter>();
        this.filterChainDefinitionMap = new LinkedHashMap<String, String>(); //order matters!
    }

    /**
     * Sets the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.  This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @return the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.
     */
    public SecurityManager getSecurityManager() {
        return securityManager;
    }

    /**
     * Sets the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.  This is a
     * required property - failure to set it will throw an initialization exception.
     *
     * @param securityManager the application {@code SecurityManager} instance to be used by the constructed Shiro Filter.
     */
    public void setSecurityManager(SecurityManager securityManager) {
        this.securityManager = securityManager;
    }

    /**
     * Returns the filterName-to-Filter map of filters available for reference when defining filter chain definitions.
     * All filter chain definitions will reference filters by the names in this map (i.e. the keys).
     *
     * @return the filterName-to-Filter map of filters available for reference when defining filter chain definitions.
     */
    public Map<String, Filter> getFilters() {
        return filters;
    }

    /**
     * Sets the filterName-to-Filter map of filters available for reference when creating
     * {@link #setFilterChainDefinitionMap(Map) filter chain definitions}.
     * <p/>
     * <b>Note:</b> This property is optional:  this {@code FactoryBean} implementation will discover all beans in the
     * web application context that implement the {@link Filter} interface and automatically add them to this filter
     * map under their bean name.
     * <p/>
     * For example, just defining this bean in a web Spring XML application context:
     * <pre>
     * &lt;bean id=&quot;myFilter&quot; class=&quot;com.class.that.implements.javax.servlet.Filter&quot;&gt;
     * ...
     * &lt;/bean&gt;</pre>
     * Will automatically place that bean into this Filters map under the key '<b>myFilter</b>'.
     *
     * @param filters the optional filterName-to-Filter map of filters available for reference when creating
     *                {@link #setFilterChainDefinitionMap (java.util.Map) filter chain definitions}.
     */
    public void setFilters(Map<String, Filter> filters) {
        this.filters = filters;
    }

    /**
     * Returns the chainName-to-chainDefinition map of chain definitions to use for creating filter chains intercepted
     * by the Shiro Filter.  Each map entry should conform to the format defined by the
     * {@link FilterChainManager#createChain(String, String)} JavaDoc, where the map key is the chain name (e.g. URL
     * path expression) and the map value is the comma-delimited string chain definition.
     *
     * @return he chainName-to-chainDefinition map of chain definitions to use for creating filter chains intercepted
     *         by the Shiro Filter.
     */
    public Map<String, String> getFilterChainDefinitionMap() {
        return filterChainDefinitionMap;
    }

    /**
     * Sets the chainName-to-chainDefinition map of chain definitions to use for creating filter chains intercepted
     * by the Shiro Filter.  Each map entry should conform to the format defined by the
     * {@link FilterChainManager#createChain(String, String)} JavaDoc, where the map key is the chain name (e.g. URL
     * path expression) and the map value is the comma-delimited string chain definition.
     *
     * @param filterChainDefinitionMap the chainName-to-chainDefinition map of chain definitions to use for creating
     *                                 filter chains intercepted by the Shiro Filter.
     */
    public void setFilterChainDefinitionMap(Map<String, String> filterChainDefinitionMap) {
        this.filterChainDefinitionMap = filterChainDefinitionMap;
    }

    /**
     * A convenience method that sets the {@link #setFilterChainDefinitionMap(Map) filterChainDefinitionMap}
     * property by accepting a {@link java.util.Properties Properties}-compatible string (multi-line key/value pairs).
     * Each key/value pair must conform to the format defined by the
     * {@link FilterChainManager#createChain(String,String)} JavaDoc - each property key is an ant URL
     * path expression and the value is the comma-delimited chain definition.
     *
     * @param definitions a {@link java.util.Properties Properties}-compatible string (multi-line key/value pairs)
     *                    where each key/value pair represents a single urlPathExpression-commaDelimitedChainDefinition.
     */
    public void setFilterChainDefinitions(String definitions) {
        Ini ini = new Ini();
        ini.load(definitions);
        //did they explicitly state a 'urls' section?  Not necessary, but just in case:
        Ini.Section section = ini.getSection(IniFilterChainResolverFactory.URLS);
        if (CollectionUtils.isEmpty(section)) {
            //no urls section.  Since this _is_ a urls chain definition property, just assume the
            //default section contains only the definitions:
            section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        }
        setFilterChainDefinitionMap(section);
    }

    /**
     * Lazily creates and returns a {@link AbstractShiroFilter} concrete instance via the
     * {@link #createInstance} method.
     *
     * @return the application's Shiro Filter instance used to filter incoming web requests.
     * @throws Exception if there is a problem creating the {@code Filter} instance.
     */
    public Object getObject() throws Exception {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }

    /**
     * Returns <code>{@link org.apache.shiro.web.servlet.AbstractShiroFilter}.class</code>
     *
     * @return <code>{@link org.apache.shiro.web.servlet.AbstractShiroFilter}.class</code>
     */
    public Class getObjectType() {
        return SpringTokenShiroFilter.class;
    }

    /**
     * Returns {@code true} always.  There is almost always only ever 1 Shiro {@code Filter} per web application.
     *
     * @return {@code true} always.  There is almost always only ever 1 Shiro {@code Filter} per web application.
     */
    public boolean isSingleton() {
        return true;
    }

    protected FilterChainManager createFilterChainManager() {

        DefaultFilterChainManager manager = new DefaultFilterChainManager();

        //Apply the acquired and/or configured filters:
        Map<String, Filter> filters = getFilters();
        if (!CollectionUtils.isEmpty(filters)) {
            for (Map.Entry<String, Filter> entry : filters.entrySet()) {
                String name = entry.getKey();
                Filter filter = entry.getValue();
                if (filter instanceof Nameable) {
                    ((Nameable) filter).setName(name);
                }
                //'init' argument is false, since Spring-configured filters should be initialized
                //in Spring (i.e. 'init-method=blah') or implement InitializingBean:
                manager.addFilter(name, filter, false);
            }
        }

        //build up the chains:
        Map<String, String> chains = getFilterChainDefinitionMap();
        if (!CollectionUtils.isEmpty(chains)) {
            for (Map.Entry<String, String> entry : chains.entrySet()) {
                String url = entry.getKey();
                String chainDefinition = entry.getValue();
                manager.createChain(url, chainDefinition);
            }
        }

        return manager;
    }

    /**
     * This implementation:
     * <ol>
     * <li>Ensures the required {@link #setSecurityManager(org.apache.shiro.mgt.SecurityManager) securityManager}
     * property has been set</li>
     * <li>{@link #createFilterChainManager() Creates} a {@link FilterChainManager} instance that reflects the
     * configured {@link #setFilters(Map) filters} and
     * {@link #setFilterChainDefinitionMap(Map) filter chain definitions}</li>
     * <li>Wraps the FilterChainManager with a suitable
     * {@link org.apache.shiro.web.filter.mgt.FilterChainResolver FilterChainResolver} since the Shiro Filter
     * implementations do not know of {@code FilterChainManager}s</li>
     * <li>Sets both the {@code SecurityManager} and {@code FilterChainResolver} instances on a new Shiro Filter
     * instance and returns that filter instance.</li>
     * </ol>
     *
     * @return a new Shiro Filter reflecting any configured filters and filter chain definitions.
     * @throws Exception if there is a problem creating the AbstractShiroFilter instance.
     */
    protected AbstractTokenShiroFilter createInstance() throws Exception {

        log.debug("Creating Shiro Filter instance.");

        SecurityManager securityManager = getSecurityManager();
        if (securityManager == null) {
            String msg = "SecurityManager property must be set.";
            throw new BeanInitializationException(msg);
        }

//        if (!(securityManager instanceof WebSecurityManager)) {
//            String msg = "The security manager does not implement the WebSecurityManager interface.";
//            throw new BeanInitializationException(msg);
//        }

        FilterChainManager manager = createFilterChainManager();

        //Expose the constructed FilterChainManager by first wrapping it in a
        // FilterChainResolver implementation. The AbstractShiroFilter implementations
        // do not know about FilterChainManagers - only resolvers:
        PathMatchingFilterChainResolver chainResolver = new PathMatchingFilterChainResolver();
        chainResolver.setFilterChainManager(manager);

        //Now create a concrete ShiroFilter instance and apply the acquired SecurityManager and built
        //FilterChainResolver.  It doesn't matter that the instance is an anonymous inner class
        //here - we're just using it because it is a concrete AbstractShiroFilter instance that accepts
        //injection of the SecurityManager and FilterChainResolver:
        return new SpringTokenShiroFilter(securityManager, chainResolver);
    }
    
    /**
     * Inspects a bean, and if it implements the {@link Filter} interface, automatically adds that filter
     * instance to the internal {@link #setFilters(Map) filters map} that will be referenced
     * later during filter chain construction.
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Filter) {
            log.debug("Found filter chain candidate filter '{}'", beanName);
            Filter filter = (Filter) bean;
            getFilters().put(beanName, filter);
        } else {
            log.trace("Ignoring non-Filter bean '{}'", beanName);
        }
        return bean;
    }

    /**
     * Does nothing - only exists to satisfy the BeanPostProcessor interface and immediately returns the
     * {@code bean} argument.
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /**
     * Ordinarily the {@code AbstractShiroFilter} must be subclassed to additionally perform configuration
     * and initialization behavior.  Because this {@code FactoryBean} implementation manually builds the
     * {@link AbstractShiroFilter}'s
     * {@link AbstractShiroFilter#setSecurityManager(org.apache.shiro.web.mgt.WebSecurityManager) securityManager} and
     * {@link AbstractShiroFilter#setFilterChainResolver(org.apache.shiro.web.filter.mgt.FilterChainResolver) filterChainResolver}
     * properties, the only thing left to do is set those properties explicitly.  We do that in a simple
     * concrete subclass in the constructor.
     */
    private static final class SpringTokenShiroFilter extends AbstractTokenShiroFilter {

        protected SpringTokenShiroFilter(SecurityManager SecurityManager, FilterChainResolver resolver) {
            super();
            if (SecurityManager == null) {
                throw new IllegalArgumentException("WebSecurityManager property cannot be null.");
            }
            setSecurityManager(SecurityManager);
            if (resolver != null) {
                setFilterChainResolver(resolver);
            }
        }
    }
}
