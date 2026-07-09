import {
  Box,
  Card,
  CardContent,
  CardMedia,
  Chip,
  Typography,
} from "@mui/material";

import type { HotelSearchResult } from "../api/hotelapi";

type HotelResultsProps = {
  hotels: HotelSearchResult[];
};

function HotelResults({ hotels }: HotelResultsProps) {
  if (hotels.length === 0) {
    return null;
  }

  return (
    <Box sx={{ pb: 8 }}>
      <Typography variant="h4" sx={{ fontWeight: 900, mb: 3 }}>
        Available Hotels
      </Typography>

      <Box
        sx={{
          display: "grid",
          gridTemplateColumns: { xs: "1fr", md: "repeat(3, 1fr)" },
          gap: 3,
        }}
      >
        {hotels.map((hotel) => (
          <Card key={hotel.id} sx={{ borderRadius: 3, overflow: "hidden" }}>
            <CardMedia
              component="img"
              height="180"
              image={hotel.imageUrl}
              alt={hotel.name}
            />

            <CardContent sx={{ p: 3 }}>
              <Typography variant="h6" sx={{ fontWeight: 800 }}>
                {hotel.name}
              </Typography>

              <Typography sx={{ color: "text.secondary", mb: 1 }}>
                {hotel.address}, {hotel.city}
              </Typography>

              <Box sx={{ display: "flex", gap: 1, mb: 2 }}>
                <Chip label={`${hotel.rating} stars`} size="small" />
                <Chip
                  label={`${hotel.availableRooms} rooms left`}
                  size="small"
                  color="success"
                />
              </Box>

              <Typography variant="h6" sx={{ fontWeight: 900 }}>
                ${hotel.pricePerNight} / night
              </Typography>
            </CardContent>
          </Card>
        ))}
      </Box>
    </Box>
  );
}

export default HotelResults;