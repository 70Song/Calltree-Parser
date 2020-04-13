package com.songxu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MethodList{
    HashMap<String,Integer> index = new HashMap<>();
    List<MethodInfo> list = new ArrayList<>();
    public void add(MethodInfo info){
        String method_info = info.className +"."+ info.method +" "+ info.arguments;
        if(index.containsKey(method_info)){
            int i = index.get(method_info);
            MethodInfo temp = list.get(i);
            temp.time = temp.addi(temp.time,info.time);
            temp.count = temp.addi(temp.count,info.count);
            temp.selfTime = temp.addi(temp.selfTime,info.selfTime);
        }else {
            int i = list.size();
            index.put(method_info,i);
            list.add(info);
        }
    }
}
