package com.kitchen.iChef.Controller.Model.Request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FilterRequest {
    private String title="";
    private Float difficulty=0f;
    private OperationType difficultyOperation=OperationType.GreaterThanEqual;
    private Integer preparationTime=1000;
    private Integer portions=0;
    private OperationType portionsOperation=OperationType.GreaterThanEqual;
}
