package com.example.platzi_pizzeria.persistence.entity;

import com.example.platzi_pizzeria.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@Table(name = "pizza")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 30, unique = true)
    private String name;

    @Column(length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "Decimal(5,2)")
    private Double price;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean vegetarian;

    @Column(columnDefinition = "BOOLEAN")
    private Boolean vegan;

    @Column(nullable = false, columnDefinition = "BOOLEAN")
    private Boolean available;
}
