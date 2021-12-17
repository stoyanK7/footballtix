describe('Verify that visitors can see a match overview', function () {
  it('See match overview', function () {
    cy.visit('http://localhost:3000')

    cy.get('div.match-card').first().click()
  
    cy.url().should('include', '/matches/')

    cy.contains('BUY')
    cy.contains('€')
  })
})