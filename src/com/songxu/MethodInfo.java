package com.songxu;

import org.w3c.dom.html.HTMLImageElement;

public class MethodInfo {
    String className;
    String method;
    String arguments;
    String time;
    String count;
    String selfTime;

    MethodInfo(String className,String method,String arguments,String time,String count,String selfTime){
        this.className = className;
        this.method = method;
        this.arguments = arguments;
        this.time = time;
        this.count = count;
        this.selfTime = selfTime;
    }

    public String addi(String input1,String input2){
        int result = Integer.parseInt(input1) + Integer.parseInt(input2);
        return String.valueOf(result);
    }

    MethodInfo(){}
}
