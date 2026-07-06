import { Box, Card, CardContent, Typography } from "@mui/material";

import HotelIcon from "@mui/icons-material/Hotel";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import AdminPanelSettingsIcon from "@mui/icons-material/AdminPanelSettings";

function FeatureCards() {
  return (
    <Box
      sx={{
        pb: 8,
        display: "grid",
        gridTemplateColumns: { xs: "1fr", md: "repeat(3, 1fr)" },
        gap: 3,
      }}
    >
      <Card sx={{ height: "100%", borderRadius: 3 }}>
        <CardContent sx={{ p: 3 }}>
          <HotelIcon color="primary" sx={{ fontSize: 42, mb: 2 }} />

          <Typography variant="h6" sx={{ fontWeight: 800, mb: 1 }}>
            Real-time availability
          </Typography>

          <Typography sx={{ color: "text.secondary" }}>
            Users can search available rooms, tables, or time slots before
            making a reservation.
          </Typography>
        </CardContent>
      </Card>

      <Card sx={{ height: "100%", borderRadius: 3 }}>
        <CardContent sx={{ p: 3 }}>
          <CalendarMonthIcon color="primary" sx={{ fontSize: 42, mb: 2 }} />

          <Typography variant="h6" sx={{ fontWeight: 800, mb: 1 }}>
            Booking management
          </Typography>

          <Typography sx={{ color: "text.secondary" }}>
            Customers can create, view, update, and cancel their bookings
            through the app.
          </Typography>
        </CardContent>
      </Card>

      <Card sx={{ height: "100%", borderRadius: 3 }}>
        <CardContent sx={{ p: 3 }}>
          <AdminPanelSettingsIcon color="primary" sx={{ fontSize: 42, mb: 2 }} />

          <Typography variant="h6" sx={{ fontWeight: 800, mb: 1 }}>
            Admin dashboard
          </Typography>

          <Typography sx={{ color: "text.secondary" }}>
            Admins can manage reservations, customers, inventory, and
            availability rules.
          </Typography>
        </CardContent>
      </Card>
    </Box>
  );
}

export default FeatureCards;