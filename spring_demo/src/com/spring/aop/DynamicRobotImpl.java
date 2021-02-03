package com.spring.aop;
/**
 * 接口实现类
 * DynamicRobotImpl
 */
public class DynamicRobotImpl implements DynamicRobot {

    @Override
    public String getEnergy() {
        return "DynamicRobotImpl getEnergy()...";
    }

    @Override
    public void work(String time) {
        System.out.println("DynamicRobotImpl work" + time);
    }
}
