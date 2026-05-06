import { useAuth } from "../auth/AuthContext";

function Dashboard() {
  const { user, logout } = useAuth();

  return (
    <div>
      <h2>Dashboard</h2>
      <p>Role: {user.role}</p>

      {user.role === "INSTRUCTOR" && <p>Instructor Panel</p>}
      {user.role === "STUDENT" && <p>Student Panel</p>}
      {user.role === "ADMIN" && <p>Admin Panel</p>}

      <button onClick={logout}>Logout</button>
    </div>
  );
}

export default Dashboard;
