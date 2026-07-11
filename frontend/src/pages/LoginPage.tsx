import { useState } from "react";

import { Alert, Box, Button, Card, CardContent, Container, TextField, Typography } from "@mui/material";

import { useNavigate } from "react-router-dom";

import { login, saveAuth } from "../api/authApi";

function LoginPage() {

  const navigate = useNavigate();

  const [email, setEmail] = useState("solan2@example.com");

  const [password, setPassword] = useState("password123");

  const [errorMessage, setErrorMessage] = useState("");

  async function handleLogin() {

    try {

      setErrorMessage("");

      const auth = await login({

        email,

        password,

      });

      saveAuth(auth);
      window.dispatchEvent(new Event("auth-changed"));
      
      if (auth.role === "ADMIN") {

        navigate("/admin");

      } else {

        navigate("/hotels");

      }

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage("Could not login.");

      }

    }

  }

  return (

    <Container maxWidth="sm">

      <Box sx={{ py: { xs: 5, md: 8 } }}>

        <Card sx={{ borderRadius: 3 }}>

          <CardContent sx={{ p: 4 }}>

            <Typography variant="h4" sx={{ fontWeight: 900, mb: 3 }}>

              Login

            </Typography>

            {errorMessage && (

              <Alert severity="error" sx={{ mb: 3 }}>

                {errorMessage}

              </Alert>

            )}

            <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>

              <TextField

                label="Email"

                value={email}

                onChange={(event) => setEmail(event.target.value)}

                fullWidth

              />

              <TextField

                label="Password"

                type="password"

                value={password}

                onChange={(event) => setPassword(event.target.value)}

                fullWidth

              />

              <Button variant="contained" onClick={handleLogin}>

                Login

              </Button>

            </Box>

          </CardContent>

        </Card>

      </Box>

    </Container>

  );

}

export default LoginPage;