import { Component, OnInit } from '@angular/core';
import { PATHS } from 'src/app/constants/paths';
import { ACCOUNT_MENU_LINKS } from 'src/app/constants/account-page';

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent {

    readonly landingPageImage = PATHS.LANDING_PAGE_IMAGE;
    readonly profilePicture = PATHS.DEFAULT_PROFILE_PICTURE;
    readonly profileName = 'Aya Kiotsune';
    readonly overviewLink = ACCOUNT_MENU_LINKS.ACCOUNT_OVERVIEW;
    readonly myRecipesLink = ACCOUNT_MENU_LINKS.MY_RECIPES;
    readonly settingsLink = ACCOUNT_MENU_LINKS.SETTINGS;
    readonly logoutLink = ACCOUNT_MENU_LINKS.LOGOUT;

    accountLinks = new Map<string, boolean>([
        [this.overviewLink, false],
        [this.myRecipesLink, true],
        [this.settingsLink, false],
        [this.logoutLink, false]
    ]);

    resetOptions() : void {
        for (const key of this.accountLinks.keys()) {
            this.accountLinks.set(key, false);
        }
    }

    setActiveOption(tab: string) : void  {
        this.resetOptions();
        if (tab === this.overviewLink) {
            this.accountLinks.set(this.overviewLink, true);
        } else if (tab === this.myRecipesLink) {
            this.accountLinks.set(this.myRecipesLink, true);
        } else if (tab === this.settingsLink) {
            this.accountLinks.set(this.settingsLink, true);
        } else if (tab === this.logoutLink) {
            this.accountLinks.set(this.logoutLink, true);
        }
    }
}
