import { InterPage } from './app.po';

describe('Nlighten App', function() {
  let page: InterPage;

  beforeEach(() => {
    page = new InterPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
