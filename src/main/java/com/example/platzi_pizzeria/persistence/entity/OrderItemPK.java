package com.example.platzi_pizzeria.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class OrderItemPK implements Serializable {
    @Column(name = "id_item")
    private Integer idItem;

    @Column(name = "id_order")
    private Integer idOrder;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        OrderItemPK that = (OrderItemPK) object;
        return Objects.equals(idItem, that.idItem) && Objects.equals(idOrder, that.idOrder);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idItem, idOrder);
    }
}
