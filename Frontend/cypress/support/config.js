import "cypress-localstorage-commands";

Cypress.config('defaultCommandTimeout', 10000);

Cypress.Commands.add('loginAdmin', () => {
  cy.request('POST', 'http://localhost:8080/api/authenticate', {
    'email': "admin@gmail.com",
    'password': 'fOoTbAlLtIx_281202'
  })
    .then(response => {
      // cy.window(win => win.localStorage.setItem('jwtToken', response.body.jwt))
      cy.setLocalStorage('jwtToken', response.body.jwt)
    })
})

Cypress.Commands.add('loginUser', (password, email = "test@gmail.com") => {
  cy.request('POST', 'http://localhost:8080/api/authenticate', {
    'email': email,
    'password': password
  })
    .then(response => {
      // cy.window(win => win.localStorage.setItem('jwtToken', response.body.jwt))
      cy.setLocalStorage('jwtToken', response.body.jwt)
    })
})