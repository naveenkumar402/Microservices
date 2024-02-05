import React ,{useState} from "react";
import axios from "axios";
import './Customer.css';
const Customers = () => {
    const [formData, setFormData] = useState({
        name: '',
        age: '',
        mobile: '',
        email: '',
        password: '',
        country: '',
        state: '',
        zipcode: ''
    });
    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
    
        try {
          const apiUrl = 'http://localhost:8081/customerservice/registercustomer';
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
        <h2>Enter Your Details</h2>
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="name">Name:</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="age">Age:</label>
                <input
                    type="number"
                    id="age"
                    name="age"
                    value={formData.age}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="mobile">Mobile:</label>
                <input
                    type="tel"
                    id="mobile"
                    name="mobile"
                    value={formData.mobile}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="email">Email:</label>
                <input
                    type="email"
                    id="email"
                    name="email"
                    value={formData.email}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="password">Password:</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    value={formData.password}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="country">Country:</label>
                <input
                    type="text"
                    id="country"
                    name="country"
                    value={formData.country}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="state">State:</label>
                <input
                    type="text"
                    id="state"
                    name="state"
                    value={formData.state}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
                <label htmlFor="zipcode">Zipcode:</label>
                <input
                    type="text"
                    id="zipcode"
                    name="zipcode"
                    value={formData.zipcode}
                    onChange={handleChange}
                    required
                />
            </div>
            <button type="submit">Sign Up</button>
        </form>
    </div>
    );
}
export default Customers;