package ru.yandex.productmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class ShopUnitHistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    Long id;
    @Column(updatable = false)

    private ShopUnitType type;

    @NotNull
    private String name;

    private UUID uuid;

    @Nullable
    private Integer price;

    private UUID parentUUID;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;


    public ShopUnitHistoryRecord(@NonNull ShopUnit shopUnit) {
        this.parentUUID = shopUnit.getParentId();
        this.type = shopUnit.getType();
        this.name = shopUnit.getName();
        this.uuid = shopUnit.getId();
        this.price = shopUnit.getPrice();
        this.date = shopUnit.getDate();
    }

    public ShopUnitHistoryRecord() {

    }
}
