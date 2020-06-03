package com.boot.mytt.core.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

/**
 * @author kxd
 * @date 2019/10/25 15:55
 * description:
 */

@Intercepts({
        @Signature(
                method = "query",
                type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
        ),
        @Signature(method = "query",
                type = Executor.class,
                args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
        )
})
@Slf4j
@Component
public class MybatisLogInterceptor implements Interceptor {

    /**
     * 是否显示语句的执行时间
     */
    public static final String PROPERTIES_KEY_ENABLE_EXECUTOR_TIME = "enableExecutorTIme";
    public static final String NO = "NO";
    public static final String YES = "YES";
    private String enableExecutorTime = "NO";


    /**
     * 执行逻辑
     *
     * @param invocation
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        if (enableExecutorTime.equals(YES)) {
            MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
            //这里是为了找到上层是哪个方法触发了这个拦截器

            Object parameter = null;
            if (invocation.getArgs().length > 1) {
                parameter = invocation.getArgs()[1];
            }
            String sqlId = mappedStatement.getId();
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            Configuration configuration = mappedStatement.getConfiguration();
            long sqlStartTime = System.currentTimeMillis();
            Object next = invocation.proceed();
            long sqlEndTime = System.currentTimeMillis();
            String sql = getSql(configuration, boundSql, sqlId);
            String sqlTimeLog = sqlId.concat(">>runs :").concat(String.valueOf(sqlEndTime - sqlStartTime)).concat("ms");
            log.info(">>>>>>>>>>>>>runs method:{}", sqlTimeLog);
            log.info(">>>>>>>>>>>>>content:{}", sql);
            return next;
        }
        return invocation.proceed();
    }

    private String getSql(Configuration configuration, BoundSql boundSql, String sqlId) {
        return sqlId + ">>execute sql:" + assembleSql(configuration, boundSql);
    }

    /**
     * 组装sql信息
     *
     * @param configuration
     * @param boundSql
     * @return
     */
    private String assembleSql(Configuration configuration, BoundSql boundSql) {
        Object sqlParameter = boundSql.getParameterObject();
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        String sql = boundSql.getSql().replaceAll("[\\s+]", "").replaceAll("from", "\n\tFROM\n\t").replaceAll("select", "\n\tSELECT\t\n");
        if (parameterMappings.size() > 0 && sqlParameter != null) {
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(sqlParameter.getClass())) {
                sql = sql.replaceFirst("\\?", getParameterValue(sqlParameter));
            } else {
                MetaObject metaObject = configuration.newMetaObject(sqlParameter);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        Object obj = metaObject.getValue(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        sql = sql.replaceFirst("\\?", getParameterValue(obj));
                    }
                }
            }

        }
        return sql;
    }

    /**
     * 获取参数对应string值
     *
     * @param obj
     * @return
     */
    private String getParameterValue(Object obj) {
        String value = "";
        if (obj instanceof String) {
            value = "'".concat(obj.toString()).concat("'");
        } else if (obj instanceof Date) {
            DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'".concat(dateFormat.format(new Date())) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }
        }
        return value != null ? makeStringAllRegExp(value) : value;
    }

    /**
     * 转义正则特殊字符串
     *
     * @param str
     * @return
     */
    private String makeStringAllRegExp(String str) {
        if (str != null && !str.equals("")) {
            return str.replace("\\", "\\\\").replace("*", "\\*")
                    .replace("+", "\\+").replace("|", "\\|")
                    .replace("{", "\\{").replace("}", "\\}")
                    .replace("(", "\\(").replace(")", "\\)")
                    .replace("^", "\\^").replace("$", "\\$")
                    .replace("[", "\\[").replace("]", "\\]")
                    .replace("?", "\\?").replace(",", "\\,")
                    .replace(".", "\\.").replace("&", "\\&");
        }
        return str;
    }

    /**
     * 返回代理对象
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * 设置属性信息
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {
        if (properties != null) {
            String executorTimeValue = properties.getProperty(PROPERTIES_KEY_ENABLE_EXECUTOR_TIME);
            if (executorTimeValue != null) {
                enableExecutorTime = executorTimeValue;
            }
        }
    }
}