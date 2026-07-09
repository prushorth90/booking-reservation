export type HotelSearchResult = {

  id: number;

  name: string;

  city: string;

  address: string;

  rating: number;

  pricePerNight: number;

  availableRooms: number;

  imageUrl: string;

};

export type HotelSearchParams = {

  location: string;

  checkIn: string;

  checkOut: string;

  guests: number;

};

export async function searchHotels(

  params: HotelSearchParams

): Promise<HotelSearchResult[]> {

  const queryParams = new URLSearchParams({

    location: params.location,

    checkIn: params.checkIn,

    checkOut: params.checkOut,

    guests: String(params.guests),

  });

  const response = await fetch(

    `http://localhost:8080/api/hotels/search?${queryParams.toString()}`

  );

   if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to search hotels");

  }

  return response.json();

}