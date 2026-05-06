import { BrowserRouter, Route, Routes } from "react-router-dom";
import Login from "./auth/Login";
import Register from "./auth/Register";
import Dashboard from "./pages/Dashboard";
import ProtectedRoute from "./routes/Protected";
import Home from "./pages/Home";
import { AuthProvider } from "./auth/AuthContext";
import "./App.css";

function App() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />

          <Route
            path="/instructor"
            element={
              <ProtectedRoute role="INSTRUCTOR">
                <Dashboard />
              </ProtectedRoute>
            }
          />

          <Route path="/" element={<Home />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default App;
