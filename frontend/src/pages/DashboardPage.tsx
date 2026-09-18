import type { Adventurer, Quest } from '../types';
import styles from './DashboardPage.module.css';

// ── Données fictives (en attendant la DB d'Antoine) ──────────────────────────
const FAKE_ADVENTURERS: Adventurer[] = [
  { id: 1, name: 'Grimbold le Prudent',  adventurerType: 'WARRIOR', level: 7, xp: 420, gold: 3240 },
  { id: 2, name: 'Lyrawen Sombre',       adventurerType: 'MAGE',    level: 6, xp: 540, gold: 2100 },
  { id: 3, name: "Fenwick l'Invisible",  adventurerType: 'RANGER',  level: 5, xp: 210, gold: 1860 },
  { id: 4, name: 'Sœur Marvyn',          adventurerType: 'CLERIC',  level: 5, xp: 100, gold: 1640 },
  { id: 5, name: 'Drakh-Baine',          adventurerType: 'WARRIOR', level: 4, xp: 230, gold: 980  },
];

const FAKE_QUESTS: Quest[] = [
  { id: 1, title: 'La Crypte Oubliée',   description: '', difficulty: 'HARD',   status: 'ON_GOING',  minLevel: 5, goldReward: 800,  xpReward: 300 },
  { id: 2, title: 'Escorte du Marchand', description: '', difficulty: 'EASY',   status: 'AVAILABLE', minLevel: 1, goldReward: 150,  xpReward: 80  },
  { id: 3, title: 'Le Dragon de Givre',  description: '', difficulty: 'EPIC',   status: 'AVAILABLE', minLevel: 8, goldReward: 2000, xpReward: 900 },
  { id: 4, title: 'Rats des Égouts',     description: '', difficulty: 'EASY',   status: 'COMPLETED', minLevel: 1, goldReward: 50,   xpReward: 30  },
  { id: 5, title: 'Tour du Nécromant',   description: '', difficulty: 'MEDIUM', status: 'ON_GOING',  minLevel: 3, goldReward: 400,  xpReward: 180 },
];

// ── Libellés lisibles pour le statut ─────────────────────────────────────────
const STATUS_LABEL: Record<Quest['status'], string> = {
  AVAILABLE: 'Disponible',
  ON_GOING:  'En cours',
  COMPLETED: 'Terminée',
};

// ── Composant principal ───────────────────────────────────────────────────────
export default function DashboardPage() {
  // Calculs des statistiques
  const activeQuests    = FAKE_QUESTS.filter(q => q.status === 'ON_GOING').length;
  const completedQuests = FAKE_QUESTS.filter(q => q.status === 'COMPLETED').length;

  // Top aventurier : d'abord par niveau, puis par XP en cas d'égalité
  const topAdventurer = [...FAKE_ADVENTURERS].sort(
    (a, b) => b.level - a.level || b.xp - a.xp
  )[0];

  // Leaderboard : les 5 aventuriers triés par niveau puis XP
  const leaderboard = [...FAKE_ADVENTURERS].sort(
    (a, b) => b.level - a.level || b.xp - a.xp
  );

  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Tableau de bord</h1>

      {/* ── 4 cartes de statistiques ── */}
      <div className={styles.statsGrid}>

        <div className={styles.statCard}>
          <span className={styles.statLabel}>Aventuriers</span>
          <span className={`${styles.statValue} ${styles.colorAdventurers}`}>
            {FAKE_ADVENTURERS.length}
          </span>
          <span className={styles.statSub}>membres de la guilde</span>
        </div>
 
        <div className={styles.statCard}>
          <span className={styles.statLabel}>Quêtes actives</span>
          <span className={`${styles.statValue} ${styles.colorActive}`}>
            {activeQuests}
          </span>
          <span className={styles.statSub}>en cours en ce moment</span>
        </div>
 
        <div className={styles.statCard}>
          <span className={styles.statLabel}>Quêtes terminées</span>
          <span className={`${styles.statValue} ${styles.colorCompleted}`}>
            {completedQuests}
          </span>
          <span className={styles.statSub}>missions accomplies</span>
        </div>
 
        <div className={styles.statCard}>
          <span className={styles.statLabel}>Meilleur aventurier</span>
          <span className={`${styles.statValue} ${styles.colorTop}`} style={{ fontSize: '1.4rem' }}>
            {topAdventurer.name}
          </span>
          <span className={styles.statSub}>
            {topAdventurer.adventurerType} · Niv. {topAdventurer.level}
          </span>
        </div>

      </div>

      {/* ── Section du bas : leaderboard + quêtes récentes ── */}
      <div className={styles.bottomGrid}>

        {/* Leaderboard */}
        <div className={styles.panel}>
          <h2 className={styles.panelTitle}>🏆 Classement</h2>
          <ul className={styles.leaderList}>
            {leaderboard.map((adv, index) => {
              const rankClass =
                index === 0 ? styles.rank1 :
                index === 1 ? styles.rank2 :
                index === 2 ? styles.rank3 :
                styles.rankOther;

              return (
                <li key={adv.id} className={styles.leaderItem}>
                  <span className={`${styles.rank} ${rankClass}`}>
                    {index + 1}
                  </span>
                  <span className={styles.leaderName}>{adv.name}</span>
                  <span className={`${styles.leaderClass} ${styles[adv.adventurerType]}`}>
                    {adv.adventurerType}
                  </span>
                  <span className={styles.leaderLevel}>Niv. {adv.level}</span>
                </li>
              );
            })}
          </ul>
        </div>

        {/* Quêtes récentes */}
        <div className={styles.panel}>
          <h2 className={styles.panelTitle}>📜 Quêtes récentes</h2>
          <ul className={styles.questList}>
            {FAKE_QUESTS.map(quest => (
              <li key={quest.id} className={styles.questItem}>
                <span className={styles.questTitle}>{quest.title}</span>
                <span className={`${styles.difficulty} ${styles[quest.difficulty]}`}>
                  {quest.difficulty}
                </span>
                <span className={styles.status}>
                  {STATUS_LABEL[quest.status]}
                </span>
              </li>
            ))}
          </ul>
        </div>

      </div>
    </div>
  );
}