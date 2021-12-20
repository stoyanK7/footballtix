describe('Verify that registered users can log out of their account', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })

  it('Log in', function () {
    cy.visit('http://localhost:3000')

    cy.get('.fa-bars').click()

    cy.get('a[href="/logout"]').click()

    cy.url().should('include', '/login')

    cy.contains('Until next time')
  })
})