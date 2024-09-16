package com.example.platzi_pizzeria.web.controller;

import com.example.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.example.platzi_pizzeria.service.PizzaService;
import com.example.platzi_pizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaController {

    @Autowired
    PizzaService pizzaService;

    @GetMapping
    public ResponseEntity<Page<PizzaEntity>> getAll(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(pizzaService.getAll(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity <PizzaEntity> getPizaById(@PathVariable int idPizza) {
        return ResponseEntity.ok(pizzaService.getPizzaById(idPizza));
    }

    @GetMapping("/available")
    public ResponseEntity <Page<PizzaEntity>> getAvailable(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "8") int elements,
                                                           @RequestParam(defaultValue = "price") String sortBy) {
        return ResponseEntity.ok(pizzaService.getAvailable(page, elements, sortBy));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity <PizzaEntity> getPizzaByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getPizzaByName(name));
    }

    @GetMapping("/with/{ingredient}")
    public ResponseEntity <List<PizzaEntity>> getPizzaByIngredient(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getWithDescription(ingredient));
    }

    @GetMapping("/withouth/{ingredient}")
    public ResponseEntity <List<PizzaEntity>> getPizzaWithouthIngredient(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getWithouthDescription(ingredient));
    }

    @GetMapping("/cheapest/{price}")
    public ResponseEntity <List<PizzaEntity>> getCheapestPizzas(@PathVariable double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> add(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null || pizzaService.exists(pizza.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizza));
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/price")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDTO dto) {
        if (pizzaService.exists(dto.getPizzaId())) {
            pizzaService.updatePrice(dto);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable int idPizza) {
        if (pizzaService.exists(idPizza)) {
            pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
