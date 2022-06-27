package ru.yandex.productmanager.service;

import ru.yandex.productmanager.dto.ShopUnitImport;
import ru.yandex.productmanager.dto.ShopUnitStatisticUnit;
import ru.yandex.productmanager.entity.ShopUnitHistoryRecord;
import ru.yandex.productmanager.entity.ShopUnit;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ShopUnitService {


    void importShopUnit(List<ShopUnitImport> shopUnitList, Date date);

    ShopUnit getNodes(UUID id);

    void deleteNode(UUID id);

    List<ShopUnitStatisticUnit> sales(Date date);

    List<ShopUnitHistoryRecord> getStatistic(UUID id, Date dateStart, Date dateEnd);
}
    