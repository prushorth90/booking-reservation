export type AuthResponse = {

  token: string;

  userId: number;

  name: string;

  email: string;

  role: "CUSTOMER" | "ADMIN";

};

export type LoginRequest = {

  email: string;

  password: string;

};

export type RegisterRequest = {

  name: string;

  email: string;

  password: string;

};

const AUTH_STORAGE_KEY = "booking_app_auth";

export async function login(request: LoginRequest): Promise<AuthResponse> {

  const response = await fetch("http://localhost:8080/api/auth/login", {

    method: "POST",

    headers: {

      "Content-Type": "application/json",

    },

    body: JSON.stringify(request),

  });

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to login");

  }

  return response.json();

}

export async function register(

  request: RegisterRequest

): Promise<AuthResponse> {

  const response = await fetch("http://localhost:8080/api/auth/register", {

    method: "POST",

    headers: {

      "Content-Type": "application/json",

    },

    body: JSON.stringify(request),

  });

  if (!response.ok) {

    const errorMessage = await response.text();

    throw new Error(errorMessage || "Failed to register");

  }

  return response.json();

}

export function saveAuth(auth: AuthResponse) {

  localStorage.setItem(AUTH_STORAGE_KEY, JSON.stringify(auth));

}

export function getSavedAuth(): AuthResponse | null {

  const rawAuth = localStorage.getItem(AUTH_STORAGE_KEY);

  if (!rawAuth) {

    return null;

  }

  return JSON.parse(rawAuth) as AuthResponse;

}

export function clearSavedAuth() {

  localStorage.removeItem(AUTH_STORAGE_KEY);

}

export function getAuthToken(): string | null {

  return getSavedAuth()?.token ?? null;

}