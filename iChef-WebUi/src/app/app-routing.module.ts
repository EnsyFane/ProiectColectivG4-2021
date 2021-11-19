import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { RecipesComponent } from './components/recipes/recipes.component';
import { RecipeCreationPageComponent } from './components/recipe-creation-page/recipe-creation-page.component';

const routes: Routes = [

    { path: '', redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: LandingPageComponent },
    { path: 'recipes/create', component: RecipeCreationPageComponent },
    { path: 'recipes/details/:id', component: RecipeDetailsComponent },
    { path: 'recipes', component: RecipesComponent }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
