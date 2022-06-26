package ru.yandex.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.productmanager.dto.ShopUnitStatisticUnit;
import ru.yandex.productmanager.entity.ShopUnit;
import ru.yandex.productmanager.entity.ShopUnitType;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ShopUnitRepository extends JpaRepository<ShopUnit, UUID> {

    List<ShopUnitStatisticUnit> findAllByDateBetweenAndTypeEquals(Date date, Date date2, ShopUnitType type);

}
