import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LandingPageComponent} from './components/landing-page/landing-page.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RecipePageComponent } from './components/recipe-page/recipe-page.component';

const routes: Routes = [
<<<<<<< HEAD
    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: LandingPageComponent},
    {path: 'recipes', component: RecipesComponent},
    {path: 'recipes/details/:id', component: RecipeDetailsComponent}
=======
  {
    path: '',
    component: RecipePageComponent // RecipesComponent
  },
  {
      path: '**',
      redirectTo: ''
  },
  {
    path: 'createrecipe',
    component: RecipePageComponent
  }
>>>>>>> TM_recipeScreen
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
