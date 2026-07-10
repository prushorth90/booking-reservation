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

export async function getAdminHotels(): Promise<AdminHotel[]> {

  const response = await fetch("http://localhost:8080/api/admin/hotels");

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

    headers: {

      "Content-Type": "application/json",

    },

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

      headers: {

        "Content-Type": "application/json",

      },

      body: JSON.stringify(request),

    }

  );

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to create room");

  }

  return response.json();

}