describe('Verify that visitors cannot access public chat', function () {
  it('Visit public chat', function () {
    cy.visit('http://localhost:3000/chat')

    cy.url().should('include', '/login')

    cy.contains('You need to login first')
  })
})