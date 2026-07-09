import { Box, Button, Chip, Typography } from "@mui/material";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import SearchIcon from "@mui/icons-material/Search";
import AvailabilitySearchCard from "./AvailabilitySearchCard";

type HeroSectionProps = {
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

function HeroSection({
  location,
  checkIn,
  checkOut,
  guests,
  onLocationChange,
  onCheckInChange,
  onCheckOutChange,
  onGuestsChange,
  onSearch,
}: HeroSectionProps) {
  return (
    <Box
      sx={{
        py: { xs: 6, md: 10 },
        display: "grid",
        gridTemplateColumns: { xs: "1fr", md: "7fr 5fr" },
        gap: 6,
        alignItems: "center",
      }}
    >
      <Box>
        <Chip
          icon={<CheckCircleIcon />}
          label="Spring Boot + React booking app"
          color="primary"
          variant="outlined"
          sx={{ mb: 3 }}
        />

        <Typography
          variant="h2"
          sx={{
            fontWeight: 900,
            fontSize: { xs: "2.4rem", md: "4rem" },
            lineHeight: 1.05,
            mb: 3,
          }}
        >
          Book rooms, tables, and appointments in seconds.
        </Typography>

        <Typography
          variant="h6"
          sx={{
            color: "text.secondary",
            mb: 4,
            maxWidth: 650,
          }}
        >
          A full-stack reservation platform built with React, TypeScript,
          Material UI, Java Spring Boot, and PostgreSQL.
        </Typography>

        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", sm: "row" },
            gap: 2,
          }}
        >
          <Button
            size="large"
            variant="contained"
            startIcon={<SearchIcon />}
            onClick={onSearch}
          >
            Search Availability
          </Button>

          <Button size="large" variant="outlined">
            View Demo
          </Button>
        </Box>
      </Box>

      <AvailabilitySearchCard
        location={location}
        checkIn={checkIn}
        checkOut={checkOut}
        guests={guests}
        onLocationChange={onLocationChange}
        onCheckInChange={onCheckInChange}
        onCheckOutChange={onCheckOutChange}
        onGuestsChange={onGuestsChange}
        onSearch={onSearch}
      />
    </Box>
  );
}

export default HeroSection;