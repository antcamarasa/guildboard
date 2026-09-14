export type AdventurerType = 'warrior' | 'mage' | 'ranger' | 'cleric';
export Difficulty = 'easy' | 'medium' | 'hard' | 'epic';
export type QuestStatus = 'available' | 'on_going' | 'completed';

//entité principale 

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
    minLevel: number;
    goldReward: number;
    xpReward: number;
}
export interface Assignement {
    id: number;
    adventurer: Adventurer;
    quest: Quest;
    assignedAt: string;
}
export interface CreateAdventurerRequest {
  name: string;
  characterType: AdventurerType; // ← le backend attend CE nom exact
}
export interface UpdateAdventurerRequest {
  name: string;
  adventurerType: AdventurerType;
  level: number;
  xp: number;
  gold: number;
}

//erreur api 

export interface ApiError {
    status: number;
    code: string;
    message: string;
}
