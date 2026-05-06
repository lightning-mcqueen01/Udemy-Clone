import { useState } from "react";
import axios from "../api/axios";
import { useAuth } from "./AuthContext";
import { useNavigate } from "react-router-dom";

function Login() {
  const [form, setForm] = useState({ email: "", password: "" });
  const { login } = useAuth();
  const navigate = useNavigate();

  const submitHandler = async (e) => {
    e.preventDefault();

    const res = await axios.post("/auth/login", form);
    login(res.data.token, res.data.role);
    navigate("/dashboard");
  };

  return (
    <form onSubmit={submitHandler}>
      <h2>Login</h2>

      <input
        placeholder="Email"
        onChange={(e) => setForm({ ...form, email: e.target.value })}
      />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) => setForm({ ...form, password: e.target.value })}
      />

      <button type="submit">Login</button>
    </form>
  );
}

export default Login;
