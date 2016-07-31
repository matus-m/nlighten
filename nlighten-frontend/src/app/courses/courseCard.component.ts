import { Component, Input } from '@angular/core';
import { ROUTER_DIRECTIVES } from '@angular/router';

@Component({
    selector: "course-card",
    directives: [ROUTER_DIRECTIVES],
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
        <div class="course-card" >
            <a [routerLink]="[course.id]" class="menu-item"><span class="course-title">{{course.title}}</span> </a>
            <p class="course-description">{{course.description}}</p> 
        </div>
    `
})
export class CourseCard {
    @Input() course;
}