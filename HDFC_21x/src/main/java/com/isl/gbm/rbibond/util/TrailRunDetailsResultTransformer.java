package com.isl.gbm.rbibond.util;

import org.hibernate.transform.ResultTransformer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TrailRunDetailsResultTransformer implements ResultTransformer {

    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (int i = 0; i < tuple.length; i++) {
            result.put(aliases[i], tuple[i]);
        }
        return result;
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }
}