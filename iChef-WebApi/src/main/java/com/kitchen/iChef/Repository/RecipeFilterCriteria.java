package com.kitchen.iChef.Repository;

import com.kitchen.iChef.Controller.Model.Request.FilterRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RecipeFilterCriteria {
    private List<FilterRequest> filters;
}
