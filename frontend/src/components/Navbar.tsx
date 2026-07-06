import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";

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

  );

}

export default Navbar;