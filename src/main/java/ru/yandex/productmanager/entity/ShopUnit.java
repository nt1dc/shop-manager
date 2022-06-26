package ru.yandex.productmanager.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners({AuditingEntityListener.class})

public class ShopUnit {
    @Column(updatable = false)
    private ShopUnitType type;
    @NotNull
    private String name;
    @Id
    private UUID id;
    @Nullable
    private Integer price;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private Date date;

    @Column(name = "parent_uuid")
    private UUID parentId;

    @Nullable
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ShopUnit parent;

    @OneToMany(orphanRemoval = true)
    @JsonIgnore
    List<ShopUnitHistoryRecord> shopUnitHistoryRecords = new ArrayList<>();

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    List<ShopUnit> children = new ArrayList<>();

    public void addToHistory(ShopUnitHistoryRecord shopUnitHistoryRecord) {
        shopUnitHistoryRecords.add(shopUnitHistoryRecord);
    }

    public void setParent(@Nullable ShopUnit parent) {
        parentId = parent != null ? parent.getId() : null;
        this.parent = parent;
    }

    @PostUpdate
    @PostLoad
    private void updateDepends() {
        updateDate();
    }

    private Integer getCount() {
        Integer count = 0;
        for (ShopUnit s : children
        ) {
            if (s.getType() == ShopUnitType.CATEGORY) {
                count += s.getCount();
            } else count++;

        }
        return count;
    }

    private Integer getSum() {
        if (type == ShopUnitType.CATEGORY) {
            return children.stream().mapToInt(ShopUnit::getSum).sum();
        }
        return price;
    }

    public Integer getPrice() {
        if (type == ShopUnitType.CATEGORY) {
            Integer count = getCount();
            Integer sum = getSum();
            if (count == 0) return 0;
            else return sum / count;
        }
        return price;
    }

    private void updateDate() {
        if (parent != null) {
            parent.setDate(date);
            parent.updateDate();
        }
    }

    public List<ShopUnit> getChildren() {
        if (type == ShopUnitType.OFFER) return null;
        return children;
    }
}




