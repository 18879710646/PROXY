package com.org.Test;

import com.org.A.Star;
import com.org.A.StarAdvice;
import com.org.A.SuperStar;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author HP
 * @Date 2021/11/7 14:11
 * @Version 1.0
 * 其它事与我无关，多看一眼都是愚蠢的。
 * 享有特权而没有力量的人是废物，
 * 受过教育而无影响力的人是一堆一文不值的垃圾。
 */

public class Test {
    final Star star= new SuperStar();
    final StarAdvice starAdvice=new StarAdvice();
    @org.junit.Test
    public void JDBCProxy(){
       Star starProxy=(Star) Proxy.newProxyInstance(
               // 代理类的类加载器
               star.getClass().getClassLoader(),
                // 代理类的实现类的接口
                star.getClass().getInterfaces(),
                new InvocationHandler() {
                   // 每次通过动态代理调用方法，都会执行这个方法一次，method表示的是你动态代理调用方法的该方法对象，args表示为该方法的参数
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        // 前置增强（调用你要增强的方法）
                        starAdvice.before();
//                        我们把要增强的方法的对象和参数传递进去，在把数据返回回去
                        Object result = method.invoke(star, args);
                        // 后置增强（调用你要增强的方法）
                        starAdvice.after();
                        return result;
                    }
                }
        );
       // 通过动态代理来调用方法
        starProxy.sing("忘情水");
        starProxy.dance("霹雳舞");
    }
}
