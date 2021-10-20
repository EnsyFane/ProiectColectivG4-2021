import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  d1:string = "Easy";
  t1:string = "Pasta Carbonara";
  u1:string = "../../../../../assets/imgs/pasta.jpg";
  r1:number = 4.5;
  d2:string = "Easy";
  t2:string = "Pizza Quattro Fromaggi";
  u2:string = "../../../../../assets/imgs/pizza.jpg";
  r2:number = 4.0;
  d3:string = "Easy";
  t3:string = "Burger";
  u3:string = "../../../../../assets/imgs/burger.jpg";
  r3:number = 4.8;

  constructor() { }

  ngOnInit(): void {
    
  }

}
