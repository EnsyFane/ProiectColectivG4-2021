import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/data-types/recipe';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  recipe1: Recipe = new Recipe("Pasta Carbonara","Easy", "/assets/imgs/pasta.jpg",4.5);
  recipe2: Recipe = new Recipe("Pizza Quattro Fromaggi","Easy", "/assets/imgs/pizza.jpg",4.0);
  recipe3: Recipe = new Recipe("Burger","Easy", "/assets/imgs/burger.jpg",4.8);

  constructor() { }

  ngOnInit(): void {
    
  }

}
