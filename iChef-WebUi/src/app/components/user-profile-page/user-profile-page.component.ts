import { Component, Input, OnInit } from '@angular/core';
import { PATHS } from 'src/app/constants/paths';
import { ACCOUNT_MENU_LINKS } from 'src/app/constants/account-page';
import { UsersService } from '../../services/users.service';
import { LoggedUser } from '../../data-types/logged-user';
import { SharedService } from '../../services/shared.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-profile-page',
  templateUrl: './user-profile-page.component.html',
  styleUrls: ['./user-profile-page.component.scss']
})
export class UserProfilePageComponent implements OnInit {

    @Input() userId?: string;
    user!: LoggedUser;

    readonly landingPageImage = PATHS.LANDING_PAGE_IMAGE;
    readonly profilePicture = PATHS.DEFAULT_PROFILE_PICTURE;
    readonly overviewLink = ACCOUNT_MENU_LINKS.ACCOUNT_OVERVIEW;
    readonly myRecipesLink = ACCOUNT_MENU_LINKS.MY_RECIPES;
    readonly settingsLink = ACCOUNT_MENU_LINKS.SETTINGS;
    readonly logoutLink = ACCOUNT_MENU_LINKS.LOGOUT;

    profileName! : string;

    accountLinks = new Map<string, boolean>([
        [this.overviewLink, false],
        [this.myRecipesLink, true],
        [this.settingsLink, false],
        [this.logoutLink, false]
    ]);

    constructor(
        private usersService: UsersService,
        private sharedService: SharedService,
        private router: Router) { }

    ngOnInit(): void {
        if (!this.sharedService.getIsUserLogged()) {
            this.router.navigate(['home']);
            return;
        }
        this.user = this.usersService.getLoggedUser();
        this.profileName = this.user.firstName + ' ' + this.user.lastName;
    }

    isUserLogged() : boolean {
        return this.sharedService.isUserLogged;
    }

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
