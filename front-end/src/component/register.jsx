import React, { useState } from "react";
import "./register.css";

const Register = () => {
  const [username, setUsername] = useState("");
  const [phone, setPhone] = useState("");
  const [passwordHash, setPasswordHash] = useState("");
  const [message, setMessage] = useState("");

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:8080/api/users/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, phone, passwordHash }),
      });

      if (response.ok) {
        setMessage("✅ Registration successful! You can login now.");
        setUsername("");
        setPhone("");
        setPasswordHash("");
      } else {
        setMessage("❌ Registration failed. Try again.");
      }
    } catch (error) {
      console.error("Register failed", error);
      setMessage("❌ Error registering user.");
    }
  };

  return (
    <div className="register-container">
      <div className="register-card">
        <h2 className="register-title">Create an Account</h2>
        <form onSubmit={handleRegister} className="register-form">
          <div className="input-group">
            <label>Username</label>
            <input
              type="text"
              placeholder="Enter username"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label>Phone</label>
            <input
              type="text"
              placeholder="Enter phone number"
              value={phone}
              onChange={(e) => setPhone(e.target.value)}
              required
            />
          </div>

          <div className="input-group">
            <label>Password</label>
            <input
              type="password"
              placeholder="Enter password"
              value={passwordHash}
              onChange={(e) => setPasswordHash(e.target.value)}
              required
            />
          </div>

          <button type="submit" className="register-btn">
            Register
          </button>
        </form>

        {message && <p className="register-message">{message}</p>}
      </div>
    </div>
  );
};

export default Register;
