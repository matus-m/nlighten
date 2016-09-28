import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { CourseListComponent } from './course-list';
import { CourseEditComponent } from './course-edit';
import { CourseDetailComponent } from './course-detail';
import { CourseNewComponent } from './course-new';
import { CourseCard } from './courseCard.component';
import { CoursesService } from './courses.service';
import { routing } from './courses.routes';

@NgModule({
  imports: [CommonModule, FormsModule, routing],
  declarations: [CourseListComponent, CourseDetailComponent, CourseCard, CourseEditComponent, CourseNewComponent],
  exports: [],
  providers: [CoursesService]
})
export class CourseModule { }