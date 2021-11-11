import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Recipe } from 'src/app/data-types/recipe';
import { BUTTON_STRINGS, PLACEHOLDERS_STRINGS } from '../../constants/texts';

@Component({
  selector: 'app-recipes',
  templateUrl: './recipes.component.html',
  styleUrls: ['./recipes.component.scss']
})
export class RecipesComponent {

    readonly searchBtn = BUTTON_STRINGS.SEARCH;
    readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;
    readonly createBtn = BUTTON_STRINGS.CREATE;
    readonly filterBtn = BUTTON_STRINGS.FILTER;

    recipe1: Recipe = new Recipe(1,'Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', 4.5,['1 tablespoon extra virgin olive oil or unsalted butter','1/2 pound pancetta or thick cut bacon, diced','1-2 garlic cloves, minced, about 1 teaspoon (optional)','3-4 whole eggs','1 cup grated Parmesan or pecorino cheese','1 pound spaghetti (or bucatini or fettuccine)','Salt and black pepper to taste'],[''],'30 min','Heat pasta water: Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).Sauté pancetta/bacon and garlic: While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. Beat eggs and half of the cheese: In a small bowl, beat the eggs and mix in about half of the cheese. Cook pasta: Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. Toss pasta with pancetta/bacon: When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). Add the beaten egg mixture: Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.','4 to 6 servings');
    recipe2: Recipe = new Recipe(2,'Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', 4.0,[''],[''],'','','');
    recipe3: Recipe = new Recipe(3,'Burger', 'Easy', '/assets/imgs/burger.jpg', 4.8,[''],[''],'','','');

    constructor(private router: Router) { }

    onCreateEvent() {
      this.router.navigate(['details']);
    }
}
