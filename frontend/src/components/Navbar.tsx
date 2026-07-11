import { useEffect, useState } from "react";

import { AppBar, Box, Button, Toolbar, Typography } from "@mui/material";

import { Link as RouterLink, useNavigate } from "react-router-dom";

import {

  clearSavedAuth,

  getSavedAuth,

  type AuthResponse,

} from "../api/authApi";

function Navbar() {

  const navigate = useNavigate();

  const [auth, setAuth] = useState<AuthResponse | null>(getSavedAuth());

  useEffect(() => {

    function handleStorageChange() {

      setAuth(getSavedAuth());

    }

    window.addEventListener("storage", handleStorageChange);

    window.addEventListener("auth-changed", handleStorageChange);

    return () => {

      window.removeEventListener("storage", handleStorageChange);

      window.removeEventListener("auth-changed", handleStorageChange);

    };

  }, []);

  function handleLogout() {

    clearSavedAuth();

    setAuth(null);

    window.dispatchEvent(new Event("auth-changed"));

    navigate("/login");

  }

  const isLoggedIn = auth !== null;

  const isAdmin = auth?.role === "ADMIN";

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

            alignItems: "center",

          }}

        >

          <Button component={RouterLink} to="/" color="inherit">

            Home

          </Button>

          <Button component={RouterLink} to="/hotels" color="inherit">

            Hotels

          </Button>

          {isLoggedIn && (

            <Button component={RouterLink} to="/bookings" color="inherit">

              My Bookings

            </Button>

          )}

          {isAdmin && (

            <Button component={RouterLink} to="/admin" color="inherit">

              Admin

            </Button>

          )}

        </Box>

        <Box sx={{ display: "flex", gap: 1, alignItems: "center" }}>

          {!isLoggedIn ? (

            <>

              <Button component={RouterLink} to="/login" color="inherit">

                Login

              </Button>

              <Button component={RouterLink} to="/register" variant="contained">

                Register

              </Button>

            </>

          ) : (

            <>

              <Typography

                sx={{

                  display: { xs: "none", md: "block" },

                  color: "text.secondary",

                  fontSize: 14,

                }}

              >

                {auth.name} · {auth.role}

              </Typography>

              <Button variant="outlined" onClick={handleLogout}>

                Logout

              </Button>

            </>

          )}

        </Box>

      </Toolbar>

    </AppBar>

  );

}

export default Navbar;