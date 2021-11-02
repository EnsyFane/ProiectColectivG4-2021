import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RecipePageComponent } from './components/recipe-page/recipe-page.component';

const routes: Routes = [
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
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
