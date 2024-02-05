// Products.js

import React, { useState } from "react";
import axios from "axios";
import styles from "./Products.css"; // Import the CSS module

const Products = () => {
  const [formData, setFormData] = useState({
    category: "",
    product: "",
    price: "",
    email: "",
  });

  const categories = ["Electronics", "Clothing", "Books"];
  const productsByCategory = {
    Electronics: ["Laptop", "Smartphone", "Tablet"],
    Clothing: ["Shirt", "Pants", "Shoes"],
    Books: ["Fiction", "Non-fiction", "Science"],
  };

  const handleChange = (e) => {
    const { name, value } = e.target;

    setFormData({
      ...formData,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
        const apiUrl = 'http://localhost:8082/productservice/addproduct';
        const response = await axios.post(apiUrl, formData);
        console.log('API Response:', response.data);
  
        if (response.status === 200) {
          window.alert(response.data);
      }
      
  }
       catch (error) {
        console.error('Error:', error);
      }
      
    
  };

  return (
    <div className="form-container">
        <h1>Choose your product</h1>
      <form onSubmit={handleSubmit}>
        <label>
          Category:
          <select name="category" value={formData.category} onChange={handleChange}>
            <option value="">Select Category</option>
            {categories.map((category) => (
              <option key={category} value={category}>
                {category}
              </option>
            ))}
          </select>
        </label>

        {formData.category && (
          <label>
            Product:
            <select name="product" value={formData.product} onChange={handleChange}>
              <option value="">Select Product</option>
              {productsByCategory[formData.category].map((product) => (
                <option key={product} value={product}>
                  {product}
                </option>
              ))}
            </select>
          </label>
        )}


        <label>
          Price:
          <input
            type="number"
            name="price"
            value={formData.price}
            onChange={handleChange}
          />
        </label>

        <label>
          Email:
          <input
            type="email"
            name="email"
            value={formData.email}
            onChange={handleChange}
          />
        </label>

        <button type="submit" className={styles.button}>Submit</button>
      </form>
    </div>
  );
};

export default Products;
