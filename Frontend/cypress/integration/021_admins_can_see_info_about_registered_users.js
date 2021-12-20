describe('Verify that admins can see info about registered users', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })
  it('See users statistics', function () {
    cy.visit('http://localhost:3000/stats')

    cy.contains('registeredUsers')
  })
})