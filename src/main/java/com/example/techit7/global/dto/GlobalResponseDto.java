package com.example.techit7.global.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class GlobalResponseDto<T> {

    private String resultCode;
    private String msg;
    private T data;

    public static<T> GlobalResponseDto<T> of(String resultCode, String msg){
        GlobalResponseDto<T> globalResponseDto = new GlobalResponseDto<T>();

        globalResponseDto.setResultCode(resultCode);
        globalResponseDto.setMsg(msg);

        return globalResponseDto;
    }

    public static<T> GlobalResponseDto<T> of(String resultCode, String msg, T data){
        GlobalResponseDto<T> globalResponseDto = new GlobalResponseDto<T>();

        globalResponseDto.setResultCode(resultCode);
        globalResponseDto.setMsg(msg);
        globalResponseDto.setData(data);

        return globalResponseDto;
    }
}
