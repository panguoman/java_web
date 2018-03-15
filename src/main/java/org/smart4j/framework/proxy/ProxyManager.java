package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 代理管理器
 */
public class ProxyManager {

    @SuppressWarnings("unchecked")
    public static <T> T createProxy (final Class<?> targerClass, final List<Proxy> proxyList) {
        return (T) Enhancer.create(targerClass, new MethodInterceptor() {
            public Object intercept(Object targetObjct, Method targetMethod, Object[] meethodParams, MethodProxy methodProxy) throws Throwable {
                return new ProxyChain(targerClass, targetObjct, targetMethod,
                        methodProxy, meethodParams, proxyList).doProxyChain();
            }
        });
    }
}
