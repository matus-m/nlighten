import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders }  from '@angular/core';

const appRoutes: Routes = [
    { path: '', redirectTo: 'home', pathMatch: 'full'},
    { path: 'courses', loadChildren: 'app/courses/courses.module#CourseModule' },
    { path: 'profile', loadChildren: 'app/profile/profile.module#ProfileModule' }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);