import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipesModule } from './components/recipes/recipes.module';
import { RecipePageModule } from './components/recipe-page/recipe-page.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    RecipesModule,
    RecipePageModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
