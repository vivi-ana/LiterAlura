package com.aluraChallenge.literAlura.service;

/**
 * Interface for converting data.
 */
public interface IDataConverter {
    /**
     * Converts JSON data to the specified class.
     *
     * @param json The JSON data to convert.
     * @param toClass The class to convert the JSON data to.
     * @return The converted object.
     */
    <T> T getData(String json, Class<T> toClass);
}