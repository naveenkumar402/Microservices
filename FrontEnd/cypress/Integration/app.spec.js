// cypress/integration/app.spec.js

describe('App Tests', () => {
    it('Should navigate to Customers page', () => {
      cy.visit('/');
      cy.contains('Customers').click();
      cy.url().should('include', '/customers');
      // Add more assertions as needed
    });
  
    it('Should navigate to Products page', () => {
      cy.visit('/');
      cy.contains('Products').click();
      cy.url().should('include', '/products');
      // Add more assertions as needed
    });
  
    it('Should submit customer form', () => {
      cy.visit('/customers');
      cy.get('#name').type('John Doe');
      cy.get('#age').type('25');
      cy.get('#mobile').type('1234567890');
      cy.get('#email').type('john@example.com');
      cy.get('#password').type('password123');
      cy.get('#country').type('USA');
      cy.get('#state').type('CA');
      cy.get('#zipcode').type('12345');
      cy.get('button[type="submit"]').click();
      
    });
  
    it('Should submit product form', () => {
      cy.visit('/products');
      cy.get('#category').select('Electronics');
      cy.get('#product').select('Laptop');
      cy.get('#price').type('999');
      cy.get('#email').type('john@example.com');
      cy.get('button[type="submit"]').click();
      // Add assertions based on the expected behavior
    });
  });
  