import { getAuthToken } from "./authApi";
import type { BookingResponse } from "./bookingApi";

export type AdminHotel = {
  id: number;
  name: string;
  city: string;
  address: string;
  rating: number;
  pricePerNight: number;
  availableRooms: number;
  imageUrl: string;
};

export type CreateHotelRequest = {
  name: string;
  city: string;
  address: string;
  rating: number;
  imageUrl: string;
};

export type CreateRoomRequest = {
  roomType: string;
  capacity: number;
  pricePerNight: number;
};

function getAuthHeaders() {
  const token = getAuthToken();

  return {
    "Content-Type": "application/json",
    ...(token ? { Authorization: `Bearer ${token}` } : {}),
  };
}

export async function getAdminHotels(): Promise<AdminHotel[]> {
  const response = await fetch("http://localhost:8080/api/admin/hotels", {
    headers: getAuthHeaders(),
  });

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to load hotels");
  }

  return response.json();
}

export async function createAdminHotel(
  request: CreateHotelRequest
): Promise<AdminHotel> {
  const response = await fetch("http://localhost:8080/api/admin/hotels", {
    method: "POST",
    headers: getAuthHeaders(),
    body: JSON.stringify(request),
  });

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to create hotel");
  }

  return response.json();
}

export async function createAdminRoom(
  hotelId: number,
  request: CreateRoomRequest
): Promise<unknown> {
  const response = await fetch(
    `http://localhost:8080/api/admin/hotels/${hotelId}/rooms`,
    {
      method: "POST",
      headers: getAuthHeaders(),
      body: JSON.stringify(request),
    }
  );

  if (!response.ok) {
    const errorMessage = await response.text();
    throw new Error(errorMessage || "Failed to create room");
  }

  return response.json();
}

export async function getAdminBookings(): Promise<BookingResponse[]> {

  const response = await fetch("http://localhost:8080/api/admin/bookings", {

    headers: getAuthHeaders(),

  });

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to load admin bookings");

  }

  return response.json();

}
 