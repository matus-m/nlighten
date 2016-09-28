import { ModuleWithProviders } from '@angular/core';
import { RouterModule } from '@angular/router';

import { CourseListComponent } from './course-list/course-list.component';
import { CourseNewComponent } from './course-new/course-new.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';

export const routing: ModuleWithProviders = RouterModule.forChild([
  { path: '', component: CourseListComponent },
  { path: 'new', component: CourseNewComponent },
  { path: ':id', component: CourseDetailComponent }
]);
