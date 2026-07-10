import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";

import { Link as RouterLink } from "react-router-dom";

function Navbar() {

  return (

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

          component={RouterLink}

          to="/"

          variant="h5"

          sx={{

            fontWeight: 900,

            color: "primary.main",

            textDecoration: "none",

          }}

        >

          ReserveNow

        </Typography>

        <Box

          sx={{

            display: { xs: "none", md: "flex" },

            gap: 2,

          }}

        >

          <Button component={RouterLink} to="/" color="inherit">

            Home

          </Button>

          <Button component={RouterLink} to="/hotels" color="inherit">

            Hotels

          </Button>

          <Button component={RouterLink} to="/bookings" color="inherit">

            My Bookings

          </Button>

        </Box>

        <Button component={RouterLink} to="/hotels" variant="contained">

          Get Started

        </Button>

      </Toolbar>

    </AppBar>

  );

}

export default Navbar;