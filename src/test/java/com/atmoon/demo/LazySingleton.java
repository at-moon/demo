package com.atmoon.demo;

/**
 * @author : zy
 */
public class LazySingleton {

    // volatile保证变量可见性，禁止指令重排序
    private static volatile LazySingleton singleton = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (singleton == null) {
            synchronized (LazySingleton.class) {
                if (singleton == null) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }

}
