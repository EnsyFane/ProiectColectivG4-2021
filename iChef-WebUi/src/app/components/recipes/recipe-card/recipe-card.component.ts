import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-card',
  templateUrl: './recipe-card.component.html',
  styleUrls: ['./recipe-card.component.scss']
})
export class RecipeCardComponent implements OnInit {

  @Input() difficulty? : string;
  @Input() title? : string;
  @Input() url? : string;
  @Input() rating? : number;

  constructor() { }

  ngOnInit(): void {
  }

}
