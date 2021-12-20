import setDate from '../support/setDate';

describe('Verify that admins can add a match', function () {
  beforeEach(() => {
    cy.loginAdmin();
  })

  it('Create football match', function () {
    cy.visit('http://localhost:3000')

    cy.get('.fa-bars').click()

    cy.get('a[href="/matches/create"]').click()

    cy.get('input[name=homeTeam]').type('Cypress test home')
    cy.get('input[name=awayTeam]').type('Cypress test away')
    cy.get('input[name=startingDateTime]').click()
      .then(input => {
        setDate(input[0], '2223-12-12T00:00')
      }).click()
    cy.get('input[name=stadium]').type('Cypress test staidum')
    cy.get('input[name=location]').type('Cypress test location')
    cy.get('input[name=league]').type('Cypress test league')
    cy.get('input[name=ticketsAvailable]').type('1500')
    cy.get('input[name=pricePerTicket]').type('100')

    cy.get('button[type=submit]').click()

    cy.url().should('include', '/matches/')

    cy.contains('created')
  })
})