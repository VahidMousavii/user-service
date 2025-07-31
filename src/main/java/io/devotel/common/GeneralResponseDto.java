package io.devotel.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GeneralResponseDto<T> {
    private int code ;
    private String message;
    private T data;


}
