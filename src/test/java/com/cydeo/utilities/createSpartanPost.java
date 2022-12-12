package com.cydeo.utilities;

import java.util.LinkedHashMap;
import java.util.Map;

public class createSpartanPost {

    public static Map<String,Object> spartanPost(String name, String gender, Long phone){

        Map<String,Object> holder = new LinkedHashMap<>();

        holder.put("name",name);
        holder.put("gender",gender);
        holder.put("phone",phone);

return holder;
    }
}
