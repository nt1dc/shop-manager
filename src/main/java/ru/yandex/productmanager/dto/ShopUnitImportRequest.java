package ru.yandex.productmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopUnitImportRequest {
    @Valid
    private List<ShopUnitImport> items;
    @DateTimeFormat()
    private Date updateDate;
}
