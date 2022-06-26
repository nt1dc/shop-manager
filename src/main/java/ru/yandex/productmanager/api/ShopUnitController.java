package ru.yandex.productmanager.api;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import ru.yandex.productmanager.dto.ShopUnitImportRequest;
import ru.yandex.productmanager.dto.ShopUnitStatisticUnit;
import ru.yandex.productmanager.entity.ShopUnitHistoryRecord;
import ru.yandex.productmanager.entity.ShopUnit;
import ru.yandex.productmanager.service.ShopUnitService;
import ru.yandex.productmanager.utils.DateFormatUtil;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController("")
@Data
public class ShopUnitController {

    private final ShopUnitService service;

    public ShopUnitController(ShopUnitService service) {
        this.service = service;
    }

    @PostMapping("/imports")
    public ResponseEntity postImports(@Valid @RequestBody ShopUnitImportRequest importRequest) {

        service.importShopUnit(importRequest.getItems(), importRequest.getUpdateDate());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") @NotNull UUID id) {

        service.deleteNode(id);
        return ResponseEntity.ok().build();


    }

    @NonNull
    @GetMapping("/nodes/{id}")
    private ResponseEntity<ShopUnit> getNodes(@PathVariable("id") UUID id) {
        ShopUnit shopUnit = service.getNodes(id);
        return ResponseEntity.ok().body(shopUnit);
    }

    @NonNull
    @GetMapping("/sales")
    private ResponseEntity<List<ShopUnitStatisticUnit>> getSales(@RequestParam String date) throws IllegalArgumentException {
        Date formatDate = DateFormatUtil.format(date);
        return ResponseEntity.ok().body(service.sales(formatDate));
    }


    @NonNull
    @GetMapping("/node/{id}/statistic")
    private ResponseEntity<List<ShopUnitHistoryRecord>> statistic(@PathVariable("id") UUID id, @RequestParam String dateStart, @RequestParam String dateEnd) {
        Date formatDateStart = DateFormatUtil.format(dateStart);
        Date formatDateEnd = DateFormatUtil.format(dateEnd);
        List<ShopUnitHistoryRecord> statisticUnits = service.getStatistic(id, formatDateStart, formatDateEnd);
        return ResponseEntity.ok().body(statisticUnits);
    }

}
