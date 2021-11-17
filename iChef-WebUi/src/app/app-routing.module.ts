import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LandingPageComponent} from './components/landing-page/landing-page.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RecipePageComponent } from './components/recipe-page/recipe-page.component';

const routes: Routes = [

    {path: '', redirectTo: '/home', pathMatch: 'full'},
    {path: 'home', component: LandingPageComponent},
    {path: 'recipes', component: RecipesComponent},
    {path: 'createrecipe', component: RecipePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
