import { Component } from '@angular/core';
import { BUTTON_STRINGS, TITLES } from 'src/app/constants/texts';
import { Recipe } from 'src/app/data-types/recipe';
import { Review } from 'src/app/data-types/review';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.scss']
})
export class RecipeDetailsComponent{

  readonly reviews = TITLES.REVIEWS;
  readonly ingredients = TITLES.INGREDIENTS;
  readonly utensils = TITLES.UTENSILS;
  readonly instructions = TITLES.INSTRUCTIONS;
  readonly notes = TITLES.NOTES;
  readonly addReviewBtn = BUTTON_STRINGS.REVIEW;

  recipe: Recipe = new Recipe(1,'Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', 4.5,['1 tablespoon extra virgin olive oil or unsalted butter','1/2 pound pancetta or thick cut bacon, diced','1-2 garlic cloves, minced, about 1 teaspoon (optional)','3-4 whole eggs','1 cup grated Parmesan or pecorino cheese','1 pound spaghetti (or bucatini or fettuccine)','Salt and black pepper to taste'],['Utensil 1','Utensil 2','Utensil 3','Utensil 4','Utensil 5'],'30 min','Heat pasta water:\n Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).\n\nSauté pancetta/bacon and garlic:\n While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. \n\nBeat eggs and half of the cheese:\n In a small bowl, beat the eggs and mix in about half of the cheese. \n\nCook pasta:\n Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. \n\nToss pasta with pancetta/bacon:\n When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). \n\nAdd the beaten egg mixture:\n Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.','4 to 6 servings');

  review1: Review = new Review('Lorem ipsum','Lorem ipsum dolor sit amet, consectetur adipiscing elit. In consequat egestas mi tempor condimentum. Pellentesque eu arcu eu libero maximus luctus id ut lorem. Proin et justo sed turpis ', 4.7);
  review2: Review = new Review('Lorem ipsum 2','Lorem ipsum dolor sit amet, consectetur adipiscing elit. ', 4);
}
