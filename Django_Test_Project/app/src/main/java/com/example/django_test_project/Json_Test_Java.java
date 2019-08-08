package com.example.django_test_project;

import com.google.gson.annotations.SerializedName;

public class Json_Test_Java {

    @SerializedName("test")
    public String test;


    public Json_Test_Java(String test) {
        this.test = test;


    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }


}
