import { Box, Button, Container, Typography } from "@mui/material";
import { Link as RouterLink } from "react-router-dom";
import FeatureCards from "../components/FeatureCards";

function HomePage() {
  return (
    <Container maxWidth="lg">
      <Box sx={{ py: { xs: 6, md: 10 } }}>
        <Typography
          variant="h2"
          sx={{
            fontWeight: 900,
            fontSize: { xs: "2.5rem", md: "4rem" },
            lineHeight: 1.05,
            mb: 3,
          }}
        >
          Book rooms, tables, and appointments in seconds.
        </Typography>

        <Typography
          variant="h6"
          sx={{ color: "text.secondary", mb: 4, maxWidth: 700 }}
        >
          A full-stack booking platform built with React, TypeScript, Material
          UI, Spring Boot, and PostgreSQL.
        </Typography>

        <Button
          component={RouterLink}
          to="/hotels"
          size="large"
          variant="contained"
        >
          Search Hotels
        </Button>
      </Box>

      <FeatureCards />
    </Container>
  );
}

export default HomePage;