import { Component } from '@angular/core';
import { FormControl } from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { PATHS } from 'src/app/constants/paths';
import { BUTTON_STRINGS, LANDING_PAGE_STRINGS, PLACEHOLDERS_STRINGS } from 'src/app/constants/texts';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent {

  readonly landingPageImage = PATHS.LANDING_PAGE_IMAGE;
  readonly appTitle = LANDING_PAGE_STRINGS.TITLE;
  readonly description = LANDING_PAGE_STRINGS.DESCRIPTION;
  readonly searchBtn = BUTTON_STRINGS.SEARCH;
  readonly searchPlaceHolder = PLACEHOLDERS_STRINGS.SEARCH;

  searchText = new FormControl('');

  constructor(private router: Router) {}

  search(): void {
    const navigationExtras: NavigationExtras = {
      state: {
        text: this.searchText.value
      }
    };
    this.router.navigate(['recipes'], navigationExtras);
  }
}
