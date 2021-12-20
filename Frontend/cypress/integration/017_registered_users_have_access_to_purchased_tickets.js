describe('Verify that registered users have access to purchased tickets', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })
  it('See purchased tickets', function () {
    cy.visit('http://localhost:3000/orders')
  
    cy.contains('Orders')
  })
})