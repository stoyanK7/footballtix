describe('Verify that admins can edit a match', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Update football match', function () {
    cy.visit('http://localhost:3000')

    cy.get('div.match-card').last().click()

    cy.get('button.edit-button').click()

    cy.get('input[name=homeTeam]').clear().type('aasdasd')

    cy.get('button[type=submit]').click()

    cy.url().should('include', '/matches/')
    cy.contains('Match edited successfully')
    cy.contains('aasdasd')
  })
})