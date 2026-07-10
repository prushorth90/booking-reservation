import {

  Alert,

  Box,

  Button,

  Card,

  CardContent,

  Container,

  TextField,

  Typography,

} from "@mui/material";

function AdminPage() {

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

        <Alert severity="info" sx={{ mb: 4 }}>

          This is the admin dashboard UI. Next, we will connect it to Spring Boot

          admin APIs.

        </Alert>

        <Box

          sx={{

            display: "grid",

            gridTemplateColumns: { xs: "1fr", md: "repeat(3, 1fr)" },

            gap: 3,

            mb: 5,

          }}

        >

          <Card sx={{ borderRadius: 3 }}>

            <CardContent sx={{ p: 3 }}>

              <Typography variant="h5" sx={{ fontWeight: 800 }}>

                Hotels

              </Typography>

              <Typography sx={{ color: "text.secondary", mt: 1 }}>

                View and create hotel listings.

              </Typography>

            </CardContent>

          </Card>

          <Card sx={{ borderRadius: 3 }}>

            <CardContent sx={{ p: 3 }}>

              <Typography variant="h5" sx={{ fontWeight: 800 }}>

                Rooms

              </Typography>

              <Typography sx={{ color: "text.secondary", mt: 1 }}>

                Manage room types, capacity, and price.

              </Typography>

            </CardContent>

          </Card>

          <Card sx={{ borderRadius: 3 }}>

            <CardContent sx={{ p: 3 }}>

              <Typography variant="h5" sx={{ fontWeight: 800 }}>

                Bookings

              </Typography>

              <Typography sx={{ color: "text.secondary", mt: 1 }}>

                Review and cancel customer bookings.

              </Typography>

            </CardContent>

          </Card>

        </Box>

        <Card sx={{ borderRadius: 3 }}>

          <CardContent sx={{ p: 4 }}>

            <Typography variant="h5" sx={{ fontWeight: 800, mb: 3 }}>

              Add Hotel

            </Typography>

            <Box

              sx={{

                display: "grid",

                gridTemplateColumns: { xs: "1fr", md: "1fr 1fr" },

                gap: 2,

              }}

            >

              <TextField label="Hotel Name" fullWidth />

              <TextField label="City" fullWidth />

              <TextField label="Address" fullWidth />

              <TextField label="Rating" type="number" fullWidth />

              <TextField label="Image URL" fullWidth />

            </Box>

            <Button variant="contained" sx={{ mt: 3 }}>

              Create Hotel

            </Button>

          </CardContent>

        </Card>

      </Box>

    </Container>

  );

}

export default AdminPage;