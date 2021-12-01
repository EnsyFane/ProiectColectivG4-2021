import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HeaderComponent } from './components/header/header.component';
import { MatIconModule } from '@angular/material/icon';
import {MatDialogModule} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RecipesModule } from './components/recipes/recipes.module';
import { RecipeDetailsComponent } from './components/recipe-details/recipe-details.component';
import { ReviewCardComponent } from './components/recipe-details/review-card/review-card.component';
import { RecipePageModule } from './components/recipe-page/recipe-page.module';
import { UserProfilePageComponent } from './components/user-profile-page/user-profile-page.component';
import { UserRecipesComponent } from './components/user-profile-page/user-recipes/user-recipes.component';
import { environment } from 'src/environments/environment';
import { RecipesService } from './services/recipes.service';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';

@NgModule({
    declarations: [
        AppComponent,
        LandingPageComponent,
        HeaderComponent,
        RecipeDetailsComponent,
        ReviewCardComponent,
        UserProfilePageComponent,
        UserRecipesComponent,
        LoginComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        RecipesModule,
        MatButtonModule,
        MatIconModule,
        MatFormFieldModule,
        MatDialogModule,
        RecipePageModule,
        FormsModule,
        ReactiveFormsModule,
        MatInputModule
    ],
    providers: [
        { provide: 'BASE_API_URL', useValue: environment.apiUrl },
        RecipesService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
