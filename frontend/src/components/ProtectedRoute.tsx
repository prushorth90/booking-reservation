 import { Navigate } from "react-router-dom";
import { getSavedAuth } from "../api/authApi";

type ProtectedRouteProps = {
  children: React.ReactNode;
  requiredRole?: "CUSTOMER" | "ADMIN";
};

function ProtectedRoute({ children, requiredRole }: ProtectedRouteProps) {
  const auth = getSavedAuth();

  if (!auth) {
    return <Navigate to="/login" replace />;
  }

  if (requiredRole && auth.role !== requiredRole) {
    return <Navigate to="/hotels" replace />;
  }

  return children;
}

export default ProtectedRoute;