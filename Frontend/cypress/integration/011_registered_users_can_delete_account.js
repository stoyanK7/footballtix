describe('Verify that registered users can delete account', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Delete account', function () {
    cy.visit('http://localhost:3000/profile')

    cy.get('button.delete-button').click()
    cy.on('window:confirm', () => true);

    cy.contains('Account deleted successfully')
  })
})