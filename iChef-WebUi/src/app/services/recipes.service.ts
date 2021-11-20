import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import { Ingredient } from '../data-types/ingredient';
import {Recipe} from '../data-types/recipe';
import { Utensil } from '../data-types/utensil';

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

    recipe1: Recipe = new Recipe('Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', [new Ingredient('extra virgin olive oil or unsalted butter', '1', 'tablespoon'), new Ingredient('pancetta or thick cut bacon, diced', '1/2', 'pound' ), new Ingredient('eggs', '3-4', 'whole'), new Ingredient('grated Parmesan or pecorino cheese', '1', 'cup'), new Ingredient('spaghetti (or bucatini or fettuccine)', '1', 'pound' ), new Ingredient('Salt and black pepper', 'to taste','')], [new Utensil('Utensil 1'), new Utensil('Utensil 2'), new Utensil('Utensil 3'), new Utensil('Utensil 4'), new Utensil('Utensil 5')], '30 min', 'Heat pasta water:\n Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).\n\nSauté pancetta/bacon and garlic:\n While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. \n\nBeat eggs and half of the cheese:\n In a small bowl, beat the eggs and mix in about half of the cheese. \n\nCook pasta:\n Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. \n\nToss pasta with pancetta/bacon:\n When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). \n\nAdd the beaten egg mixture:\n Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe2: Recipe = new Recipe('Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', [], [], '', '', '');
    recipe3: Recipe = new Recipe('Burger', 'Easy', '/assets/imgs/burger.jpg', [], [], '', '', '');
    recipe4: Recipe = new Recipe('Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', [new Ingredient('extra virgin olive oil or unsalted butter', '1', 'tablespoon'), new Ingredient('pancetta or thick cut bacon, diced', '1/2', 'pound' ), new Ingredient('eggs', '3-4', 'whole'), new Ingredient('grated Parmesan or pecorino cheese', '1', 'cup'), new Ingredient('spaghetti (or bucatini or fettuccine)', '1', 'pound' ), new Ingredient('Salt and black pepper', 'to taste','')], [new Utensil('Utensil 1'), new Utensil('Utensil 2'), new Utensil('Utensil 3'), new Utensil('Utensil 4'), new Utensil('Utensil 5')], '30 min', 'Heat pasta water:\n Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).\n\nSauté pancetta/bacon and garlic:\n While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. \n\nBeat eggs and half of the cheese:\n In a small bowl, beat the eggs and mix in about half of the cheese. \n\nCook pasta:\n Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. \n\nToss pasta with pancetta/bacon:\n When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). \n\nAdd the beaten egg mixture:\n Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe5: Recipe = new Recipe('Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', [], [], '', '', '');
    recipe6: Recipe = new Recipe('Burger', 'Easy', '/assets/imgs/burger.jpg', [], [], '', '', '');
    recipe7: Recipe = new Recipe('Pasta Carbonara', 'Easy', '/assets/imgs/pasta.jpg', [new Ingredient('extra virgin olive oil or unsalted butter', '1', 'tablespoon'), new Ingredient('pancetta or thick cut bacon, diced', '1/2', 'pound' ), new Ingredient('eggs', '3-4', 'whole'), new Ingredient('grated Parmesan or pecorino cheese', '1', 'cup'), new Ingredient('spaghetti (or bucatini or fettuccine)', '1', 'pound' ), new Ingredient('Salt and black pepper', 'to taste','')], [new Utensil('Utensil 1'), new Utensil('Utensil 2'), new Utensil('Utensil 3'), new Utensil('Utensil 4'), new Utensil('Utensil 5')], '30 min', 'Heat pasta water:\n Put a large pot of salted water on to boil (1 tablespoon salt for every 2 quarts of water).\n\nSauté pancetta/bacon and garlic:\n While the water is coming to a boil, heat the olive oil or butter in a large sauté pan over medium heat. Add the bacon or pancetta and cook slowly until crispy. Add the garlic (if using) and cook another minute, then turn off the heat and put the pancetta and garlic into a large bowl. \n\nBeat eggs and half of the cheese:\n In a small bowl, beat the eggs and mix in about half of the cheese. \n\nCook pasta:\n Once the water has reached a rolling boil, add the dry pasta, and cook, uncovered, at a rolling boil. \n\nToss pasta with pancetta/bacon:\n When the pasta is al dente (still a little firm, not mushy), use tongs to move it to the bowl with the bacon and garlic. Let it be dripping wet. Reserve some of the pasta water. Move the pasta from the pot to the bowl quickly, as you want the pasta to be hot. It is the heat of the pasta that will heat the eggs sufficiently to create a creamy sauce. Toss everything to combine, allowing the pasta to cool just enough so that it does not make the eggs curdle when you mix them in. (That is the tricky part). \n\nAdd the beaten egg mixture:\n Add the beaten eggs with cheese and toss quickly to combine once more. Add salt to taste. Add some pasta water back to the pasta to keep it from drying out.Serve at once with the rest of the parmesan and freshly ground black pepper. If you want, sprinkle with a little fresh chopped parsley.', '4 to 6 servings');
    recipe8: Recipe = new Recipe('Pizza Quattro Fromaggi', 'Easy', '/assets/imgs/pizza.jpg', [], [], '', '', '');
    recipe9: Recipe = new Recipe('Burger', 'Easy', '/assets/imgs/burger.jpg', [], [], '', '', '');

    recipes = [this.recipe1, this.recipe2, this.recipe3,
               this.recipe4, this.recipe5, this.recipe6,
               this.recipe7, this.recipe8, this.recipe9];

    userRecipes = [this.recipe1, this.recipe2, this.recipe3];

    getRecipes(): Recipe[] {
        return this.recipes;
    }

    getRecipesByUserId(userId: string): Recipe[] {
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
