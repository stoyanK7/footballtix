describe('Verify that visitors can see past matches', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })
  it('See past matches', function () {
    cy.visit('http://localhost:3000/matches/past')
  
    cy.contains('Past matches')
  })
})