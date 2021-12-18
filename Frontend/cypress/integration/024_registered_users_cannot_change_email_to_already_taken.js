describe('Verify that registered users cannot change to an email that is already taken', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Change email', function () {
    cy.visit('http://localhost:3000/profile')

    cy.get('input[type=email]').type('admin@gmail.com')

    cy.get('button[type=submit]').first().click()

    cy.contains('User with such email already exists')
  })
})