import { useEffect, useState } from "react";
import { Alert, Box, Button, Container, Snackbar, Typography } from "@mui/material";

import BookingList from "../components/BookingList";
import {
  cancelBooking,
  getBookings,
  type BookingResponse,
} from "../api/bookingApi";

function BookingsPage() {
  const [bookings, setBookings] = useState<BookingResponse[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [errorMessage, setErrorMessage] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  async function handleLoadBookings() {
    try {
      setIsLoading(true);
      setErrorMessage("");

      const results = await getBookings();
      setBookings(results);
    } catch (error) {
      if (error instanceof Error) {
        setErrorMessage(error.message);
      } else {
        setErrorMessage("Could not load bookings.");
      }
    } finally {
      setIsLoading(false);
    }
  }

  async function handleCancelBooking(bookingId: number) {
    try {
      setErrorMessage("");
      setSuccessMessage("");

      const cancelledBooking = await cancelBooking(bookingId);

      setSuccessMessage(
        `Booking cancelled for ${cancelledBooking.guestName} at ${cancelledBooking.hotelName}`
      );

      await handleLoadBookings();
    } catch (error) {
      if (error instanceof Error) {
        setErrorMessage(error.message);
      } else {
        setErrorMessage("Could not cancel booking.");
      }
    }
  }

  useEffect(() => {
    handleLoadBookings();
  }, []);

  return (
    <Container maxWidth="lg">
      <Box sx={{ py: { xs: 5, md: 8 } }}>
        <Typography
          variant="h3"
          sx={{
            fontWeight: 900,
            mb: 2,
          }}
        >
          My Bookings
        </Typography>

        <Typography
          variant="h6"
          sx={{
            color: "text.secondary",
            mb: 4,
            maxWidth: 700,
          }}
        >
          View your confirmed and cancelled reservations.
        </Typography>

        <Button
          variant="outlined"
          onClick={handleLoadBookings}
          sx={{ mb: 4 }}
          disabled={isLoading}
        >
          {isLoading ? "Loading..." : "Refresh Bookings"}
        </Button>

        {errorMessage && (
          <Alert severity="error" sx={{ mb: 3 }}>
            {errorMessage}
          </Alert>
        )}

        {isLoading && (
          <Typography sx={{ mb: 3 }}>Loading bookings...</Typography>
        )}

        {!isLoading && bookings.length === 0 && (
          <Alert severity="info" sx={{ mb: 3 }}>
            You do not have any bookings yet.
          </Alert>
        )}

        <BookingList
          bookings={bookings}
          onCancelBooking={handleCancelBooking}
        />
      </Box>

      <Snackbar
        open={successMessage.length > 0}
        autoHideDuration={4000}
        onClose={() => setSuccessMessage("")}
      >
        <Alert severity="success" onClose={() => setSuccessMessage("")}>
          {successMessage}
        </Alert>
      </Snackbar>
    </Container>
  );
}

export default BookingsPage;