package com.example.techit7.global.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GlobalResponse<T> {

    private String resultCode;
    private String msg;
    private T data;

    public static<T> GlobalResponse<T> of(String resultCode, String msg){
        GlobalResponse<T> globalResponse = new GlobalResponse<T>();

        globalResponse.setResultCode(resultCode);
        globalResponse.setMsg(msg);

        return globalResponse;
    }

    public static<T> GlobalResponse<T> of(String resultCode, String msg, T data){
        GlobalResponse<T> globalResponse = new GlobalResponse<T>();

        globalResponse.setResultCode(resultCode);
        globalResponse.setMsg(msg);
        globalResponse.setData(data);

        return globalResponse;
    }
}
