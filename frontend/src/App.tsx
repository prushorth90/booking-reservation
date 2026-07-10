import { useState } from "react";

import {

  Alert,

  Box,

  Button,

  Container,

  Dialog,

  DialogActions,

  DialogContent,

  DialogTitle,

  Snackbar,

  TextField,

  Typography,

} from "@mui/material";

import Navbar from "./components/Navbar";

import HeroSection from "./components/HeroSection";

import FeatureCards from "./components/FeatureCards";

import HotelResults from "./components/HotelResults";

import { searchHotels, type HotelSearchResult } from "./api/hotelapi";

import { createBooking } from "./api/bookingApi";

function App() {

  const [location, setLocation] = useState("Seattle");

  const [checkIn, setCheckIn] = useState("2026-07-20");

  const [checkOut, setCheckOut] = useState("2026-07-23");

  const [guests, setGuests] = useState(2);

  const [hotels, setHotels] = useState<HotelSearchResult[]>([]);

  const [selectedHotel, setSelectedHotel] = useState<HotelSearchResult | null>(

    null

  );

  const [guestName, setGuestName] = useState("");

  const [isLoading, setIsLoading] = useState(false);

  const [isBooking, setIsBooking] = useState(false);

  const [errorMessage, setErrorMessage] = useState("");

  const [successMessage, setSuccessMessage] = useState("");

  async function handleSearch() {

    try {

      setIsLoading(true);

      setErrorMessage("");

      setSuccessMessage("");

      const results = await searchHotels({

        location,

        checkIn,

        checkOut,

        guests,

      });

      setHotels(results);

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage(

          "Could not search hotels. Make sure the Spring Boot backend is running."

        );

      }

    } finally {

      setIsLoading(false);

    }

  }

  function handleOpenReserveDialog(hotel: HotelSearchResult) {

    setSelectedHotel(hotel);

    setGuestName("");

    setErrorMessage("");

  }

  function handleCloseReserveDialog() {

    setSelectedHotel(null);

    setGuestName("");

  }

  async function handleCreateBooking() {

    if (!selectedHotel) {

      return;

    }

    try {

      setIsBooking(true);

      setErrorMessage("");

      const booking = await createBooking({

        roomId: selectedHotel.roomId,

        guestName,

        checkInDate: checkIn,

        checkOutDate: checkOut,

      });

      setSuccessMessage(

        `Booking confirmed for ${booking.guestName} at ${booking.hotelName}`

      );

      handleCloseReserveDialog();

      const refreshedHotels = await searchHotels({

        location,

        checkIn,

        checkOut,

        guests,

      });

      setHotels(refreshedHotels);

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage("Could not create booking.");

      }

    } finally {

      setIsBooking(false);

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

        <HotelResults hotels={hotels} onReserve={handleOpenReserveDialog} />

        <FeatureCards />

      </Container>

      <Dialog

        open={selectedHotel !== null}

        onClose={handleCloseReserveDialog}

        fullWidth

        maxWidth="sm"

      >

        <DialogTitle>Confirm Reservation</DialogTitle>

        <DialogContent>

          {selectedHotel && (

            <Box sx={{ pt: 1 }}>

              <Typography sx={{ mb: 2 }}>

                You are reserving <strong>{selectedHotel.roomType}</strong> at{" "}

                <strong>{selectedHotel.name}</strong>.

              </Typography>

              <Typography sx={{ mb: 2, color: "text.secondary" }}>

                {checkIn} to {checkOut} · ${selectedHotel.pricePerNight} / night

              </Typography>

              <TextField

                label="Guest Name"

                value={guestName}

                onChange={(event) => setGuestName(event.target.value)}

                fullWidth

              />

            </Box>

          )}

        </DialogContent>

        <DialogActions>

          <Button onClick={handleCloseReserveDialog}>Cancel</Button>

          <Button

            variant="contained"

            onClick={handleCreateBooking}

            disabled={isBooking || guestName.trim().length === 0}

          >

            {isBooking ? "Booking..." : "Confirm Booking"}

          </Button>

        </DialogActions>

      </Dialog>

      <Snackbar

        open={successMessage.length > 0}

        autoHideDuration={4000}

        onClose={() => setSuccessMessage("")}

      >

        <Alert severity="success" onClose={() => setSuccessMessage("")}>

          {successMessage}

        </Alert>

      </Snackbar>

    </Box>

  );

}

export default App;