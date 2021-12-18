describe('Verify that admins can delete a match', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Delete football match', function () {
    cy.visit('http://localhost:3000')

    cy.get('div.match-card').first().click()

    cy.get('button.delete-button').click()

    cy.url().should('include', '/')

    cy.contains('successfully')
  })
})