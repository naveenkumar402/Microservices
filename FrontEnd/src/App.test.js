// jest-dom adds custom jest matchers for asserting on DOM nodes.
// allows you to do things like:
// expect(element).toHaveTextContent(/react/i)
// learn more: https://github.com/testing-library/jest-dom


import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import '@testing-library/jest-dom';
import axios from 'axios';
import Customers from './Customers';


jest.mock('axios');

describe('Customers Component', () => {
  test('should render the component and handle form submission', async () => {
    const mockResponse = {
      status: 200,
      data: 'Registration successfully',
    };

    axios.post.mockResolvedValueOnce(mockResponse);

    render(<Customers />);

  
    fireEvent.change(screen.getByLabelText(/name/i), { target: { value: 'John Doe' } });
    fireEvent.change(screen.getByLabelText(/age/i), { target: { value: '25' } });
    fireEvent.change(screen.getByLabelText(/mobile/i),{target: {value: '1234567890'}});
    fireEvent.change(screen.getByLabelText(/email/i),{target: {value: 'john@gmail.com'}});
    fireEvent.change(screen.getByLabelText(/password/i),{target: {value: 'john123'}});
    fireEvent.change(screen.getByLabelText(/country/i),{target: {value: 'India'}});
    fireEvent.change(screen.getByLabelText(/state/i),{target: {value: 'Tamilnadu'}});
    fireEvent.change(screen.getByLabelText(/zipcode/i),{target: {value: '123456'}});

    fireEvent.click(screen.getByText(/sign up/i));

    
    await waitFor(() => {
      
      expect(axios.post).toHaveBeenCalledWith('http://localhost:8081/customerservice/registercustomer', {
        name: 'John Doe',
        age: '25',
        mobile: '1234567890',
        email: 'john@gmail.com',
        password: 'john@123',
        country: 'India',
        state: 'Tamilnadu',
        zipcode: '123456'

        
      });

      
      expect(screen.getByText(/registration successfully/i)).toBeInTheDocument();
    });
  });
});
