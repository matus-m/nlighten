import { Component, OnInit } from '@angular/core';
import {CoursesService} from '../courses.service';
import {Course} from '../course.model';

@Component({
  moduleId: module.id,
  selector: 'app-course-new',
  templateUrl: 'course-new.component.html',
  providers: [CoursesService],
  styleUrls: ['course-new.component.css']
})
export class CourseNewComponent implements OnInit {

  private course:Course

  constructor(private service: CoursesService) {}

  ngOnInit() {
    this.course = {};
  }

  public save() {
    console.log('saving....' + JSON.stringify(this.course));
    this.service.save(this.course).subscribe(
      data => console.log('save ok'),
      error => console.log('failed to save')
    );
  }

  public cancel() {

  }

}
