import {
  beforeEachProviders,
  it,
  describe,
  expect,
  inject
} from '@angular/core/testing';
import { CoursesService } from './courses.service';

describe('CoursesService Service', () => {
  beforeEachProviders(() => [CoursesService]);

  it('should ...',
      inject([CoursesService], (service: CoursesService) => {
    expect(service).toBeTruthy();
  }));
});
