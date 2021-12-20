describe('Verify that registered users can see how much they have spent', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })
  it('See purchased tickets', function () {
    cy.visit('http://localhost:3000/orders')
  
    cy.contains('Total spent:')
  })
})