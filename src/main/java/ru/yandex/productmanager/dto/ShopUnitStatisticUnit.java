package ru.yandex.productmanager.dto;

import ru.yandex.productmanager.entity.ShopUnitType;

import java.util.Date;
import java.util.UUID;

public interface ShopUnitStatisticUnit {
    UUID getId();

    String getName();

    UUID getParentId();

    Date getDate();

    Integer getPrice();

    ShopUnitType getType();
}
