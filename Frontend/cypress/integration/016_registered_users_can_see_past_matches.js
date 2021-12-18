describe('Verify that visitors can see past matches', function () {
  it('See past matches', function () {
    cy.visit('http://localhost:3000/past')
  
    cy.contains('Past matches')
  })
})