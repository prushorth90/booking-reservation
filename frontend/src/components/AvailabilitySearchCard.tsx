import {
  Box,
  Button,
  Card,
  CardContent,
  MenuItem,
  TextField,
  Typography,
} from "@mui/material";

import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";

function AvailabilitySearchCard() {
  return (
    <Card
      sx={{
        borderRadius: 4,
        boxShadow: "0 20px 50px rgba(20,32,60,0.12)",
      }}
    >
      <CardContent sx={{ p: 4 }}>
        <Box
          sx={{
            display: "flex",
            alignItems: "center",
            gap: 1,
            mb: 3,
          }}
        >
          <CalendarMonthIcon color="primary" />

          <Typography variant="h5" sx={{ fontWeight: 800 }}>
            Find Availability
          </Typography>
        </Box>

        <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
          <TextField
            select
            label="Reservation Type"
            defaultValue="hotel"
            fullWidth
          >
            <MenuItem value="hotel">Hotel Room</MenuItem>
            <MenuItem value="restaurant">Restaurant Table</MenuItem>
            <MenuItem value="appointment">Appointment</MenuItem>
          </TextField>

          <TextField label="Location" defaultValue="Seattle, WA" fullWidth />

          <Box
            sx={{
              display: "grid",
              gridTemplateColumns: { xs: "1fr", sm: "1fr 1fr" },
              gap: 2,
            }}
          >
            <TextField
              label="Start Date"
              type="date"
              defaultValue="2026-07-20"
              fullWidth
              slotProps={{
                inputLabel: {
                  shrink: true,
                },
              }}
            />

            <TextField
              label="End Date"
              type="date"
              defaultValue="2026-07-23"
              fullWidth
              slotProps={{
                inputLabel: {
                  shrink: true,
                },
              }}
            />
          </Box>

          <Button size="large" variant="contained" fullWidth>
            Search
          </Button>
        </Box>
      </CardContent>
    </Card>
  );
}

export default AvailabilitySearchCard;