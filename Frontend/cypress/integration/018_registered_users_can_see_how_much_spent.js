describe('Verify that registered users can see how much they have spent', function () {
  it('See purchased tickets', function () {
    cy.visit('http://localhost:3000/orders')
  
    cy.contains('Total spent:')
  })
})