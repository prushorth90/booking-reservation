import { Box, Container } from "@mui/material";

import Navbar from "./components/navbar";

import HeroSection from "./components/HeroSection";

import FeatureCards from "./components/FeatureCards";

function App() {

  return (

    <Box sx={{ minHeight: "100vh", bgcolor: "#f7f8fc" }}>

      <Navbar />

      <Container maxWidth="lg">

        <HeroSection />

        <FeatureCards />

      </Container>

    </Box>

  );

}

export default App;