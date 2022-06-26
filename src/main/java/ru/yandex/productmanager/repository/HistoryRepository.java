package ru.yandex.productmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.productmanager.entity.ShopUnitHistoryRecord;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface HistoryRepository extends JpaRepository<ShopUnitHistoryRecord,Long> {
    List<ShopUnitHistoryRecord> findAllByDateBetweenAndUuid(Date dateStart, Date dateEnd, UUID id);
}
