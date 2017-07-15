package com.example.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by summit on 9/7/17.
 */

@ConfigurationProperties(prefix = "seller.service.db")
public class DataSourceProperties {
    private String url;

    private String username;

    private String password;

    private String dialect;

    private boolean showSql;

    private String driver;

    private String enableLazyLoadNoTrans;

    private String factoryClass;

    private String useSecondLevelCache;

    private String providerClass;

    private String useQueryCache;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialect() {
        return dialect;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public boolean isShowSql() {
        return showSql;
    }

    public void setShowSql(boolean showSql) {
        this.showSql = showSql;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getEnableLazyLoadNoTrans() {
        return enableLazyLoadNoTrans;
    }

    public void setEnableLazyLoadNoTrans(String enableLazyLoadNoTrans) {
        this.enableLazyLoadNoTrans = enableLazyLoadNoTrans;
    }

    public String getFactoryClass() {
        return factoryClass;
    }

    public void setFactoryClass(String factoryClass) {
        this.factoryClass = factoryClass;
    }

    public String getUseSecondLevelCache() {
        return useSecondLevelCache;
    }

    public void setUseSecondLevelCache(String useSecondLevelCache) {
        this.useSecondLevelCache = useSecondLevelCache;
    }

    public String getProviderClass() {
        return providerClass;
    }

    public void setProviderClass(String providerClass) {
        this.providerClass = providerClass;
    }

    public String getUseQueryCache() {
        return useQueryCache;
    }

    public void setUseQueryCache(String useQueryCache) {
        this.useQueryCache = useQueryCache;
    }
}
