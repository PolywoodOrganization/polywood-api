package com.polywood.api.utils;

import java.util.HashMap;

/**
 * Build Json response for controllers
 *
 * Example :
 *
 * {
 *     "code":200,
 *     "message":"OK",
 *     "content" [
 *     ]
 * }
 */
public class GenericResponse
{
    public String code = "200";
    public String message = "OK";

    private HashMap<String,Object> content;

    public GenericResponse()
    {
        content = new HashMap<>();
    }

    public void addToContent(String key, Object value)
    {
        content.put(key,value);
    }
}