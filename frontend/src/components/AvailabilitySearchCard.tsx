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

type AvailabilitySearchCardProps = {
  location: string;
  checkIn: string;
  checkOut: string;
  guests: number;
  onLocationChange: (value: string) => void;
  onCheckInChange: (value: string) => void;
  onCheckOutChange: (value: string) => void;
  onGuestsChange: (value: number) => void;
  onSearch: () => void;
};

function AvailabilitySearchCard({
  location,
  checkIn,
  checkOut,
  guests,
  onLocationChange,
  onCheckInChange,
  onCheckOutChange,
  onGuestsChange,
  onSearch,
}: AvailabilitySearchCardProps) {
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
          <TextField select label="Reservation Type" defaultValue="hotel" fullWidth>
            <MenuItem value="hotel">Hotel Room</MenuItem>
          </TextField>

          <TextField
            label="Location"
            value={location}
            onChange={(event) => onLocationChange(event.target.value)}
            fullWidth
          />

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
              value={checkIn}
              onChange={(event) => onCheckInChange(event.target.value)}
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
              value={checkOut}
              onChange={(event) => onCheckOutChange(event.target.value)}
              fullWidth
              slotProps={{
                inputLabel: {
                  shrink: true,
                },
              }}
            />
          </Box>

          <TextField
            label="Guests"
            type="number"
            value={guests}
            onChange={(event) => onGuestsChange(Number(event.target.value))}
            fullWidth
          />

          <Button size="large" variant="contained" fullWidth onClick={onSearch}>
            Search
          </Button>
        </Box>
      </CardContent>
    </Card>
  );
}

export default AvailabilitySearchCard;