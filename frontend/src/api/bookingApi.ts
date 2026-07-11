import { getAuthToken } from "./authApi";

export type CreateBookingRequest = {
  roomId: number;
  guestName: string;
  checkInDate: string;
  checkOutDate: string;
};

export type BookingResponse = {
  id: number;
  guestName: string;
  hotelName: string;
  roomType: string;
  checkInDate: string;
  checkOutDate: string;
  status: string;
};

function getAuthHeaders() {
  const token = getAuthToken();

  return {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
  };
}

export async function createBooking(
  request: CreateBookingRequest
): Promise<BookingResponse> {
  const response = await fetch("http://localhost:8080/api/bookings", {
    method: "POST",
    headers: getAuthHeaders(),
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to create booking");
  }

  return response.json();
}

export async function getBookings(): Promise<BookingResponse[]> {
  const response = await fetch("http://localhost:8080/api/bookings", {
    headers: getAuthHeaders(),
  });

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to load bookings");
  }

  return response.json();
}

export async function cancelBooking(
  bookingId: number
): Promise<BookingResponse> {
  const response = await fetch(
    `http://localhost:8080/api/bookings/${bookingId}/cancel`,
    {
      method: "PATCH",
      headers: getAuthHeaders(),
    }
  );

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to cancel booking");
  }

  return response.json();
}