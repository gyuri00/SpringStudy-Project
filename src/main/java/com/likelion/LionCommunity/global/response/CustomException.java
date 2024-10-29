package com.likelion.LionCommunity.global.response;

import com.likelion.LionCommunity.global.response.resEnum.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode code;
}
