describe('Verify that visitors can register', function () {
  it('Register', function () {
    cy.visit('http://localhost:3000/register')

    cy.get('input[name=email]').type('test@gmail.com')

    cy.get('input[name=fullName]').type('Asddd')

    cy.get('input[name=password]').type('Gogo_281202')

    cy.get('button[type=submit]').click()

    cy.contains('User with such email already exists')
  })
})