import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HeaderComponent } from './components/header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipesModule } from './components/recipes/recipes.module';
<<<<<<< HEAD
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { ReviewCardComponent } from './components/recipe-details/review-card/review-card.component';
=======
import { RecipePageModule } from './components/recipe-page/recipe-page.module';
>>>>>>> TM_recipeScreen

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    HeaderComponent,
    RecipeDetailsComponent,
    ReviewCardComponent
  ],
  imports: [
<<<<<<< HEAD
      BrowserModule,
      AppRoutingModule,
      BrowserAnimationsModule,
      RecipesModule,
      MatButtonModule,
      MatIconModule,
      MatFormFieldModule
=======
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RecipesModule,
    RecipePageModule
>>>>>>> TM_recipeScreen
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
