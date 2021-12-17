package com.kitchen.iChef.Mapper;

import com.kitchen.iChef.Controller.Model.Response.UtensilResponse;
import com.kitchen.iChef.Domain.Utensil;

public class UtensilMapper {
    public UtensilResponse mapToResponse(Utensil utensil) {
        UtensilResponse utensilResponse = new UtensilResponse();
        utensilResponse.setUtensilName(utensil.getName());
        return utensilResponse;
    }
}
