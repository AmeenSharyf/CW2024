package Level;

/**
 * The LevelObserver interface defines the methods required to be implemented by observers that track changes in game levels.
 * <p>
 * Implementing classes will need to handle actions such as transitioning to the next level when required by the game state.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelObserver.java">
 * GitHub Link</a>
 */
public interface LevelObserver {
    /**
     * This method will be called when a change in level occurs.
     * It should handle the logic required to transition to the specified next level.
     *
     * @param nextLevel The name of the next level to transition to.
     */
    void onLevelChange(String nextLevel);
}