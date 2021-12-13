package com.kitchen.iChef.Controller.Model.Request;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class FilterRequest {
    @NotBlank(message = "The field cannot be null!")
    private String field;
    @NotBlank(message = "The operation cannot be null!")
    private String operation;
    @NotBlank(message = "The text cannot be null!")
    private String text;

}
