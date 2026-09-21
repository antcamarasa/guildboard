import type { Quest, ApiError } from '../types';
// Service functions for interacting with the backend API related to quests

const BASE_URL = 'http://localhost:8080';

export async function getAllQuests(): Promise<Quest[]> {
  const res = await fetch(`${BASE_URL}/quests`);
  if (!res.ok) {
    const err: ApiError = await res.json();
    throw err;
  }
  return res.json();
}

export async function getQuestById(id: number): Promise<Quest> {
  const res = await fetch(`${BASE_URL}/quests/${id}`);
  if (!res.ok) {
    const err: ApiError = await res.json();
    throw err;
  }
  return res.json();
}

export async function createQuest(
  data: Omit<Quest, 'id'>
): Promise<Quest> {
  const res = await fetch(`${BASE_URL}/quests`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) {
    const err: ApiError = await res.json();
    throw err;
  }
  return res.json();
}

export async function deleteQuest(id: number): Promise<void> {
  const res = await fetch(`${BASE_URL}/quests/${id}`, {
    method: 'DELETE',
  });
  if (!res.ok) {
    const err: ApiError = await res.json();
    throw err;
  }
}