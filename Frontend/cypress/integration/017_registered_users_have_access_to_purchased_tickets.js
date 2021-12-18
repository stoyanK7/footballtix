describe('Verify that registered users have access to purchased tickets', function () {
  it('See purchased tickets', function () {
    cy.visit('http://localhost:3000/orders')
  
    cy.contains('Orders')
  })
})