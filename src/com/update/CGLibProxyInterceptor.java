package com.update;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * author : liupu
 * date   : 2019/5/6
 * desc   :
 */
public class CGLibProxyInterceptor implements MethodInterceptor {
    /**
     * 被代理对象
     */
    private Object target;

    public Object getProxy(Object target){
        // 代理类class文件存入本地磁盘方便我们反编译查看源码
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "D:\\code");
        this.target=target;
        /**
         * 动态代码生成器
         */
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);

        /**
         * 动态生成字节码并返回代理对象
         */
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        long star = System.currentTimeMillis();
        Object result = proxy.invokeSuper(obj,args);
        long end = System.currentTimeMillis();
        System.out.println(String.format("method cost %s", (end - star)));
        return result;
    }
}
