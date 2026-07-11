import { useState } from "react";
import { Alert, Box, Button, Card, CardContent, Container, TextField, Typography } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { register, saveAuth } from "../api/authApi";

function RegisterPage() {
  const navigate = useNavigate();

  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [errorMessage, setErrorMessage] = useState("");

  async function handleRegister() {
    try {
      setErrorMessage("");

      const auth = await register({
        name,
        email,
        password,
      });

      saveAuth(auth);
      navigate("/hotels");
    } catch (error) {
      if (error instanceof Error) {
        setErrorMessage(error.message);
      } else {
        setErrorMessage("Could not register.");
      }
    }
  }

  return (
    <Container maxWidth="sm">
      <Box sx={{ py: { xs: 5, md: 8 } }}>
        <Card sx={{ borderRadius: 3 }}>
          <CardContent sx={{ p: 4 }}>
            <Typography variant="h4" sx={{ fontWeight: 900, mb: 3 }}>
              Register
            </Typography>

            {errorMessage && (
              <Alert severity="error" sx={{ mb: 3 }}>
                {errorMessage}
              </Alert>
            )}

            <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>
              <TextField
                label="Name"
                value={name}
                onChange={(event) => setName(event.target.value)}
                fullWidth
              />

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

              <Button variant="contained" onClick={handleRegister}>
                Register
              </Button>
            </Box>
          </CardContent>
        </Card>
      </Box>
    </Container>
  );
}

export default RegisterPage;