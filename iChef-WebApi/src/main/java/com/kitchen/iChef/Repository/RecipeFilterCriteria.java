package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;

import java.util.List;

public class RecipeFilterCriteria {
   private List<FilterRequest> filters;

    public List<FilterRequest> getFilters() {
        return filters;
    }

    public void setFilters(List<FilterRequest> filters) {
        this.filters = filters;
    }
}
