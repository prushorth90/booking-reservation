 import {

  Box,

  Button,

  Card,

  CardContent,

  Chip,

  Typography,

} from "@mui/material";

import type { BookingResponse } from "../api/bookingApi";

type BookingListProps = {

  bookings: BookingResponse[];

  onCancelBooking: (bookingId: number) => void;

};

function BookingList({ bookings, onCancelBooking }: BookingListProps) {

  if (bookings.length === 0) {

    return null;

  }

  return (

    <Box sx={{ pb: 8 }}>

      <Typography variant="h4" sx={{ fontWeight: 900, mb: 3 }}>

        My Bookings

      </Typography>

      <Box

        sx={{

          display: "grid",

          gridTemplateColumns: { xs: "1fr", md: "repeat(3, 1fr)" },

          gap: 3,

        }}

      >

        {bookings.map((booking) => {

          const isCancelled = booking.status === "CANCELLED";

          return (

            <Card key={booking.id} sx={{ borderRadius: 3 }}>

              <CardContent sx={{ p: 3 }}>

                <Typography variant="h6" sx={{ fontWeight: 800 }}>

                  {booking.hotelName}

                </Typography>

                <Typography sx={{ color: "text.secondary", mb: 1 }}>

                  {booking.roomType}

                </Typography>

                <Typography sx={{ mb: 1 }}>

                  Guest: {booking.guestName}

                </Typography>

                <Typography sx={{ mb: 2 }}>

                  {booking.checkInDate} to {booking.checkOutDate}

                </Typography>

                <Chip

                  label={booking.status}

                  color={isCancelled ? "default" : "success"}

                  size="small"

                  sx={{ mb: 2 }}

                />

                {!isCancelled && (

                  <Button

                    variant="outlined"

                    color="error"

                    fullWidth

                    onClick={() => onCancelBooking(booking.id)}

                  >

                    Cancel Booking

                  </Button>

                )}

              </CardContent>

            </Card>

          );

        })}

      </Box>

    </Box>

  );

}

export default BookingList;