package rollingcubes.results;

import com.google.inject.persist.Transactional;
import util.jpa.GenericJpaDao;

import javax.persistence.Query;

import java.util.List;

/**
 * DAO class for the {@link GameResult} entity.
 */
public class GameResultDao extends GenericJpaDao<GameResult> {

    public GameResultDao() {
        super(GameResult.class);
    }

    /**
     * Returns the list of {@code n} best results with respect to the time
     * spent for solving the puzzle.
     *
     * @param n the maximum number of results to be returned
     * @return the list of {@code n} best results with respect to the time
     * spent for solving the puzzle
     */
    @Transactional
    public List<GameResult> findBest(int n) {
        return entityManager.createQuery("SELECT r FROM GameResult r WHERE r.solved = true ORDER BY r.duration ASC, r.created DESC", GameResult.class)
                .setMaxResults(n)
                .getResultList();
    }

    /**
     * This function helps calculating the elements of the table.
     * @return the list of players from the games, not unique
     */
    @Transactional
    public List rowNumbers() {
        return  entityManager.createQuery("SELECT r.player FROM GameResult r").getResultList();

    }

    /**
     * This function use for returning all elements of the table.
     * @param n the maximum numbers of results to be return
     * @return the ordered list of {@code n} containing the elments of the GameResult table
     */
    @Transactional
    public List<GameResult> selectALL(int n) {
        return entityManager.createQuery("SELECT r FROM GameResult r ORDER BY r.duration ASC, r.steps ASC", GameResult.class)
                .setMaxResults(n)
                .getResultList();
    }
}
