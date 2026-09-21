import type { Adventurer, ApiError, CreateAdventurerRequest } from '../types';

const BASE_URL = 'http://localhost:8080/adventurers';

export async function getAllAdventurers(): Promise<Adventurer[]> {
    const res = await fetch(`${BASE_URL}`);
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}

export async function getAdventurerById(id: number): Promise<Adventurer> {
    const res = await fetch(`${BASE_URL}/${id}`);
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}

export async function createAdventurer(data: CreateAdventurerRequest): Promise<Adventurer> {
    const res = await fetch(`${BASE_URL}`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    });
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}

export async function updateAdventurer(id: number, data: Partial<Omit<Adventurer, 'id'>>): Promise<Adventurer> {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
    });
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}

export async function deleteAdventurer(id: number): Promise<void> {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE',
    });
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
}