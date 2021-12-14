package com.kitchen.iChef.Service;

import com.kitchen.iChef.Domain.Utensil;
import com.kitchen.iChef.Repository.UtensilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtensilService {
    private final UtensilRepository utensilRepository;

    @Autowired
    public UtensilService(UtensilRepository utensilRepository) {
        this.utensilRepository = utensilRepository;
    }

    public List<Utensil> getAllUtensils() {
        return utensilRepository.findAll();
    }

}
