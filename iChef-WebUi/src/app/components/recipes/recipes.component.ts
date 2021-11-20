import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ingredient } from 'src/app/data-types/ingredient';
import { Recipe } from 'src/app/data-types/recipe';
import { Utensil } from 'src/app/data-types/utensil';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from '../../constants/texts';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent implements OnInit {

  readonly searchBtn = BUTTON_STRINGS.SEARCH;
  readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
  readonly createBtn = BUTTON_STRINGS.CREATE;
  readonly filterBtn = BUTTON_STRINGS.FILTER;

  recipe1: Recipe = new Recipe('Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', [new Ingredient('extra virgin olive oil or unsalted butter', '1', 'tablespoon'), new Ingredient('pancetta or thick cut bacon, diced', '1/2', 'pound' ), new Ingredient('eggs', '3-4', 'whole'), new Ingredient('grated Parmesan or pecorino cheese', '1', 'cup'), new Ingredient('spaghetti (or bucatini or fettuccine)', '1', 'pound' ), new Ingredient('Salt and black pepper', 'to taste', '')], [new Utensil('Utensil 1'), new Utensil('Utensil 2'), new Utensil('Utensil 3'), new Utensil('Utensil 4'), new Utensil('Utensil 5')], '30 min', 'Heat pasta water:\n Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).\n\nSauté pancetta/bacon and garlic:\n While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. \n\nBeat eggs and half of the cheese:\n In a small bowl, beat the eggs and mix in about half of the cheese. \n\nCook pasta:\n Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. \n\nToss pasta with pancetta/bacon:\n When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). \n\nAdd the beaten egg mixture:\n Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
  recipe2: Recipe = new Recipe('Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', [], [], '', '', '');
  recipe3: Recipe = new Recipe('Burger', 'Easy', '/assets/imgs/burger.jpg', [], [], '', '', '');

  constructor(private router: Router) { }

  ngOnInit(): void {
    this.recipe1.id = '1';
    this.recipe1.rating = 4.5;
    this.recipe2.id = '2';
    this.recipe2.rating = 4.0;
    this.recipe3.id = '3';
    this.recipe3.rating = 4.8;
  }

  onCreateEvent(): void {
    this.router.navigate(['details']);
  }
}
