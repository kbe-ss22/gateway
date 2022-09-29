package com.kbe.gateway.controller;

public class ProductPostEntity  {
    private String name;
    private int[] hardwareIDs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int[] getHardwareIDs() {
        return hardwareIDs;
    }

    public void setHardwareIDs(int[] hardwareIDs) {
        this.hardwareIDs = hardwareIDs;
    }
}
