import {
  beforeEachProviders,
  it,
  describe,
  expect,
  inject
} from '@angular/core/testing';
import { LessonServiceService } from './lesson-service.service';

describe('LessonService Service', () => {
  beforeEachProviders(() => [LessonServiceService]);

  it('should ...',
      inject([LessonServiceService], (service: LessonServiceService) => {
    expect(service).toBeTruthy();
  }));
});
