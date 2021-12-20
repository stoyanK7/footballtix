describe('Verify that registered users can update password', function () {
  beforeEach(() => {
    cy.loginUser('fOoTbAlLtIx_281202');
  })

  it('Update password', function () {
    cy.visit('http://localhost:3000/profile')

    cy.get('input[name=currentPassword]').type('fOoTbAlLtIx_281202')
    cy.get('input[name=newPassword]').type('fOoTbAlLtIx_2812022')
    cy.get('input[name=confirmPassword]').type('fOoTbAlLtIx_2812022')

    cy.get('button[type=submit]').eq(1).click()

    cy.url().should('include', '/login')
    cy.contains('Password successfully changed')
  })
})