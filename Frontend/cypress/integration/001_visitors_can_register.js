describe('Verify that visitors can register', function () {
  it('Register', function () {
    cy.visit('http://localhost:3000')

    cy.get('.fa-bars').click()

    cy.get('a[href="/register"]').click()
    
    cy.get('input[name=email]').type('test1@gmail.com')

    cy.get('input[name=fullName]').type('Asddd')

    cy.get('input[name=password]').type('Gogo_281202')

    cy.get('button[type=submit]').click()

    cy.url().should('include', '/login')

    cy.contains('confirm your email')
  })
})