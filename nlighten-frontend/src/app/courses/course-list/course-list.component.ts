import {Component, OnInit } from '@angular/core';
import {CoursesService} from '../courses.service';

import {Course} from '../course.model';

@Component({
  selector: 'course-list',
  outputs: [],
  templateUrl: 'course-list.component.html',
  styleUrls: ['course-list.component.css']
})
export class CourseListComponent implements OnInit {

  private courses: Course[];

  constructor(private service: CoursesService) {
     console.log('constructor courses component');
  }

  ngOnInit() {
    console.log('init courses component, fetching data');
    this.service.getCourses().subscribe(
      courses => this.courses = courses,
      error => console.log('error in http ' + error)
    );
  }

}
