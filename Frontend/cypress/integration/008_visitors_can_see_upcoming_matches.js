describe('Verify that visitors can see upcoming matches', function () {
  it('See upcoming matches', function () {
    cy.visit('http://localhost:3000')
  
    cy.contains('Upcoming matches')
  })
})