import { useState, useEffect } from 'react';
import type { Adventurer, Quest } from '../types';
import { getAllAdventurers } from '../services/adventurerService';
import { getAllQuests } from '../services/questService';
import styles from './DashboardPage.module.css';

// ── Libellés lisibles pour le statut ─────────────────────────────────────────
const STATUS_LABEL: Record<Quest['status'], string> = {
  AVAILABLE:   'Disponible',
  IN_PROGRESS: 'En cours',
  COMPLETED:   'Terminée',
};

// ── Composant principal
export default function DashboardPage() {
  // On stocke les données dans des variables d'état
  const [adventurers, setAdventurers] = useState<Adventurer[]>([]);
  const [quests, setQuests]           = useState<Quest[]>([]);
  const [loading, setLoading]         = useState(true);

  // useEffect : s'exécute une seule fois après le premier affichage (grâce au [] à la fin)
  useEffect(() => {
    Promise.all([getAllAdventurers(), getAllQuests()])
      .then(([advData, questData]) => {
        setAdventurers(advData);
        setQuests(questData);
        setLoading(false);
      });
  }, []);

  if (loading) return <p style={{ color: '#f5e6c8', padding: '2rem' }}>Chargement…</p>;

  const activeQuests    = quests.filter(q => q.status === 'IN_PROGRESS').length;
  const completedQuests = quests.filter(q => q.status === 'COMPLETED').length;
  const leaderboard     = [...adventurers].sort((a, b) => b.level - a.level || b.xp - a.xp);
  const topAdventurer   = leaderboard[0];

  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Tableau de bord</h1>

      {/* ── 4 cartes de statistiques ── */}
      <div className={styles.statsGrid}>

        <div className={styles.statCard}>
          <span className={styles.statLabel}>Aventuriers</span>
          <span className={`${styles.statValue} ${styles.colorAdventurers}`}>{adventurers.length}</span>
          <span className={styles.statSub}>membres de la guilde</span>
        </div>

        <div className={styles.statCard}>
          <span className={styles.statLabel}>Quêtes actives</span>
          <span className={`${styles.statValue} ${styles.colorActive}`}>{activeQuests}</span>
          <span className={styles.statSub}>en cours en ce moment</span>
        </div>

        <div className={styles.statCard}>
          <span className={styles.statLabel}>Quêtes terminées</span>
          <span className={`${styles.statValue} ${styles.colorCompleted}`}>{completedQuests}</span>
          <span className={styles.statSub}>missions accomplies</span>
        </div>

        <div className={styles.statCard}>
          <span className={styles.statLabel}>Meilleur aventurier</span>
          <span className={`${styles.statValue} ${styles.colorTop}`} style={{ fontSize: '1.4rem' }}>
            {topAdventurer?.name}
          </span>
          <span className={styles.statSub}>{topAdventurer?.adventurerType} · Niv. {topAdventurer?.level}</span>
        </div>

      </div>

      {/* ── Section du bas : leaderboard + quêtes récentes ── */}
      <div className={styles.bottomGrid}>

        {/* Leaderboard */}
        <div className={styles.panel}>
          <h2 className={styles.panelTitle}> Classement</h2>
          <ul className={styles.leaderList}>
            {leaderboard.map((adv, index) => {
              const rankClass = index === 0 ? styles.rank1 : index === 1 ? styles.rank2 : index === 2 ? styles.rank3 : styles.rankOther;
              return (
                <li key={adv.id} className={styles.leaderItem}>
                  <span className={`${styles.rank} ${rankClass}`}>{index + 1}</span>
                  <span className={styles.leaderName}>{adv.name}</span>
                  <span className={`${styles.leaderClass} ${styles[adv.adventurerType]}`}>{adv.adventurerType}</span>
                  <span className={styles.leaderLevel}>Niv. {adv.level}</span>
                </li>
              );
            })}
          </ul>
        </div>

        {/* Quêtes récentes */}
        <div className={styles.panel}>
          <h2 className={styles.panelTitle}>Quêtes récentes</h2>
          <ul className={styles.questList}>
            {quests.slice(0, 5).map(quest => (
              <li key={quest.id} className={styles.questItem}>
                <span className={styles.questTitle}>{quest.title}</span>
                <span className={`${styles.difficulty} ${styles[quest.difficulty]}`}>{quest.difficulty}</span>
                <span className={styles.status}>{STATUS_LABEL[quest.status]}</span>
              </li>
            ))}
          </ul>
        </div>

      </div>
    </div>
  );
}