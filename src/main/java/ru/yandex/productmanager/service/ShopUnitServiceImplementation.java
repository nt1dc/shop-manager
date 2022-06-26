package ru.yandex.productmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.productmanager.dto.ShopUnitImport;
import ru.yandex.productmanager.dto.ShopUnitStatisticUnit;
import ru.yandex.productmanager.entity.ShopUnitHistoryRecord;
import ru.yandex.productmanager.entity.ShopUnit;
import ru.yandex.productmanager.entity.ShopUnitType;
import ru.yandex.productmanager.repository.HistoryRepository;
import ru.yandex.productmanager.repository.ShopUnitRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ShopUnitServiceImplementation implements ShopUnitService {
    final ShopUnitRepository shopUnitRepository;
    final
    HistoryRepository historyRepository;


    @Autowired
    public ShopUnitServiceImplementation(ShopUnitRepository shopUnitRepository, HistoryRepository historyRepository) {
        this.shopUnitRepository = shopUnitRepository;
        this.historyRepository = historyRepository;
    }


    @Override
    public void importShopUnit(List<ShopUnitImport> shopUnitList, Date date) {
        shopUnitList.forEach(unit -> {
            ShopUnit shopUnit = shopUnitRepository.findById(unit.getId()).orElse(null);
            if (shopUnit != null) {
                ShopUnitHistoryRecord shopUnitHistoryRecord = new ShopUnitHistoryRecord(shopUnit);
                shopUnit.addToHistory(shopUnitHistoryRecord);
                historyRepository.save(shopUnitHistoryRecord);
            } else shopUnit = new ShopUnit();
            shopUnit.setDate(date);
            shopUnit.setName(unit.getName());
            shopUnit.setType(unit.getType());
            shopUnit.setId(unit.getId());
            shopUnit.setPrice(unit.getPrice());
            if (unit.getParentId() != null) {
                shopUnit.setParent(shopUnitRepository.findById(unit.getParentId()).orElse(null));
            }
            ShopUnitHistoryRecord shopUnitHistoryRecord = new ShopUnitHistoryRecord(shopUnit);
            shopUnit.addToHistory(shopUnitHistoryRecord);
            historyRepository.save(shopUnitHistoryRecord);
            shopUnitRepository.save(shopUnit);
        });
    }

    @Override
    public ShopUnit getNodes(UUID id) {
        return shopUnitRepository.findById(id).orElseThrow();

    }

    @Override
    public void deleteNode(UUID id) {
        shopUnitRepository.deleteById(id);
    }

    @Override
    public List<ShopUnitStatisticUnit> sales(Date date) {
        Date dateBefore = new Date(date.getTime() - 1000 * 60 * 60 * 24);
        return shopUnitRepository.findAllByDateBetweenAndTypeEquals(dateBefore, date, ShopUnitType.OFFER);
    }

    @Override
    public List<ShopUnitHistoryRecord> getStatistic(UUID id, Date dateStart, Date dateEnd) {
        return historyRepository.findAllByDateBetweenAndUuid(dateStart, dateEnd, id);
    }
}
