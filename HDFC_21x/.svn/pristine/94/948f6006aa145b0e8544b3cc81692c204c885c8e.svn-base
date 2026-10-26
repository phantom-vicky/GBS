package com.isl.gbm.common.util;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.SerializationFeature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

;

/**
 * Modified by Sivasankara Prasad(102616) on 27/10/2025.
 */


public class JSONUtil {
    private static Logger log = LoggerFactory.getLogger(JSONUtil.class);
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();

        // ✅ Set the read constraint manually (100 MB max string length)
        com.fasterxml.jackson.core.StreamReadConstraints constraints =
                com.fasterxml.jackson.core.StreamReadConstraints.builder()
                        .maxStringLength(100 * 1024 * 1024) // 100 MB
                        .build();

        // Apply constraints to the ObjectMapper's factory
        objectMapper.getFactory().setStreamReadConstraints(constraints);
    }


    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst)
    {
        instrumentation = inst;
    }

    public static long sizeofObject(Object o){
        return instrumentation.getObjectSize(o);
    }

    public static String convertJavaToJson(Object object) {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String jsonResult = "";
        try {
            jsonResult = objectMapper.writeValueAsString(object);
        } catch (JsonParseException jpe) {
            log.error("JsonParseException :: " + jpe);
        } catch (IOException ioe) {
            log.error("IOException :: " + ioe);
        }
        return jsonResult;
    }

    public static Object convertJsonToJava(String jsonString, Class classType) {
        Object object = null;
        try {
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);
            objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
            object = objectMapper.readValue(jsonString, classType);
        } catch (IOException ioe) {
            log.error("IOException :: " + ioe);
        }
        return object;
    }
//    private static String jaxbObjectToJSON(Class cls) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//
//        AnnotationIntrospector introspector
//                = new JaxbAnnotationIntrospector();
//        mapper.setAnnotationIntrospector(introspector);
//
//        String result = mapper.writeValueAsString(cls);
//        System.out.println(result);
//        return result;
//    }

    public static   String convertJavaToJson(SampleCrnDataDTO jsonValue) {
        String jsonStr = null;
        try {
             jsonStr = objectMapper.writeValueAsString(jsonValue);
            System.out.println(jsonStr);
        } catch (IOException ioe) {
            log.error("IOException :: " + ioe);
        }
        return jsonStr;
    }



}
