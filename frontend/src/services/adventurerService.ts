import type { Adventurer, ApiError, CreateAdventurerRequest } from '../types';

const BASE_URL = 'http://localhost:8080/api/adventurers';

export async function getAdventurers(): Promise<Addventurer[]> {
    const res = await fetch ('${BASE_URL}/adventurers');
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error; 
    }
    return res.json();
}

export async function createAdventurer(
   data: CreateAdventurerRequest
): Promise<Adventurer> {
        const res = await fetch(`${BASE_URL}/adventurers`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),});

    if (!res.ok) {
       const error: ApiError = await res.json();
       throw error;
    }

  return res.json();
}

export async function deletAdventurer(id: number):Promise<void> {
    const res = await fetch(`${BASE_URL}/adventurers/${id}`, {
        method: 'DELETE',
    });
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
}

