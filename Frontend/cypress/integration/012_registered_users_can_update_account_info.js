describe('Verify that registered users can update account info', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Update account info', function () {
    cy.visit('http://localhost:3000/profile')

    cy.get('input[type=email]').type('adminasdasdasd@gmail.com')

    cy.get('button[type=submit]').first().click()

    cy.url().should('include', '/login')
    cy.contains('Info successfully changed')
  })
})