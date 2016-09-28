import { Component, Input } from '@angular/core';
import { Course } from './course.model';

@Component({
    selector: "course-card",
    styles: [`
        .course-card {
            padding: 15px;
            border-radius: 2px;
            width: 100%;
            position: relative;
        }

        .course-title {
            font-size: 2em;
        }
    `],
    template: `
        <div class="course-card shadow-1" >
            <a [routerLink]="[course.id]" class="menu-item"><span class="course-title">{{course.title}}</span> </a>
            <p class="course-description">{{course.description}}</p> 
        </div>
    `
})
export class CourseCard {
    @Input() course;
}