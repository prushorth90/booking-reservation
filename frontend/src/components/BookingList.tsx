import {

  Box,

  Card,

  CardContent,

  Chip,

  Typography,

} from "@mui/material";

import type { BookingResponse } from "../api/bookingApi";

type BookingListProps = {

  bookings: BookingResponse[];

};

function BookingList({ bookings }: BookingListProps) {

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

        {bookings.map((booking) => (

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

              <Chip label={booking.status} color="success" size="small" />

            </CardContent>

          </Card>

        ))}

      </Box>

    </Box>

  );

}

export default BookingList;