package com.tuacy.spi;

import com.tuacy.spi.service.IHelloService;
import sun.misc.Service;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @version 1.0
 * @author: tuacy.
 * @date: 2021/5/12 20:48.
 */
public class Application {

    public static void main(String[] args) {
        // 方式 一
        Iterator<IHelloService> providers = Service.providers(IHelloService.class);
        while (providers.hasNext()) {
            IHelloService ser = providers.next();
            ser.say();
        }

        System.out.println("-----------------分割线---------------");
        // 方式 二
        ServiceLoader<IHelloService> load = ServiceLoader.load(IHelloService.class);
        for (IHelloService ser : load) {
            ser.say();
        }
    }

}
