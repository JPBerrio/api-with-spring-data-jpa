package com.example.platzi_pizzeria.service;

import com.example.platzi_pizzeria.persistence.entity.PizzaEntity;
import com.example.platzi_pizzeria.persistence.repository.PizzaPagSortRepository;
import com.example.platzi_pizzeria.persistence.repository.PizzaRepository;
import com.example.platzi_pizzeria.service.dto.UpdatePizzaPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PizzaService {

    @Autowired
    PizzaRepository pizzaRepository;

    @Autowired
    PizzaPagSortRepository pizzaPagSortRepository;

    public Page<PizzaEntity> getAll(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page,elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    public Page<PizzaEntity> getAvailable(int page, int elements, String sortBy) {
        System.out.println(pizzaRepository.countByVeganTrue());
        Pageable pageRequest = PageRequest.of(page,elements, Sort.by(sortBy).descending());
        return pizzaPagSortRepository.findByAvailableTrue(pageRequest);
    }

    public PizzaEntity getPizzaByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name)
                .orElseThrow(()-> new RuntimeException("La pizza buscada no existe"));
    }

    public List<PizzaEntity> getWithDescription(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getWithouthDescription(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    public List<PizzaEntity> getCheapest(double price) {
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price);
    }

    public PizzaEntity getPizzaById(int idPizza) {
        return pizzaRepository.findById(idPizza).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean exists(int idPizza) {
        return pizzaRepository.existsById(idPizza);
    }

    public void delete(int idPizza) {
        pizzaRepository.deleteById(idPizza);
    }

    @Transactional
    public void updatePrice(UpdatePizzaPriceDTO dto) {
        pizzaRepository.updatePrice(dto.getNewPrice(), dto.getPizzaId());
    }
}
