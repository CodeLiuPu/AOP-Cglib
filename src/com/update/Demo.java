package com.update;

/**
 * author : liupu
 * date   : 2019/5/6
 * desc   :
 */
public class Demo {
    public static void main(String[] args) {
        Hello hello = new Hello();
        IHello proxy = (IHello) new CGLibProxyInterceptor().getProxy(hello);
        proxy.say();
    }

}
