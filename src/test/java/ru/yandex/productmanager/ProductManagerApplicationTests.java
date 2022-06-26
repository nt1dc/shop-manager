package ru.yandex.productmanager;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


class ProductManagerApplicationTests {
//
//    @Test
//    void contextLoads() {
//
//    }
    @SneakyThrows
    @Test
    public void zxc(){
        Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").parse("2022-02-03T15:00:00.000Z");
    }

}
