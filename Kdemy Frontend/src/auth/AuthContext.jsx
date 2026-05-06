import { createContext, useContext, useState} from "react";
import {jwtDecode} from "jwt-decode";

const AuthContext = createContext();

export const AuthProvider = ({ children }) => {
  const token = localStorage.getItem("token");
  const user = token ? jwtDecode(token) : null;

  const [authUser, setAuthUser] = useState(user);

  const login = (jwt) => {
    localStorage.setItem("token", jwt);
    setAuthUser(jwtDecode(jwt));
  };

  const logout = () => {
    localStorage.removeItem("token");
    setAuthUser(null);
  };

  return (
    <AuthContext.Provider value={{ user: authUser, login, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
