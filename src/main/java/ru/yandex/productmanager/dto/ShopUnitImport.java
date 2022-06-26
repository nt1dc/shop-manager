package ru.yandex.productmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import ru.yandex.productmanager.entity.ShopUnitType;

import javax.validation.constraints.Min;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ShopUnitImport {
    private UUID id;
    private String name;
    @Nullable
    private UUID parentId;
    private ShopUnitType type;
    @Min(1)
    private Integer price;
}
