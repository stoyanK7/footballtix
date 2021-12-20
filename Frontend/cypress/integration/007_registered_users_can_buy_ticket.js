// describe('Verify that registered users can buy ticket', function () {
//   beforeEach(() => {
//     cy.loginUser('fOoTbAlLtIx_281202');
//   })

//   it('Order ticket', function () {
//     cy.visit('http://localhost:3000')

//     cy.get('div.match-card').first().click()
  
//     cy.get('button.buy-button').click();

//     cy.get('input[name=fullName]').type('Stoyan Kostadinovv')
//     cy.get('input[name=email]').type('stoyank127@gmail.com')
//     cy.get('input[name=mobilePhone]').type('359878890852')
//     cy.get('input[name=address]').type('Argostraat 26')
//     cy.get('input[name=city]').type('Eindhoven')
//     cy.get('input[name=country]').type('Netherlands')
//     cy.get('input[name=postcode]').type('5631JZ')
//     cy.get('input[type=checkbox]').first().check()
//     cy.get('input[type=checkbox]').eq(1).check()
//     cy.get('button[type=submit]').click()

//     cy.get('#buttons-container > div > div.paypal-button-row.paypal-button-number-0.paypal-button-layout-vertical.paypal-button-shape-rect.paypal-button-number-multiple.paypal-button-env-sandbox.paypal-button-color-gold.paypal-button-text-color-black.paypal-logo-color-blue > div').click()
//   })
// })