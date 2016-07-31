import { NlightenPage } from './app.po';

describe('nlighten App', function() {
  let page: NlightenPage;

  beforeEach(() => {
    page = new NlightenPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('nlighten works!');
  });
});
