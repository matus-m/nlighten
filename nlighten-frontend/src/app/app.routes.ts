import { provideRouter, RouterConfig }  from '@angular/router';

import { HomeRoutes } from './home/home.routes';
import { CourseRoutes } from './courses/courses.routes';
import { AppComponent } from './app.component';
import { PageNotFoundComponent } from './shared/pageNotFound.component';

// import { LoginRoutes,
//          AUTH_PROVIDERS }     from './login.routes';

import { CanDeactivateGuard } from './interfaces';

const auxRoutes: RouterConfig = [
  { path: '**', component: PageNotFoundComponent }
];

export const routes: RouterConfig = [
  ...HomeRoutes,
  ...CourseRoutes,
  ...auxRoutes
  //add other routes
];

export const APP_ROUTER_PROVIDERS = [
  provideRouter(routes),
  //   AUTH_PROVIDERS,
  CanDeactivateGuard
];
