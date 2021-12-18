describe('Verify that registered users can buy ticket', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Order ticket', function () {
    cy.visit('http://localhost:3000')

    cy.get('div.match-card').first().click()
  
    cy.get('button.buy-button').click();

    cy.get('input[name=fullName]').type('Stoyan Kostadinovv')
    cy.get('input[name=email]').type('stoyank127@gmail.com')
    cy.get('input[name=mobilePhone]').type('359878890852')
    cy.get('input[name=address]').type('Argostraat 26')
    cy.get('input[name=city]').type('Eindhoven')
    cy.get('input[name=country]').type('Netherlands')
    cy.get('input[name=postcode]').type('5631JZ')
    cy.get('input[type=checkbox]').first().check()
    cy.get('input[type=checkbox]').eq(2).check()
    cy.get('button[type=submit]').click()

    cy.get('button.paypal-button').click()
  })
})