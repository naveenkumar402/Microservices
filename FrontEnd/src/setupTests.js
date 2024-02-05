const { render, screen, fireEvent, waitFor } = require('@testing-library/react');
require('@testing-library/jest-dom');
const axios = require('axios');
const Customers = require('./Customers');

jest.mock('axios');