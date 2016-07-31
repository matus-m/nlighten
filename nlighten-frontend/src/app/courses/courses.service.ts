import { Injectable } from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/catch';

import {Course} from './course.model';

@Injectable()
export class CoursesService {

  private coursesUrl = 'http://localhost:8080/nlighten/rest/v1/courses';

  private headers = new Headers();

  constructor(private http: Http) {
    console.log('Courses service initialiazed');
    this.headers.append('Content-Type', 'application/json');
  }

  getCourses(): Observable<Course[]> {
    return this.http.get(this.coursesUrl).map(this.extractData).catch(this.handleError);
  }

  getById(courseId: number): Observable<Course> {
    return this.http.get(this.coursesUrl + '/' + courseId).map(this.extractData).catch(this.handleError);
  }

  save(course: Course): Observable<Course> {
    return this.http.post(this.coursesUrl, JSON.stringify(course), {
      headers: this.headers
    }).map(this.extractData).catch(this.handleError);
  }

  private extractData(res: Response) {
    let body = res.json();
    // return body.data || {};
    return body;
  }
  private handleError(error: any) {
    // In a real world app, we might use a remote logging infrastructure
    // We'd also dig deeper into the error to get a better message
    let errMsg = (error.message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg); // log to console instead
    return Observable.throw(errMsg);
  }

}
