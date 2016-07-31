import { RouterConfig }          from '@angular/router';
import { CoursesComponent }     from './courses.component';
import { CourseNewComponent }   from './course-new/course-new.component';
import { CourseDetailComponent }   from './course-detail/course-detail.component';

export const CourseRoutes: RouterConfig = [
  { path: 'courses',  component: CoursesComponent },
  { path: 'courses/new', component: CourseNewComponent },
  { path: 'courses/:id', component: CourseDetailComponent }
  
];