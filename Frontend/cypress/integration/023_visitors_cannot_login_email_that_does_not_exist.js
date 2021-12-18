describe('Verify that visitors cannot log in with an email that does not exist', function () {
  it('Log in', function () {
    cy.visit('http://localhost:3000/login')

    cy.get('input[name=email]').type('tessadfswdfsadfsdft@gmail.com')

    cy.get('input[name=password]').type('Gogo_281202')

    cy.get('button[type=submit]').click()

    cy.contains('User not found')
  })
})