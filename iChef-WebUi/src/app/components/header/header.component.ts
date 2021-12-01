import { Component } from '@angular/core';
import { HEADER_LINKS } from 'src/app/constants/header';
import { BUTTON_STRINGS, LANDING_PAGE_STRINGS } from 'src/app/constants/texts';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { LoginComponent } from '../login/login.component';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})

export class HeaderComponent {

    readonly homeLink = HEADER_LINKS.HOME;
    readonly recipesLink = HEADER_LINKS.RECIPES;
    readonly myAccountLink = HEADER_LINKS.MY_ACCOUNT;
    readonly loginBtn = BUTTON_STRINGS.LOGIN;
    readonly registerBtn = BUTTON_STRINGS.REGISTER;
    readonly appTitle = LANDING_PAGE_STRINGS.TITLE;

  headerLinks = new Map<string, boolean>([
      [this.homeLink, true],
      [this.recipesLink, false],
      [this.myAccountLink, false]
  ]);

  constructor(private dialog: MatDialog) { }

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
      } else if (tab === this.myAccountLink) {
          this.headerLinks.set(this.myAccountLink, true);
      }
  }

  login() : void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '30%';
    dialogConfig.panelClass = 'dialog';
    this.dialog.open(LoginComponent, dialogConfig);
  }
}
