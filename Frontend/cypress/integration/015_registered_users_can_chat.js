describe('Verify that users can chat', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Chat', function () {
    cy.visit('http://localhost:3000/chat')

    cy.get('input#message').type('helo word')

    cy.get('button.primary').click()

    cy.contains('helo word')
  })
})