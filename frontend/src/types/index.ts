// Type definitions for the GuildBoard application
export type AdventurerType = 'WARRIOR' | 'MAGE' | 'RANGER' | 'CLERIC';
export type Difficulty     = 'EASY' | 'MEDIUM' | 'HARD' | 'EPIC';
export type QuestStatus    = 'AVAILABLE' | 'IN_PROGRESS' | 'COMPLETED';

export interface Adventurer {
  id: number;
  name: string;
  adventurerType: AdventurerType;
  level: number;
  xp: number;
  gold: number;
}

export interface Quest {
  id: number;
  title: string;
  description: string;
  difficulty: Difficulty;
  status: QuestStatus;
  requiredLevel: number;
  goldReward: number;
  xpReward: number;
}

export interface Assignment {
  id: number;
  adventurer: Adventurer;
  quest: Quest;
  assignedAt: string;
}

export interface CreateAdventurerRequest {
  name: string;
  characterType: AdventurerType;
}

export interface UpdateAdventurerRequest {
  name: string;
  adventurerType: AdventurerType;
  level: number;
  xp: number;
  gold: number;
}

export interface ApiError {
  status: number;
  code: string;
  message: string;
}