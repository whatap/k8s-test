package io.whatap.oom.controller;

import lombok.Data;

@Data
public class ApiResponse<T>{
    T data;

    public static <T>ApiResponse<T> create (T data){
        ApiResponse<T> response = new ApiResponse<>();
        response.data = data;
        return response;
    }
}
