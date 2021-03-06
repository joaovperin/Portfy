/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.jpe.portfy.ws.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * A helper class to work with JSONs
 *
 * @author joaovperin
 */
@Service
public class Jsons {

    @Autowired
    private Gson gson;

    /**
     * Converts a JSON String into a class instance
     *
     * @param json JSON String
     * @param <B> Class type
     * @param clazz Class object to cast
     * @return B Class instance
     */
    public <B> B castTo(String json, Class<B> clazz) {
        return gson.fromJson(json, clazz);
    }

    /**
     * Represents an Object instance as a JSON String
     *
     * @param obj
     * @return String
     */
    public String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * Represents an Object instance as a formatted JSON String
     *
     * @param obj
     * @return String
     */
    public String toFormattedJson(Object obj) {
        return gson.newBuilder().
                setPrettyPrinting().create().
                toJson(obj);
    }

    /**
     * Gson Factory
     */
    public static final class GsonFactory {

        /**
         * Returns a GSON Instance
         *
         * @return Gson
         */
        @Bean
        public static Gson gson() {
            return new GsonBuilder().
                    setExclusionStrategies(new PortfyGsonExclusionStrategy()).
                    create();
        }

    }

    /**
     * Exclusion strategy
     */
    public static final class PortfyGsonExclusionStrategy implements ExclusionStrategy {

        /**
         * Excludes fields annotated with GsonRepellent annotation
         *
         * @param fieldAttributes
         * @return boolean
         */
        @Override
        public boolean shouldSkipField(FieldAttributes fieldAttributes) {
            return fieldAttributes.getAnnotation(JsonIgnore.class) != null;
        }

        /**
         * Excludes classes annotated with GsonRepellent annotation
         *
         * @param type
         * @return boolean
         */
        @Override
        public boolean shouldSkipClass(Class<?> type) {
            return type.isAnnotationPresent(JsonIgnore.class);
        }

    }

}
