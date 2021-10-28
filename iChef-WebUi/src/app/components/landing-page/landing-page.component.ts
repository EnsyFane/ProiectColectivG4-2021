import { Component, OnInit } from '@angular/core';
import { PATHS } from 'src/app/constants/paths';
import { BUTTON, LANDING_PAGE, PLACEHOLDERS } from 'src/app/constants/texts';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss']
})
export class LandingPageComponent implements OnInit {

  readonly landingPageImage = PATHS.LANDING_PAGE_IMAGE;
  readonly searchIcon = PATHS.SEARCH_ICON;
  readonly appTitle = LANDING_PAGE.TITLE;
  readonly description = LANDING_PAGE.DESCRIPTION;
  readonly searchBtn = BUTTON.SEARCH;
  readonly searchPlaceHolder = PLACEHOLDERS.SEARCH;

  constructor() { }

  ngOnInit(): void {
  }

}
