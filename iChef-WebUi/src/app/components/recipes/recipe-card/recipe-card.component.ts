import { Component, Input, OnInit } from '@angular/core';
import { Recipe } from 'src/app/data-types/recipe';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss']
})
export class RecipeCardComponent{

  @Input() recipe!: Recipe;

  constructor() { }

  ngOnInit(): void {
  }

}
