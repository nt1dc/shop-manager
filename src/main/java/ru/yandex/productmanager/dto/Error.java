package ru.yandex.productmanager.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class Error {
    @NotNull
    private Integer code;
    @NotNull
    private String message;



}
