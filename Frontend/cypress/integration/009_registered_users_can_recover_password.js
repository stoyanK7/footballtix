describe('Verify that registered users can recover password', function () {
  it('Request password recovery', function () {
    cy.visit('http://localhost:3000/login')

    cy.get('a[href="/forgot-password"]')
      .click()

    cy.get('input[type=email]')
      .type('test@gmail.com')

    cy.get('button[type=submit]').click()

    cy.url().should('include', '/login')

    cy.contains('Check your email')
  })
})