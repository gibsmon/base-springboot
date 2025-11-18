package com.poliymorf.dagaitem.util;

import com.google.gson.Gson;

public class Mapper {

    private final static Gson mapperFactory = new Gson();

    public static <S, C> C jsonToObj(S source, Class<C> clazz) {
        if (source == null) {
            return null;
        }
        return mapperFactory.fromJson(source.toString(), clazz);
    }

    public static <S, C> C objToObj(S source, Class<C> clazz) {
        if (source == null) {
            return null;
        }
        return mapperFactory.fromJson(mapperFactory.toJson(source), clazz);
    }


    public static String objToString(Object source) {
        if (source == null) {
            return null;
        }
        return mapperFactory.toJson(source);
    }


}
