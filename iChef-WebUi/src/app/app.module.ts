import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatButtonModule } from '@angular/material/button';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';
import { HeaderComponent } from './components/header/header.component';
import { MatIconModule } from '@angular/material/icon';
import { MatDialogModule } from '@angular/material/dialog';
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
import { UsersService } from './services/users.service';
import { SnackbarModule } from './services/snackbar/snackbar.module';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { CommonModule } from '@angular/common';
import { CloudinaryService } from './services/cloudinary.service';
import { SharedService } from './services/shared.service';

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
        MatInputModule,
        SnackbarModule,
        CommonModule,
        MatSnackBarModule
    ],
    providers: [
        { provide: 'BASE_API_URL', useValue: environment.apiUrl },
        { provide: 'CLOUDINARY_CLOUD_NAME', useValue: environment.cloudinaryCloudName },
        { provide: 'CLOUDINARY_API_KEY', useValue: environment.cloudinaryAPIKey },
        { provide: 'CLOUDINARY_API_SECRET', useValue: environment.cloudinaryAPISecret },
        RecipesService,
        UsersService,
        CloudinaryService,
        SharedService
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
