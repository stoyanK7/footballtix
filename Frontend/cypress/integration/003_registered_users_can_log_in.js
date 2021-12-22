describe('Verify that registered users can log in their account', function () {
  it('Log in', function () {
    cy.visit('http://localhost:3000')

    cy.get('.fa-bars').click()

    cy.get('a[href="/login"]').click()
    
    cy.get('input[name=email]').type('test@gmail.com')

    cy.get('input[name=password]').type('fOoTbAlLtIx_281202')

    cy.get('[data-cy=submit]').click()

    cy.url().should('include', '/')

    cy.contains('Welcome')
  })
})