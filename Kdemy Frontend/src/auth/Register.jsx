import { useState } from "react";
import axios from "../api/axios";

function Register() {
  const [form, setForm] = useState({
    email: "",
    password: "",
    username: "",
    phone: "",
    role: "STUDENT",
  });

  const submitHandler = async (e) => {
    e.preventDefault();
    await axios.post("/auth/register", form);
    alert("Registration successful");
  };

  return (
    <form onSubmit={submitHandler}>
      <h2>Register</h2>

      <input
        placeholder="Email"
        onChange={(e) => setForm({ ...form, email: e.target.value })}
      />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setForm({ ...form, password: e.target.value })}
      />
      <input
        placeholder="User name"
        onChange={(e) => setForm({ ...form, username: e.target.value })}
      />
      <input
        placeholder="phone"
        onChange={(e) => setForm({ ...form, phone: e.target.value })}
      />

      <select onChange={(e) => setForm({ ...form, role: e.target.value })}>
        <option value="STUDENT">Student</option>
        <option value="INSTRUCTOR">Instructor</option>
      </select>

      <button type="submit">Register</button>
    </form>
  );
}

export default Register;
