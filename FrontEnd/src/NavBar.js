// Navbar.js
import React from 'react';
import { useNavigate } from 'react-router-dom';
import './Navbar.css';

const Navbar = () => {
  const navigate = useNavigate();

  const navigateTo = (path) => {
    navigate(path);
  };

  return (
    <nav className="navbar">
      <div className="navbar-container">
        <div className="navbar-logo">
          SEAWINGS
        </div>
        <ul className="navbar-menu">
          <li className="navbar-item">
            <button className="navbar-link" onClick={() => navigateTo('/customers')}>
              Customers
            </button>
          </li>
          <li className="navbar-item">
            <button className="navbar-link" onClick={() => navigateTo('/products')}>
              Products
            </button>
          </li>
        </ul>
      </div>
    </nav>
  );
};

export default Navbar;
