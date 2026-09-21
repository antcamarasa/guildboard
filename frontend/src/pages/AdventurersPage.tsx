import { useState, useEffect } from 'react';
import type { Adventurer } from '../types';
import { getAllAdventurers } from '../services/adventurerService';
import styles from './AdventurersPage.module.css';

const CLASS_EMOJI: Record<Adventurer['adventurerType'], string> = {
  WARRIOR: '⚔️',
  MAGE:    '🔮',
  RANGER:  '🏹',
  CLERIC:  '✨',
};

export default function AdventurersPage() {
  const [adventurers, setAdventurers] = useState<Adventurer[]>([]);
  const [loading, setLoading]         = useState(true);

  useEffect(() => {
    getAllAdventurers().then(data => {
      setAdventurers(data);
      setLoading(false);
    });
  }, []);

  if (loading) return <p style={{ color: '#f5e6c8', padding: '2rem' }}>Chargement…</p>;

  return (
    <div className={styles.page}>
      <h1 className={styles.title}>Aventuriers</h1>

      <div className={styles.grid}>
        {adventurers.map(adv => {
          const xpNeeded  = adv.level * 100;
          const xpPercent = Math.min((adv.xp / xpNeeded) * 100, 100);

          return (
            <div key={adv.id} className={styles.card}>
              <div className={styles.cardHeader}>
                <span className={styles.emoji}>{CLASS_EMOJI[adv.adventurerType]}</span>
                <div>
                  <div className={styles.name}>{adv.name}</div>
                  <span className={`${styles.classBadge} ${styles[adv.adventurerType]}`}>{adv.adventurerType}</span>
                </div>
                <div className={styles.level}>Niv. {adv.level}</div>
              </div>

              <div className={styles.xpSection}>
                <div className={styles.xpLabels}>
                  <span>XP</span>
                  <span>{adv.xp} / {xpNeeded}</span>
                </div>
                <div className={styles.xpBar}>
                  <div className={styles.xpFill} style={{ width: `${xpPercent}%` }} />
                </div>
              </div>

              <div>
                <span className={styles.gold}>🪙 {adv.gold} or</span>
              </div>
            </div>
          );
        })}
      </div>
    </div>
  );
}