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

export async function createBooking(

  request: CreateBookingRequest

): Promise<BookingResponse> {

  const response = await fetch("http://localhost:8080/api/bookings", {

    method: "POST",

    headers: {

      "Content-Type": "application/json",

    },

    body: JSON.stringify(request),

  });

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to create booking");

  }

  return response.json();

}

export async function getBookings(): Promise<BookingResponse[]> {

  const response = await fetch("http://localhost:8080/api/bookings");

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to load bookings");

  }

  return response.json();

}