import { useState } from "react";
import { Alert, Box, Container, Typography } from "@mui/material";

import Navbar from "./components/Navbar";
import HeroSection from "./components/HeroSection";
import FeatureCards from "./components/FeatureCards";
import HotelResults from "./components/HotelResults";

import { searchHotels, type HotelSearchResult } from "./api/hotelapi";

function App() {
  const [location, setLocation] = useState("Seattle");
  const [checkIn, setCheckIn] = useState("2026-07-20");
  const [checkOut, setCheckOut] = useState("2026-07-23");
  const [guests, setGuests] = useState(2);

  const [hotels, setHotels] = useState<HotelSearchResult[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");

  async function handleSearch() {
    try {
      setIsLoading(true);
      setErrorMessage("");

      const results = await searchHotels({
        location,
        checkIn,
        checkOut,
        guests,
      });

      setHotels(results);
    } catch {
      setErrorMessage(
        "Could not search hotels. Make sure the Spring Boot backend is running."
      );
    } finally {
      setIsLoading(false);
    }
  }

  return (
    <Box sx={{ minHeight: "100vh", bgcolor: "#f7f8fc" }}>
      <Navbar />

      <Container maxWidth="lg">
        <HeroSection
          location={location}
          checkIn={checkIn}
          checkOut={checkOut}
          guests={guests}
          onLocationChange={setLocation}
          onCheckInChange={setCheckIn}
          onCheckOutChange={setCheckOut}
          onGuestsChange={setGuests}
          onSearch={handleSearch}
        />

        {isLoading && (
          <Typography sx={{ mb: 3 }}>Searching hotels...</Typography>
        )}

        {errorMessage && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {errorMessage}
          </Alert>
        )}

        <HotelResults hotels={hotels} />

        <FeatureCards />
      </Container>
    </Box>
  );
}

export default App;