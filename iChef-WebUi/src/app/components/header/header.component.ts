import { Component } from '@angular/core';
import { HEADER_LINKS } from 'src/app/constants/header';
import { BUTTON, LANDING_PAGE } from 'src/app/constants/texts';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent {

    readonly homeLink = HEADER_LINKS.HOME;
    readonly recipesLink = HEADER_LINKS.RECIPES;
    readonly loginBtn = BUTTON.LOGIN;
    readonly registerBtn = BUTTON.REGISTER;
    readonly appTitle = LANDING_PAGE.TITLE;

  headerLinks = new Map<string, boolean>([
      [this.homeLink, true],
      [this.recipesLink, false]
  ]);

  resetTabs() : void {
      for (const key of this.headerLinks.keys()) {
          this.headerLinks.set(key, false);
      }
  }

  setActiveTab(tab: string) : void  {
      this.resetTabs();
      if (tab === this.homeLink) {
          this.headerLinks.set(this.homeLink, true);
      } else if (tab === this.recipesLink) {
          this.headerLinks.set(this.recipesLink, true);
      }
  }
}
