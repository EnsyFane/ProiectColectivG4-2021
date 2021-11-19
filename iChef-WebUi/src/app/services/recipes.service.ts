import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {Recipe} from '../data-types/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

    recipe1: Recipe = new Recipe('1', 'Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', 4.5, ['1 tablespoon extra virgin olive oil or unsalted butter', '1/2 pound pancetta or thick cut bacon, diced', '1-2 garlic cloves, minced, about 1 teaspoon (optional)', '3-4 whole eggs', '1 cup grated Parmesan or pecorino cheese', '1 pound spaghetti (or bucatini or fettuccine)', 'Salt and black pepper to taste'], [''], '30 min', 'Heat pasta water: Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).Sauté pancetta/bacon and garlic: While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. Beat eggs and half of the cheese: In a small bowl, beat the eggs and mix in about half of the cheese. Cook pasta: Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. Toss pasta with pancetta/bacon: When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). Add the beaten egg mixture: Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe2: Recipe = new Recipe('2', 'Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', 4.0, [''], [''], '', '', '');
    recipe3: Recipe = new Recipe('3', 'Burger', 'Easy', '/assets/imgs/burger.jpg', 4.8, [''], [''], '', '', '');
    recipe4: Recipe = new Recipe('1', 'Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', 4.5, ['1 tablespoon extra virgin olive oil or unsalted butter', '1/2 pound pancetta or thick cut bacon, diced', '1-2 garlic cloves, minced, about 1 teaspoon (optional)', '3-4 whole eggs', '1 cup grated Parmesan or pecorino cheese', '1 pound spaghetti (or bucatini or fettuccine)', 'Salt and black pepper to taste'], [''], '30 min', 'Heat pasta water: Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).Sauté pancetta/bacon and garlic: While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. Beat eggs and half of the cheese: In a small bowl, beat the eggs and mix in about half of the cheese. Cook pasta: Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. Toss pasta with pancetta/bacon: When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). Add the beaten egg mixture: Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe5: Recipe = new Recipe('2', 'Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', 4.0, [''], [''], '', '', '');
    recipe6: Recipe = new Recipe('3', 'Burger', 'Easy', '/assets/imgs/burger.jpg', 4.8, [''], [''], '', '', '');
    recipe7: Recipe = new Recipe('1', 'Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', 4.5, ['1 tablespoon extra virgin olive oil or unsalted butter', '1/2 pound pancetta or thick cut bacon, diced', '1-2 garlic cloves, minced, about 1 teaspoon (optional)', '3-4 whole eggs', '1 cup grated Parmesan or pecorino cheese', '1 pound spaghetti (or bucatini or fettuccine)', 'Salt and black pepper to taste'], [''], '30 min', 'Heat pasta water: Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).Sauté pancetta/bacon and garlic: While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. Beat eggs and half of the cheese: In a small bowl, beat the eggs and mix in about half of the cheese. Cook pasta: Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. Toss pasta with pancetta/bacon: When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). Add the beaten egg mixture: Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe8: Recipe = new Recipe('2', 'Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', 4.0, [''], [''], '', '', '');
    recipe9: Recipe = new Recipe('3', 'Burger', 'Easy', '/assets/imgs/burger.jpg', 4.8, [''], [''], '', '', '');

    recipes = [this.recipe1, this.recipe2, this.recipe3,
               this.recipe4, this.recipe5, this.recipe6,
               this.recipe7, this.recipe8, this.recipe9];

    userRecipes = [this.recipe1, this.recipe2, this.recipe3];

    getRecipes(): Recipe[] {
        return this.recipes;
    }

    getRecipesByUserId(userId: string | undefined): Recipe[] {
        return this.userRecipes;
    }

    getRecipe(recipeId: string): Observable<Recipe> {
        const recipes = this.getRecipes();
        for (const recipe of recipes) {
            if (recipe.id === recipeId) {
                return of(recipe);
            }
        }
        return of();
    }

    createRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            this.recipes.push(recipe);
            resolve(recipe);
        });
    }

    updateRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            resolve(recipe);
        });
    }

    deleteRecipe(recipe: Recipe): Promise<Recipe> {
        return new Promise((resolve, reject) => {
            // TODO Here should be the BE call but for now we use local data
            for (let i = 0; i < this.userRecipes.length; i++) {
                if (this.userRecipes[i].id === recipe.id) {
                    this.userRecipes.splice(i, 1);
                }
            }
            resolve(recipe);
        });
    }
}
