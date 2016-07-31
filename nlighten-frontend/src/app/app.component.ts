import { Component } from '@angular/core';
import { HTTP_PROVIDERS } from '@angular/http';
import { CoursesComponent } from './courses';
import { ROUTER_DIRECTIVES} from '@angular/router';
import { ProfileComponent } from './profile';
import { HomeComponent } from './home';

@Component({
  moduleId: module.id,
  selector: 'app-root',
  templateUrl: 'app.component.html',
  styleUrls: ['app.component.css'],
  providers: [HTTP_PROVIDERS],
  directives: [ROUTER_DIRECTIVES]
})
export class AppComponent {
  title = 'app works!';
}

