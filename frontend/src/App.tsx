import { Box } from "@mui/material";
import { BrowserRouter, Route, Routes } from "react-router-dom";

import Navbar from "./components/Navbar";
import ProtectedRoute from "./components/ProtectedRoute";

import HomePage from "./pages/HomePage";
import HotelSearchPage from "./pages/HotelSearchPage";
import BookingsPage from "./pages/BookingPage";
import AdminPage from "./pages/AdminPage";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";

function App() {
  return (
    <BrowserRouter>
      <Box sx={{ minHeight: "100vh", bgcolor: "#f7f8fc" }}>
        <Navbar />

        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/hotels" element={<HotelSearchPage />} />
          <Route path="/login" element={<LoginPage />} />
          <Route path="/register" element={<RegisterPage />} />

          <Route
            path="/bookings"
            element={
              <ProtectedRoute>
                <BookingsPage />
              </ProtectedRoute>
            }
          />

          <Route
            path="/admin"
            element={
              <ProtectedRoute requiredRole="ADMIN">
                <AdminPage />
              </ProtectedRoute>
            }
          />
        </Routes>
      </Box>
    </BrowserRouter>
  );
}

export default App;