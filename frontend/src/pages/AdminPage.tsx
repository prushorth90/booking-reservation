import { useEffect, useState } from "react";

import {

  Alert,

  Box,

  Button,

  Card,

  CardContent,

  Container,

  MenuItem,

  Snackbar,

  TextField,

  Typography,

} from "@mui/material";

import {

  createAdminHotel,

  createAdminRoom,

  getAdminHotels,

  type AdminHotel,

} from "../api/adminApi";

function AdminPage() {

  const [hotels, setHotels] = useState<AdminHotel[]>([]);

  const [hotelName, setHotelName] = useState("");

  const [city, setCity] = useState("Seattle");

  const [address, setAddress] = useState("");

  const [rating, setRating] = useState(4.5);

  const [imageUrl, setImageUrl] = useState("");

  const [selectedHotelId, setSelectedHotelId] = useState<number | "">("");

  const [roomType, setRoomType] = useState("");

  const [capacity, setCapacity] = useState(2);

  const [pricePerNight, setPricePerNight] = useState(150);

  const [errorMessage, setErrorMessage] = useState("");

  const [successMessage, setSuccessMessage] = useState("");

  const [isLoading, setIsLoading] = useState(false);

  async function loadHotels() {

    try {

      setErrorMessage("");

      const results = await getAdminHotels();

      setHotels(results);

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage("Could not load hotels.");

      }

    }

  }

  async function handleCreateHotel() {

    try {

      setIsLoading(true);

      setErrorMessage("");

      setSuccessMessage("");

      const hotel = await createAdminHotel({

        name: hotelName,

        city,

        address,

        rating,

        imageUrl,

      });

      setSuccessMessage(`Created hotel: ${hotel.name}`);

      setHotelName("");

      setAddress("");

      setRating(4.5);

      setImageUrl("");

      await loadHotels();

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage("Could not create hotel.");

      }

    } finally {

      setIsLoading(false);

    }

  }

  async function handleCreateRoom() {

    if (selectedHotelId === "") {

      setErrorMessage("Select a hotel before creating a room.");

      return;

    }

    try {

      setIsLoading(true);

      setErrorMessage("");

      setSuccessMessage("");

      await createAdminRoom(selectedHotelId, {

        roomType,

        capacity,

        pricePerNight,

      });

      setSuccessMessage("Created room successfully.");

      setRoomType("");

      setCapacity(2);

      setPricePerNight(150);

    } catch (error) {

      if (error instanceof Error) {

        setErrorMessage(error.message);

      } else {

        setErrorMessage("Could not create room.");

      }

    } finally {

      setIsLoading(false);

    }

  }

  useEffect(() => {

    loadHotels();

  }, []);

  return (

    <Container maxWidth="lg">

      <Box sx={{ py: { xs: 5, md: 8 } }}>

        <Typography variant="h3" sx={{ fontWeight: 900, mb: 2 }}>

          Admin Dashboard

        </Typography>

        <Typography

          variant="h6"

          sx={{ color: "text.secondary", mb: 4, maxWidth: 750 }}

        >

          Manage hotels, rooms, availability, and customer bookings.

        </Typography>

        {errorMessage && (

          <Alert severity="error" sx={{ mb: 3 }}>

            {errorMessage}

          </Alert>

        )}

        <Box

          sx={{

            display: "grid",

            gridTemplateColumns: { xs: "1fr", md: "1fr 1fr" },

            gap: 3,

            mb: 5,

          }}

        >

          <Card sx={{ borderRadius: 3 }}>

            <CardContent sx={{ p: 4 }}>

              <Typography variant="h5" sx={{ fontWeight: 800, mb: 3 }}>

                Add Hotel

              </Typography>

              <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>

                <TextField

                  label="Hotel Name"

                  value={hotelName}

                  onChange={(event) => setHotelName(event.target.value)}

                  fullWidth

                />

                <TextField

                  label="City"

                  value={city}

                  onChange={(event) => setCity(event.target.value)}

                  fullWidth

                />

                <TextField

                  label="Address"

                  value={address}

                  onChange={(event) => setAddress(event.target.value)}

                  fullWidth

                />

                <TextField

                  label="Rating"

                  type="number"

                  value={rating}

                  onChange={(event) => setRating(Number(event.target.value))}

                  fullWidth

                />

                <TextField

                  label="Image URL"

                  value={imageUrl}

                  onChange={(event) => setImageUrl(event.target.value)}

                  fullWidth

                />

                <Button

                  variant="contained"

                  onClick={handleCreateHotel}

                  disabled={isLoading}

                >

                  Create Hotel

                </Button>

              </Box>

            </CardContent>

          </Card>

          <Card sx={{ borderRadius: 3 }}>

            <CardContent sx={{ p: 4 }}>

              <Typography variant="h5" sx={{ fontWeight: 800, mb: 3 }}>

                Add Room

              </Typography>

              <Box sx={{ display: "flex", flexDirection: "column", gap: 2 }}>

                <TextField

                  select

                  label="Hotel"

                  value={selectedHotelId}

                  onChange={(event) =>

                    setSelectedHotelId(Number(event.target.value))

                  }

                  fullWidth

                >

                  {hotels.map((hotel) => (

                    <MenuItem key={hotel.id} value={hotel.id}>

                      {hotel.name} — {hotel.city}

                    </MenuItem>

                  ))}

                </TextField>

                <TextField

                  label="Room Type"

                  value={roomType}

                  onChange={(event) => setRoomType(event.target.value)}

                  fullWidth

                />

                <TextField

                  label="Capacity"

                  type="number"

                  value={capacity}

                  onChange={(event) => setCapacity(Number(event.target.value))}

                  fullWidth

                />

                <TextField

                  label="Price Per Night"

                  type="number"

                  value={pricePerNight}

                  onChange={(event) =>

                    setPricePerNight(Number(event.target.value))

                  }

                  fullWidth

                />

                <Button

                  variant="contained"

                  onClick={handleCreateRoom}

                  disabled={isLoading}

                >

                  Create Room

                </Button>

              </Box>

            </CardContent>

          </Card>

        </Box>

        <Typography variant="h4" sx={{ fontWeight: 900, mb: 3 }}>

          Existing Hotels

        </Typography>

        <Box

          sx={{

            display: "grid",

            gridTemplateColumns: { xs: "1fr", md: "repeat(3, 1fr)" },

            gap: 3,

          }}

        >

          {hotels.map((hotel) => (

            <Card key={hotel.id} sx={{ borderRadius: 3 }}>

              <CardContent sx={{ p: 3 }}>

                <Typography variant="h6" sx={{ fontWeight: 800 }}>

                  {hotel.name}

                </Typography>

                <Typography sx={{ color: "text.secondary", mb: 1 }}>

                  {hotel.address}, {hotel.city}

                </Typography>

                <Typography>Rating: {hotel.rating}</Typography>

              </CardContent>

            </Card>

          ))}

        </Box>

      </Box>

      <Snackbar

        open={successMessage.length > 0}

        autoHideDuration={4000}

        onClose={() => setSuccessMessage("")}

      >

        <Alert severity="success" onClose={() => setSuccessMessage("")}>

          {successMessage}

        </Alert>

      </Snackbar>

    </Container>

  );

}

export default AdminPage;