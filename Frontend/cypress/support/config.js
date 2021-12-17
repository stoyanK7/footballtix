import "cypress-localstorage-commands";

Cypress.Commands.add('loginAdmin', () => {
  cy.request('POST', 'http://localhost:8080/api/authenticate', {
    'email': "admin@gmail.com",
    'password': 'Gogo_281202'
  })
    .then(response => {
      // cy.window(win => win.localStorage.setItem('jwtToken', response.body.jwt))
      cy.setLocalStorage('jwtToken', response.body.jwt)
    })
})