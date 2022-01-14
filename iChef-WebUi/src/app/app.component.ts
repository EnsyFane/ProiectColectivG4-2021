import {Component, OnInit} from '@angular/core';
import {SharedService} from './services/shared.service';
import {UsersService} from './services/users.service';
import {forkJoin, of} from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  title = 'iChef-WebUi';

    constructor(private sharedService: SharedService,
                private userService: UsersService) { }

    ngOnInit(): void {
        const item = sessionStorage.getItem('loggedUserToken') as any;
        if (item) {
            this.sharedService.setIsUserLogged(true);
            const getUserRequest = this.userService.getUserById(item.userId);
            forkJoin([of(item.userId), getUserRequest]).subscribe(([userId, requestedUser]) => {
                this.userService.user = requestedUser;
            });
        }
    }
}
