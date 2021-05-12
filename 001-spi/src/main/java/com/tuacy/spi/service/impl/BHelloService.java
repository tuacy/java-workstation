package com.tuacy.spi.service.impl;

import com.tuacy.spi.service.IHelloService;

/**
 * @version 1.0
 * @author: tuacy.
 * @date: 2021/5/12 20:50.
 */
public class BHelloService implements IHelloService {
    @Override
    public void say() {
        System.out.println("BBBBBBBBBBBBBBB");
    }
}
