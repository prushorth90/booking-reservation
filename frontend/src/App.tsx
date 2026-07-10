import { Box } from "@mui/material";

import { BrowserRouter, Routes, Route } from "react-router-dom";

import Navbar from "./components/Navbar";

import HomePage from "./pages/HomePage";

import HotelSearchPage from "./pages/HotelSearchPage";

import BookingsPage from "./pages/BookingPage";

function App() {

  return (

    <BrowserRouter>

      <Box sx={{ minHeight: "100vh", bgcolor: "#f7f8fc" }}>

        <Navbar />

        <Routes>

          <Route path="/" element={<HomePage />} />

          <Route path="/hotels" element={<HotelSearchPage />} />

          <Route path="/bookings" element={<BookingsPage />} />

        </Routes>

      </Box>

    </BrowserRouter>

  );

}

export default App;