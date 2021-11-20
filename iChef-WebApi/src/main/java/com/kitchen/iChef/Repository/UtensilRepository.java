package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Domain.Utensil;
import com.kitchen.iChef.Repository.Interfaces.ICrudRepository;
import com.kitchen.iChef.Repository.Interfaces.IUtensilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UtensilRepository implements ICrudRepository<Utensil, String> {
    private final IUtensilRepository iUtensilRepository;

    @Autowired
    public UtensilRepository(IUtensilRepository iUtensilRepository) {
        this.iUtensilRepository = iUtensilRepository;
    }

    @Override
    public Utensil findOne(String s) {
        if (iUtensilRepository.findById(s).isPresent()) {
            return iUtensilRepository.findById(s).get();
        }
        return null;
    }

    @Override
    public List<Utensil> findAll() {
        List<Utensil> allUtensils = new ArrayList<>();
        Iterable<Utensil> utensils = iUtensilRepository.findAll();
        for (Utensil utensil : utensils) {
            allUtensils.add(utensil);
        }
        return allUtensils;
    }

    @Override
    public Utensil save(Utensil entity) {
        return iUtensilRepository.save(entity);
    }

    @Override
    public Utensil delete(String s) {
        Utensil utensil = findOne(s);
        iUtensilRepository.deleteById(s);
        return utensil;
    }

    @Override
    public Utensil update(Utensil entity) {
        iUtensilRepository.deleteById(entity.getUtensilId());
        iUtensilRepository.save(entity);
        return entity;
    }

    public Optional<Utensil> findByName(String name) {
        return iUtensilRepository.findByName(name);
    }

}
