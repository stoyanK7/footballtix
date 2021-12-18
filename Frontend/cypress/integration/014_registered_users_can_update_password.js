describe('Verify that registered users can update password', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Update password', function () {
    cy.visit('http://localhost:3000/profile')

    cy.get('input[name=currentPassword]').type('Gogo_281202')
    cy.get('input[name=newPassword]').type('Gogo_2812022')
    cy.get('input[name=confirmPassword]').type('Gogo_2812022')

    cy.get('button[type=submit]').eq(1).click()

    cy.url().should('include', '/login')
    cy.contains('Password successfully changed')
  })
})