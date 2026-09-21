import { useState, useEffect } from 'react';
import type { Quest, QuestStatus, Difficulty } from '../types';
import { getAllQuests } from '../services/questService';
import styles from './QuestsPage.module.css';

const STATUS_LABEL: Record<QuestStatus, string> = {
  AVAILABLE:   'Disponible',
  IN_PROGRESS: 'En cours',
  COMPLETED:   'Terminée',
};

const DIFFICULTY_LABEL: Record<Difficulty, string> = {
  EASY:   'Facile',
  MEDIUM: 'Moyen',
  HARD:   'Difficile',
  EPIC:   'Épique',
};

export default function QuestsPage() {
  const [quests, setQuests]               = useState<Quest[]>([]);
  const [loading, setLoading]             = useState(true);
  const [statusFilter, setStatusFilter]   = useState<QuestStatus | 'ALL'>('ALL');
  const [difficultyFilter, setDifficultyFilter] = useState<Difficulty | 'ALL'>('ALL');

  useEffect(() => {
    getAllQuests().then(data => {
      setQuests(data);
      setLoading(false);
    });
  }, []);

  if (loading) return <p style={{ color: '#f5e6c8', padding: '2rem' }}>Chargement…</p>;

  const filteredQuests = quests.filter(quest => {
    const statusOk     = statusFilter     === 'ALL' || quest.status     === statusFilter;
    const difficultyOk = difficultyFilter === 'ALL' || quest.difficulty === difficultyFilter;
    return statusOk && difficultyOk;
  });

  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Quêtes</h1>

      <div className={styles.filters}>
        <div className={styles.filterGroup}>
          <span className={styles.filterLabel}>Statut :</span>
          <select
            className={styles.filterSelect}
            value={statusFilter}
            onChange={e => setStatusFilter(e.target.value as QuestStatus | 'ALL')}
          >
            <option value="ALL">Toutes</option>
            <option value="AVAILABLE">Disponible</option>
            <option value="IN_PROGRESS">En cours</option>
            <option value="COMPLETED">Terminée</option>
          </select>
        </div>

        <div className={styles.filterGroup}>
          <span className={styles.filterLabel}>Difficulté :</span>
          <select
            className={styles.filterSelect}
            value={difficultyFilter}
            onChange={e => setDifficultyFilter(e.target.value as Difficulty | 'ALL')}
          >
            <option value="ALL">Toutes</option>
            <option value="EASY">Facile</option>
            <option value="MEDIUM">Moyen</option>
            <option value="HARD">Difficile</option>
            <option value="EPIC">Épique</option>
          </select>
        </div>
      </div>

      {filteredQuests.length === 0 ? (
        <p className={styles.empty}>Aucune quête ne correspond à ces filtres.</p>
      ) : (
        <div style={{ overflowX: 'auto', width: '100%' }}>
          <table className={styles.table}>
            <thead>
              <tr>
                <th>Titre</th>
                <th>Difficulté</th>
                <th>Statut</th>
                <th>Niv. min</th>
                <th>Or 🪙</th>
                <th>XP ⭐</th>
              </tr>
            </thead>
            <tbody>
              {filteredQuests.map(quest => (
                <tr key={quest.id}>
                  <td>
                    <div className={styles.questTitle}>{quest.title}</div>
                    <div className={styles.questDesc}>{quest.description}</div>
                  </td>
                  <td><span className={`${styles.badge} ${styles[quest.difficulty]}`}>{DIFFICULTY_LABEL[quest.difficulty]}</span></td>
                  <td><span className={`${styles.statusBadge} ${styles[quest.status]}`}>{STATUS_LABEL[quest.status]}</span></td>
                  <td className={styles.center}>{quest.requiredLevel}</td>
                  <td className={styles.center}>{quest.goldReward}</td>
                  <td className={styles.center}>{quest.xpReward}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}