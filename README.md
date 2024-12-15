
# COURSEWORK 2024 DMS
## Github:
### https://github.com/AmeenSharyf/CW2024
## Compilation Instructions:
- Download Source code ---> Use IDE(preferebly intellij) ----> Add the project ----> SetupSDK ----> run main class in controller package
## Implemented and Working Properly:
### Feature 1: Main Menu
- Provides a clean, interactive starting point for the game, allowing users to navigate between starting the game, adjusting settings, and exiting.
- Utilizes JavaFX layout components to create a structured and visually appealing UI.
- Mini Features:
- - Start Button: Begins the game.
- - Settings Button: Opens the Settings menu.
- - Exit Button: Exits the game.

### Feature 2: WASD Movement
- Implements a standard PC gaming control scheme for moving the player’s character:
- W: Move up.
- A: Move left.
- S: Move down.
- D: Move right.
- Movement is intuitive and works seamlessly for 2D gameplay. When the keys are released, the character stops moving.
### Feature 3: Ending Menu
-  Displays an interactive menu when the user wins, loses, or pauses the game.
-  Includes the following functional buttons:
- - Restart Button: Restarts the game, beginning from Level One.
- - Menu Button: Returns the user to the Main Menu.
- - Exit Button: Exits the game.
### Feature 4: Settings Menu
 -   Opens from the Main Menu and allows the user to adjust game settings.
 -   Features:
- -  Volume Slider: Adjusts background music and sound effects.
- -  Difficulty Slider: Changes the difficulty level.
- -  Back Button: Returns to the Main Menu.
### Feature 5: New Levels
- Two new levels (Level Three and Level Four) have been added, increasing difficulty.
- Levels incorporate enemies from previous levels (One and Two) along with a new enemy boss.
### Feature 6: Realistic Hitbox
-  Hitboxes for visual components have been reduced to match their actual size for better accuracy.
-  Exception: The Jetboss (Original Boss) retains its larger hitbox to avoid extreme difficulty completing the game.
### Feature 7: Sound Effects
-   Includes background music for gameplay.
-   Distinct sound effects added for:
- -  Enemy shooting.
- - User shooting.
### Feature 8: Pausing
- Pressing the ESCAPE key pauses the game and displays the interactive Ending Menu.
- Pressing the ESCAPE key after pausing removes interactive Ending Menu and resumes Game.
### Feature 9: Improved Visuals
-  Enhanced visuals for the user plane, enemy planes, and bullets to make gameplay more appealing.
-  Some visuals remain unchanged to highlight differences between the Original Game and the Revised Version.
-  First boss now features a shield that moves along with it.
-  Game Icon added
## Implemented but Not Working Properly:
## Features not implemented:
### Feature 1: Boss2,Boss2 Projectile new Behavior and Attributes
- Reasoning: Revising Several codes would consume too much just to implement a new boss mechanic causes the overall project completion to slow down.
## New Java Classes:
### 1.Menu.java
- Description: The Menu class is a singleton that manages the main menu of a JavaFX application. It sets up the UI with a background and buttons, handles button actions (start game, open settings, and exit), and manages the main stage and scene transitions. It uses custom image-based components (ButtonImage and MenuImage) and ensures a smooth user experience with proper resource cleanup on exit.
- Location: src/main/java/Menus/Menu.java
### 2.ButtonImage.java
- Description: The ButtonImage class extends ImageView and is designed to represent image-based buttons in a JavaFX application. It simplifies creating buttons with specific images by allowing customization of their height and width. This class improves reusability and modularity, enabling the creation of consistent and visually appealing buttons for the UI.
- Location: src/main/java/ImageClass/ButtonImage.java 
### 3.MenuImage.java
- Description: The MenuImage class extends ImageView and represents a background image for menus in a JavaFX application. It allows for customizable positioning, size, and opacity of the image, ensuring flexibility and consistency in displaying menu backgrounds. This class simplifies handling background images across different screens while maintaining a uniform appearance.
- Location: src/main/java/ImageClass/MenuImage.java
### 4.ShieldImage.java
- Description: The ShieldImage class extends ImageView and represents a shield image in a JavaFX application. It allows for customizable positioning and provides methods to control the shield's visibility. The class is primarily used to display or hide a shield image on the screen, with a default size and image path, simplifying its integration into the application.
- Location: src/main/java/ImageClass/ShieldImage.java
### 5.Window.java
- Description: The Window class is a singleton responsible for managing the main application window in a JavaFX application. It ensures that only one instance of the primary Stage exists, providing a centralized way to access and manage the main window. This simplifies stage handling and ensures consistency throughout the application.
- Location: src/main/java/Controller/Window.java
### 6.LevelThree.java
- Description: The LevelThree class, extending LevelParent, defines the third level of the game, featuring a boss fight and probability-based enemy spawning. It initializes the player's unit, manages game over conditions by detecting either the player's destruction or the boss's defeat, and transitions to the next level upon completion. The level introduces Boss2, complete with a shield image, and spawns waves of enemies dynamically to maintain challenge. Additionally, it sets up the visual representation of the level through a LevelView and manages player health, encapsulating the gameplay logic for this stage in a modular and extensible way.
- Location: src/main/java/Level/LevelThree.java
### 7.LevelFour.java
- Description: The LevelFour class extends LevelParent and represents the fourth level of the game, featuring a unique background, two bosses (Boss and Boss2), and specific gameplay mechanics. It requires players to defeat both bosses to win while managing their health, with a loss triggered if the player is destroyed. The class initializes friendly units by adding the player's unit to the scene and spawns the bosses when no enemies are present, complete with their respective shield images. Visual elements for the level are set up using a LevelView, encapsulating health and other game components to deliver a challenging and visually distinct stage.
- Location: src/main/java/Level/LevelFour.java
### 8.LevelObserver.java
- Description: The LevelObserver interface defines a contract for classes to observe and respond to level changes in the game. Implementing classes must provide logic for handling level transitions through the onLevelChange method, which takes the name of the next level as a parameter. This interface supports decoupling game state monitoring from level transition logic, ensuring flexibility and modularity in the game's design.
- Location: src/main/java/Level/LevelObserver.java
### 9.LevelSceneInitialization.java
- Description: The LevelSceneInitialization class is responsible for setting up the game scene in terms of background image and game timeline. It initializes the background image using a specified file path, adjusting its size to fit the screen dimensions. The class also manages the game timeline by scheduling updates at regular intervals. The StartGame method starts the game by focusing on the scene's root group and beginning the timeline, ensuring the game updates are performed at specified intervals. This approach ensures that the game can run smoothly with consistent updates, enhancing the player's experience.
- Location: src/main/java/Level/LevelSceneInitialization.java
### 10.ActorManager.java
- Description: The ActorManager class manages all active game actors, including friendly units (like the user plane), enemy units, and projectiles. It handles their updates, removal when destroyed, and manages lists of these actors. The class provides a singleton instance to ensure only one ActorManager exists for the game. It handles the game logic related to actors, such as updating positions, removing destroyed actors, and counting kills. The ActorManager interacts with the scene’s root Group to manage visual representation and updates of these actors, ensuring efficient management and gameplay performance.
- Location: src/main/java/Managers/ActorManager.java
### 11.CollisionManager.java
- Description: The CollisionManager class manages the detection and response to collisions between game actors (friendly units, enemy units, projectiles). It inherits from ActorManager to access shared lists of these actors. The class handles interactions such as friendly-fire collisions, enemy-projectile impacts, and checks for penetration of defenses by enemies. It utilizes a singleton pattern to ensure only one instance exists for the game session. Collision responses involve damage calculations, projectile handling, and audio feedback using sound effects. The CollisionManager also manages enemy projectile generation, ensuring gameplay dynamics are maintained effectively.
- Location: src/main/java/Managers/ActorManager.java
### 12.UserInputSystem.java
- Description: The UserInputSystem class manages user input for a game, enabling real-time control of player movements (up, down, left, right) and actions like shooting (using the spacebar) and pausing (with the ESCAPE key). It integrates with the UserPlane class to translate key presses into corresponding movements and actions, and it uses a Timeline for handling game updates and animations. The class follows the singleton pattern to ensure only one instance exists throughout a game session, and it sets event listeners for key press and release events to manage these interactions effectively. Additionally, it includes sound effects for shooting and handles pausing and resuming the game with user-friendly controls. 
- Location: src/main/java/Managers/UserInputSystem.java
### 13.UserProjectileManager.java
- Description: The UserProjectileManager class manages user-fired projectiles within the game, handling their creation, storage, and rendering. It maintains a list of ActiveActorDestructible objects representing the projectiles. The class offers methods to get the list of projectiles, reset the list, and fire new projectiles from the user's plane. It follows the singleton pattern to ensure only one instance of UserProjectileManager is used throughout a game session. The class interacts with the game scene's root Group to add projectiles and updates the scene as necessary.
- Location: src/main/java/Managers/UserProjectileManager.java
### 14.EndingMenu.java
- Description: The EndingMenu class represents the end-game menu of the application, providing options for users to restart the game, return to the main menu, or exit the game. As a singleton, it ensures only one instance is active at any given time. This class dynamically updates its root group to handle UI transitions effectively. The menu features background management, button creation with click actions (like restarting the game, returning to the main menu, and exiting), and a layout configuration with a StackPane and GridPane. Each button action triggers a specific response such as clearing the game scene, starting a new game, returning to the main menu, or exiting the application.
- Location: src/main/java/Menus/EndingMenu.java
### 15.SettingMenu.java
- Description: The SettingMenu class manages the settings menu of the application, allowing users to adjust game settings like volume and difficulty. It operates as a singleton to ensure a single instance is active at any time. The menu offers a way for users to navigate back to the main menu. The class provides functionalities for resetting settings, configuring UI components (such as sliders for volume and difficulty, and buttons for navigation), and adjusting settings dynamically. It uses Slider objects to control volume and difficulty, and a Button for returning to the main menu. This setup provides a seamless user experience for customizing game settings within the application.
- Location: src/main/java/Menus/SettingMenu.java
### 16.Boss2.java
- Description: The Boss2 class represents the second boss character in a game, inheriting from FighterPlane. It initializes attributes like image name, position, fire rate, health, and movement patterns. The boss’s movement is governed by a pattern of vertical moves that are shuffled periodically. The class manages a shield that can be activated at a certain probability and lasts for a set number of frames before deactivating. When active, the shield makes the boss invincible to damage. The fireProjectile() method determines if the boss should shoot a projectile based on a fire rate. The boss’s position and shield are synchronized within the scene graph, ensuring that visual elements are correctly aligned with the game mechanics. The class also handles edge cases such as movement boundaries and ensures a consistent and challenging gameplay experience for players.
- Location: src/main/java/Plane/Boss2.java
### 17.Boss2Projectile.java
- Description: The Boss2Projectile class represents a projectile fired by the Boss2 in the game. It extends the Projectile class and initializes with specific attributes, such as image name, height, initial X position, and horizontal velocity. The constructor sets the initial position and image of the projectile. The updatePosition() method moves the projectile horizontally at a constant velocity. The updateActor() method updates the projectile's position by calling updatePosition(). This class manages the movement and state of the projectile in the game, ensuring it behaves correctly within the gameplay mechanics.
- Location: src/main/java/Projectile/Boss2Projectile.java
### 18.SoundEffects.java
- Description: The SoundEffects class manages both background music and sound effects for a game. It uses MediaPlayer for playing background music and AudioClip for handling sound effects. The volume level for all sounds can be adjusted globally through the setVolume method. The playBackgroundMusic() method plays the background music, creating a MediaPlayer if it does not already exist. The pauseBackgroundMusic() method pauses the background music if it is playing. The UserShootSound() and EnemyBullets() methods play specific sound effects for user shooting and enemy bullets, respectively, using AudioClip. The setVolume method updates the volume for both music and sound effects, ensuring a consistent audio experience across the game.
- Location: src/main/java/Sound/SoundEffects.java
## Modified Java Classes:
### 1.ActiveActor.java
- The changes made to the ActiveActor class involved updating the image loading mechanism to use relative paths through getClass().getResource(...), centralizing the location of images with the IMAGE_LOCATION constant, and adding methods like ChangeImage(String imageName) and AdjustImage(int height, int width). These changes improve the encapsulation and flexibility of the class, allowing easier management of image resources, dynamic updates to images, and adjustments to image dimensions. The use of relative paths ensures compatibility across different IDEs and package structures, while the new methods simplify the process of modifying images and their dimensions, thus enhancing the overall usability and maintainability of the class.
- These modifications were necessary to make the ActiveActor class more user-friendly and robust in handling game assets. By using relative paths for image loading, developers can avoid hardcoding paths and simplify updates when images are moved or renamed. The addition of ChangeImage and AdjustImage methods provides a more intuitive API for adjusting the visual representation of game entities, reducing the amount of code required for modifications and improving the class's adaptability to various game designs. These changes are crucial for maintaining clean and modular code, especially in larger projects where game assets might be managed by different teams or through different systems.
### 2.Controller.java
- The changes made to Controller class would be implementing the new custom Observer called LevelObserver and using e.printstacktrace instead of Alert.
- The modifications were necessary coz the old observer used is outdated,inflexible and has lack of support for mordern concurrency.Moreover, for the reasoning why e.printstacktrace is used is because it provides more detailed examinations of exceptions when compared to alert hence helping in the debug process significantly.
### 3.Main.java
- In the revised Main class, significant changes were made to streamline the initialization of the game and its components. The SoundEffects class is now used to play background music at the start, setting the tone for the game environment right from the beginning. This approach not only enhances the user experience by providing an immersive audio element but also centralizes the management of audio in one location, making it easier to modify or extend in the future. This change is necessary because it decouples the game’s core logic from audio handling, thus improving maintainability and modularity of the codebase.
- The initialization of the main menu has been moved into a dedicated class (Window) that manages the game window and its components. This change ensures that the logic for setting up the game environment is more organized and separate from the core application logic. By using a singleton pattern to retrieve the window instance, the application reduces the overhead of creating multiple window objects, which can be inefficient. The Menu class is now accessed directly through the Window class, ensuring that the main menu is consistently set up and displayed across different parts of the application. This separation is essential for clean code architecture, making it easier to extend or modify the menu functionality as needed.
### 4.Boss.java
- The changes made to Boss class would be within activateshield method using shieldimage.showshield to display shield when active and within deactivateshield method calling shieldimage.hideshield to hide shield when deactivated,-Creating ShieldPosition Method to synchronize the shield of boss with the boss movement,-Adding ShieldPosition method to updateActor,-Created GetShieldImage method to return shieldImage variable.
- These modifications were necessary to enable the shield for the boss to be displayed and synchronized withn its movement properly to avoid user frustration of assuming bullets not being registered.Moreover, it is a quality of life improvement.
### 5.EnemyPlane.java
- The code for EnemyPlane has moved from  image name like enemyplane.png to a new one, Enemies/EnemyJet.png. Additionally, adjustments have been made to the image size with AdjustImage(150, 150). This change is necessary to better differentiate between different enemy types in the game, enhancing visual clarity and allowing for more varied enemy designs. The ChangeEnemy() method is also added to dynamically change the plane's appearance, making the gameplay more engaging and providing visual variety to the player.
- The health of the EnemyPlane was increased from 1 to 5 in the new implementation, making the enemy more resilient. This adjustment is necessary to increase the challenge level for players, especially at higher difficulty levels. The increase in health allows the enemy to survive more damage, requiring players to use different strategies or weaponry to defeat these tougher opponents, thus enhancing the game’s difficulty progression.
### 6.EnemyProjectile.java
- The changes made to the EnemyProjectile class involve refining the initialization and movement logic of the projectile. The constructor was modified to include a size adjustment by calling AdjustImage(50, 50), setting the image dimensions explicitly. This ensures that the projectile’s image size matches the intended visual appearance in the game. Additionally, a conditional check was added to randomly change the image appearance, providing visual variety for the player. This change is necessary to enhance gameplay experience by preventing visual monotony in projectile types.
### 7.FighterPlane.java
- The changes made is created method called to sethealth to set the health of the object and created a method called EnemyDifficultyIncrease used to increase the health of enemy planes to increase the difficulty.
- The modifications were necessary to in order to set the health of a subclass object.Moreover, to properly implement the functionality of higher difficulty the enemydificultyincrease method is necessary to be called and created.
### 8.LevelOne.java
- The changes made is in the intializationfriendlyunits method added getuser.resetpositon and getuser.resethealth. Added a  boolean flag in checkifgameover to ensure that if the condition meets it is run one time and Changed the enemy spawn probability  0.005.
- These modifications are necessary as the userplane needs to be properly reset when levelOne is called.Moreover, the flag is used to ensure repeated runs wont happen.
### 9.LevelParent.java
- The updated LevelParent class introduces significant changes to enhance modularity, maintainability, and scalability. Key changes include the delegation of responsibilities to specialized managers such as ActorManager, CollisionManager, UserInputSystem, and UserProjectileManager, which handle specific tasks like managing actors, collisions, user inputs, and projectiles. This reduces the complexity of LevelParent and improves code reusability since these managers can be shared across multiple levels. Additionally, the introduction of a custom LevelObserver interface replaces the outdated Observable, providing a cleaner implementation of the observer pattern to notify level transitions. A new LevelSceneInitialization class is added to separate scene setup tasks such as background initialization and timeline configuration from the game loop, improving readability and reducing redundant code.

- Singleton design patterns are employed for the managers to ensure only one instance exists, promoting centralized control and consistent behavior across levels. Methods such as initializeScene, startGame, and updateScene are refactored to delegate tasks to respective managers, decoupling the game logic and improving testability.Integration with EndingMenu provides a smooth transition to the ending menu when the game is won or lost, enhancing the user experience. Reset and reinitialization logic, including methods like ResetKillCount and manager reset calls, ensures that levels start with a clean slate, preventing unintended carryover of states.

- These changes collectively make the LevelParent class more scalable, flexible, and reusable. By modularizing functionalities, the design allows for easier addition of new features, better readability, and more focused testing. The decoupled architecture ensures that individual components can be updated or replaced without affecting the overall functionality, which is critical for managing complex projects and supporting future expansions.
### 10.LevelTwo.java
- Within instantiateLevelView method  the  new levelview object parameter taking playerhealth using PLAYER_INITIAL_HEALTH is replaced with getUser().getHealth().Moreover added the boss shield to root in the method spawnenemyunits.
- Reasoning for this change is the getuser.gethealth would represent the actual health of the user after levelone hence it was used to avoid incorrect health being displayed.Furthermore, the boss shield being added to root was necessary to display the bosses shield as it didnt properly did before.
### 11.LevelView.java
- The changes in the updated LevelView class primarily focus on refining the display of win/lose screens and health management. The WinImage and GameOverImage components now include explicit width and height adjustments using setFitWidth() and setFitHeight(). These adjustments ensure that the images scale appropriately to provide better visibility, covering a significant portion of the screen while maintaining their aspect ratio through the preserveRatioProperty() setting. This is crucial for maintaining a professional and consistent appearance across various screen resolutions.

- Additionally, the positioning of the WinImage and GameOverImage is now explicitly defined using setX() and setY(), which ensures precise placement on the screen. This improvement eliminates potential alignment issues and guarantees that the images appear at visually appropriate and intended positions. These changes enhance the overall user experience by providing clearer visual feedback during key moments, such as winning or losing a level.

- Moreover, these updates contribute to better scalability and usability of the game UI by making the visual components more adaptable to different screen sizes and resolutions, which is essential for modern game design. The overall goal of these changes is to improve the visual quality and functionality of the game, ensuring a polished and immersive experience for players.
### 12.LevelViewLevelTwo.java
- Deleted the entire class.
- Reasoning is it was unnecessary to use as levelview can be used instead since this class has no significant impact as there is no class that is impacted highly by it or is fully utilising it.
### 13.UserProjectile.java
- AdjustImage is called within the constructor to properly adjust the new bullet image to provide the correct visual.
- It is necessary as if the image is not adjusted it would be enlarged significantly and provides a easy way for developer to use the function to adjust the images for the projectiles easily.
### 14.Userplane.java
- The UserPlane class underwent several significant changes to improve functionality, flexibility, and gameplay experience. Firstly, a singleton design pattern was implemented by introducing the getUserPlane method and a static userPlane field. This ensures only one instance of the UserPlane exists, maintaining consistency throughout the game and enabling centralized control, especially useful in cases like resetting the player's state.

- Horizontal movement was added to allow the plane to move left and right in addition to its vertical motion. This included introducing new constants (X_UPPER_BOUND, X_LOWER_BOUND, and HORIZONTAL_VELOCITY) and separate velocity multipliers (HorizontalvelocityMultiplier and VerticalvelocityMultiplier). The new movement logic was integrated into the updatePosition method, ensuring that horizontal and vertical bounds are respected during movement. These changes enhance the gameplay by giving players more control and freedom over their plane's movement, making the experience more dynamic.

- A method to reset the plane’s position (ResetPosition) and health (ResetHealth) was introduced, which is crucial for scenarios like restarting a level or the entire game. It ensures the plane returns to its original state, providing consistency and fairness in gameplay.

- The fireProjectile method was updated to allow for customized projectile firing positions using offsets (PROJECTILE_X_POSITION_OFFSET and PROJECTILE_Y_POSITION_OFFSET). This makes the projectile firing visually and functionally aligned with the plane’s current position, enhancing precision and realism.

- Additionally, new methods to control the horizontal (moveLeft, moveRight, Horizontalstop) and vertical movement (moveUp, moveDown, Verticalstop) were added, simplifying the code and improving readability. These methods make it easier to manage plane movement in response to player input.

- Finally, the plane's kill count management was improved with the addition of setNumberOfKills, which allows for external setting of the kill count, and the incrementKillCount method was retained for incremental updates. These methods ensure proper tracking of gameplay metrics.

Overall, these changes were necessary to enhance the user experience, improve code organization, and provide essential functionality for gameplay mechanics.
## Unexpected Problems:



### 1.Switch level Crash
- Meeting the error:When level one is completed it causes immediate crash<br><br>
- Pinpointing the error:In the Modified controller class e.printstacktrace(); method was used instead of alert enabling me to find the exact cause and location of the error<br><br>
- Fixing the error:Within Shield image class changed the directory shield.jpg to shield.png (Refer to heading Modified Java Class in subheading (1) for technicality)<br><br>
- Reasoning behind the error:(/com/example/demo/images/shield.png) is incorrect or the file does not exist, the new Image() constructor will likely throw an exception. This happens because the method getResource() or toExternalForm() fails to locate or properly reference the file.The exception I got is NullpointerException because the specified file cannot be found hence causing any subsequent method call (toExternalForm()) to throw a NullPointerException since it returns null


### 2.Exceeding Heap Memory Resulting in Crash 
- Meeting the error:When first level is finished which results in the loading of second level caused high ram usage hence depending on the run causes crashing due to exceeding heap memory.<br><br>
- Pinpointing the error:Using Intellij Profiler I was able to find out which method was causing highest usage of heap memory which would be the method in levelone class called public void CheckIfGameOver.<br><br>
- Fixing the error:I added timeline.stop method to the standalone method called public void goToNextLevel in LevelParent.java which would stop the gamelogic entirely right before the transition to nextlevel using method CheckIfGameOver(LevelOne.java).(Refer to heading Modified Java Class in subheading(2) for more technicality)<br><br>
- Reasoning behind the error:When method CheckIfGameOver(LevelOne.java) is called right after using meets the condition to transition what happens is the next level is loaded several times within the short span of 50ms several times resulting in heap memory being consumed since no guard mechanism exit for the transition when the gamelogic is being updated.<br><br>

### 3.Shield Not Displaying & not parallel with Enemy Boss
- Meeting the error: When transitioning to level two the shield image isnt displayed but the shield works
- Pinpointing the error: From the knowledge regarding stage,scene,root the shield wasnt properly added to the root relating to the scene (Logical Reasoning).
- Fixing the error: Created ShieldImage object called shieldImage(IMPORTANT) in boss.class ---> Created ShieldPosition method syncing shield image with the boss ---> added ShieldPosition to updateActor so the shieldposition would be updated with the boss poition ---> added shieldImage.showShield method to activateShield method so shield would be visible when activateShield is called ---> added shieldImage.HideShield method to deactivateshield so the shield wont be visible when gone ---> then (IMPORTANT)  created method that returns ShieldImage class object hence returning shieldImage instance ---> In LevelTwo in SpawnEnemyUnits method added the object shieldImage instance to the root using the aforementioned method.
