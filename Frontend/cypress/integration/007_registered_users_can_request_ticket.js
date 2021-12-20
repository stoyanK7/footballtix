describe('Verify that registered users can request ticket', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })

  it('Log in', function () {
    cy.visit('http://localhost:3000')

    cy.get('.fa-bars').click()

    cy.get('a[href="/orders"]').click()

    cy.get('div.order-card').first().click()

    cy.url().should('include', '/orders/')

    cy.get('input[name=email]').type('test1@gmail.com')

    cy.get('button').click()

    cy.contains('Ticket sent')
  })
})