import {Component, OnInit } from '@angular/core';
import {CoursesService} from './courses.service';
import {ROUTER_DIRECTIVES} from '@angular/router';
import {HTTP_PROVIDERS} from '@angular/http';
import {CourseDetailComponent} from './course-detail/';
import {CourseEditComponent} from './course-edit/';
import {CourseNewComponent} from './course-new/';
import {Course} from './course.model';
import {CourseCard} from './courseCard.component';


@Component({
  moduleId: module.id,
  selector: 'courses',
  templateUrl: 'courses.component.html',
  styleUrls: ['courses.component.css'],
  directives: [ROUTER_DIRECTIVES, CourseCard],
  providers: [CoursesService, HTTP_PROVIDERS]
})
export class CoursesComponent implements OnInit {

  private courses: Course[];

  constructor(private service: CoursesService) {
  }

  ngOnInit() {
    console.log('init courses component, fetching data');
    this.service.getCourses().subscribe(
      courses => this.courses = courses,
      error => console.log('error in http ' + error)
    );
  }

}
