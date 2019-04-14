package com.lyf.springmq.util;

public class TestDTO implements java.io.Serializable {
    private String name;

    public TestDTO() {
    }

    public TestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
