import {
  AppBar,
  Box,
  Button,
  Card,
  CardContent,
  Chip,
  Container,
  MenuItem,
  TextField,
  Toolbar,
  Typography,
} from "@mui/material";

import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import CheckCircleIcon from "@mui/icons-material/CheckCircle";
import HotelIcon from "@mui/icons-material/Hotel";
import SearchIcon from "@mui/icons-material/Search";
import AdminPanelSettingsIcon from "@mui/icons-material/AdminPanelSettings";

function App() {
  return (
    <Box sx={{ minHeight: "100vh", bgcolor: "#f7f8fc" }}>
      <AppBar
        position="static"
        elevation={0}
        sx={{
          bgcolor: "white",
          color: "#172033",
          borderBottom: "1px solid #e8eaf0",
        }}
      >
        <Toolbar
          sx={{
            justifyContent: "space-between",
            px: { xs: 2, md: 6 },
          }}
        >
          <Typography
            variant="h5"
            sx={{ fontWeight: 900, color: "primary.main" }}
          >
            ReserveNow
          </Typography>

          <Box
            sx={{
              display: { xs: "none", md: "flex" },
              gap: 2,
            }}
          >
            <Button color="inherit">Features</Button>
            <Button color="inherit">Bookings</Button>
            <Button color="inherit">Admin</Button>
          </Box>

          <Button variant="contained">Get Started</Button>
        </Toolbar>
      </AppBar>

      <Container maxWidth="lg">
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
              <Button size="large" variant="contained" startIcon={<SearchIcon />}>
                Search Availability
              </Button>

              <Button size="large" variant="outlined">
                View Demo
              </Button>
            </Box>
          </Box>

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
        </Box>

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
              <AdminPanelSettingsIcon
                color="primary"
                sx={{ fontSize: 42, mb: 2 }}
              />

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
      </Container>
    </Box>
  );
}

export default App;