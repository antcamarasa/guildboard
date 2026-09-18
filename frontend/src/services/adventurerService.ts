import type { Adventurer, ApiError, CreateAdventurerRequest } from '../types';
// URL de base pour les requêtes API
const BASE_URL = 'http://localhost:8080/api/adventurers';
// Fonction pour récupérer tous les aventuriers
export async function getAdventurers(): Promise<Adventurer[]> {
    const res = await fetch ('${BASE_URL}');
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}
// Fonction pour créer un nouvel aventurier
export async function createAdventurer(
   data: CreateAdventurerRequest
): Promise<Adventurer> {
        const res = await fetch(`${BASE_URL}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data),});

    if (!res.ok) {
       const error: ApiError = await res.json();
       throw error;
    }

  return res.json();
}
// Fonction pour supprimer un aventurier par son ID
export async function deleteAdventurer(id: number):Promise<void> {
    const res = await fetch(`${BASE_URL}/${id}`, {
        method: 'DELETE',
    });
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
}

// Fonction pour récupérer un aventurier par son ID exemple 3 a la fin qui le recupere dans la base de donnée quand on clique sur le bouton
export async function getAdventurerById(id: number): Promise<Adventurer> {
    const res = await fetch(`${BASE_URL}/${id}`);
    if (!res.ok) {
        const error: ApiError = await res.json();
        throw error;
    }
    return res.json();
}


// Fonction pour mettre à jour un aventurier
export async function updateAdventurer(
    id: number,
    data: Partial<Omit<Adventurer, 'id'>>
): Promise<Adventurer> {
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

