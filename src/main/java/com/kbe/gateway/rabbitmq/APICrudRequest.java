package com.kbe.gateway.rabbitmq;

import org.springframework.context.annotation.Bean;


public class APICrudRequest {
    private int id;
    private String name;
    private int[] hardwareIds = new int[10];

    public APICrudRequest() {}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHardwareIds(int[] hardwareIds) {
        this.hardwareIds = hardwareIds;
    }

    public APICrudRequest(int it, String name, int[] hardwareIds) {
        this.id = id;
        this.name = name;
        this.hardwareIds = hardwareIds;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int[] getHardwareIds() {
        return hardwareIds;
    }
}


