import {
  beforeEachProviders,
  it,
  describe,
  expect,
  inject
} from '@angular/core/testing';
import { HomeServiceService } from './home-service.service';

describe('HomeService Service', () => {
  beforeEachProviders(() => [HomeServiceService]);

  it('should ...',
      inject([HomeServiceService], (service: HomeServiceService) => {
    expect(service).toBeTruthy();
  }));
});
