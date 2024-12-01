
# COURSEWORK 2024 DMS
## Github:
## Compilation Instructions:
## Implemented and Working Properly:
### Feature 1
- Main Menu: provides a clean, interactive starting point for the game, allowing users to easily navigate between the start of the game, settings, and exit options. It utilizes JavaFX layout components to create a structured and visually appealing UI. The main menu is fully functional with the start game and exit buttons, and it is designed to be extensible for additional menu options in the future, such as settings or high scores.
### Feature 2
- WASD movement:a control scheme used in video games where the W, A, S, and D keys are used to move the player’s character or object W:Move up, A:Move left ,S:Move down ,D:Move right.This setup is common for PC games, allowing intuitive movement in 2D or 3D environments. In the feature code, the player's character moves according to the key pressed, with stop functionality added when the keys are released.




## Implemented but Not Working Properly:
## Features not implemented:
## New Java Classes:
### 1.Menu.java
- Description: The Menu class provides the core functionality for the main menu of the "Sky Battle" game. It includes graphical components such as a background image and buttons for navigation. The buttons trigger actions like starting the game, accessing settings, or exiting the application. The class uses JavaFX's layout managers (GridPane, StackPane) to ensure the interface is organized and user-friendly.<br>
- Location:
### 2.ButtonImage.java
- Description: The ButtonImage class simplifies the process of creating image-based buttons in a JavaFX application. It abstracts the repetitive steps of loading an image, resizing it, and ensuring its visibility. The class is reusable and allows easy addition of new buttons without needing to repeat code, making it efficient for applications that require multiple image-based buttons. <br>
- Location: 
### 3.MenuImage.java
- Description: The MenuImage class is a specialized ImageView used for displaying images, especially background images, in the game menu. It allows for easy setup of the image's position, size, and visibility. This class promotes code reuse by providing a single template for handling background images, making it easier to manage and modify the graphical elements in the game’s user interface.<br>
- Location
## Modified Java Classes:
### 1.Controller.java
- Changes: Within the public void update method replaced alert error handling method with e.printstacktrace<br><br>
- Reasoning: The original code used an Alert to show the error to the user. However, it only displayed the class name of the exception (e.g., java.lang.NullPointerException), which was not very informative for debugging, nor did it provide enough detail to help developers understand what went wrong. The change to e.printStackTrace() provides a more detailed view of the error. When an exception is caught, the stack trace includes:The exception type (e.g., NullPointerException).
The method calls that led to the exception.The line numbers in the source code where each method was invoked.This is more useful for developers during debugging as it helps them trace the exact sequence of events that caused the error.Which in turn helped me to fix the Switch level Crash <br><br>

### 2.LevelParent.java
- Changes: Within public void goToNextLevel added timeline.stop method.<br>
- Reasoning:Helped stop CheckGameOver method from level one being run several times during the time frame of transitioning to another level (Refer Unexpected Problems 2 for more information)<br><br>

- Changes: The changes made to the initializeBackground() method involve expanding the movement controls for the user plane. In the original version, only vertical movement (up and down) was handled using the W and S keys, along with projectile firing via the SPACE key. The updated version introduces left (A) and right (D) movement, allowing the user plane to move in all four directions. Additionally, the new implementation adds functionality to stop the movement when the respective keys are released. Specifically, when the vertical movement keys (Up or Down) are released, the vertical movement stops, and when the horizontal movement keys (Left or Right) are released, horizontal movement ceases as well.<br>
- Reasoning: Modifying the code to use WASD keys for movement aligns with industry standards and enhances player experience. The WASD layout is widely recognized for ease of use and comfort, making it intuitive for players. It provides ergonomic benefits, allowing players to control movement with their left hand while using the right hand for other tasks. This setup also improves gameplay by offering precise, fluid controls, and allows flexibility for future updates or features. Overall, switching to WASD ensures consistency with player expectations, leading to a more enjoyable and accessible gaming experience.<br>

### 3.ShieldImage.java
- Changes: Within public ShieldImage replaces the directory within the chain of methods in this.image with the variable IMAGE_NAME and changed the variable to the proper directory
- Reasoning: To achieve:<br>
  --Maintainability such as easier to update <br>
  --Readability such as meaningful variable making it easier to understand the code and adds clarity<br>
 --Reusability such as having the image path stored in a constant makes it easier to reuse as the same varible can be used preventing the same string being manually written<br><br>
  --Error Prevention such as when working with files or resources, the paths are prone to errors. Using a constant ensures that the path is correct in all instances where it's used. It can also be easier to handle the path centrally if needed (e.g.for validation or debugging).<br>
  ### 4.Boss.java
  - Changes: Created  private ShieldImage.java object called shieldImage ---> Within Constructor initialized the aforementioned object --> Created ShieldPosition method within sets the X and Y axis of the image in line with the boss enemy's Y axis and X axis ---> Added the ShieldPosition method to the UpdateActor methold so it would sync with boss movement when movement is updated ---> Added shieldimage.showShield method  to ActivateSheild method so the shield would be visible when ActivateShield is called ---> Added shieldimage.hideshield method to DeactivateShield method so the shield would not be visible when DeactivateShield is called ---> Created getShieldImage method that returns instance(shieldImage) of class ShieldImage<br><br>
- Reasoning: These changes ensure that the boss can have a shield that moves with it, activates and deactivates correctly, and is visible at the appropriate times. The shieldImage object encapsulates the shield's functionality, while the various methods allow for synchronization with the boss and management of the shield's visibility. The getShieldImage method makes the shield accessible to other parts of the game, enabling its inclusion in the scene.<br>
### 5.Main.java
- Changes: Converts private Controller myController to protected ---> Delete all private variables and unnecessary imports such java.lang.reflect.invocationTargetException ---> remove uneccessary  throws start method only keep SecurityException,IllegalArguementException --->  Everything  in start method deleted ---> Within Start Method create instance of Menu class ---> call MenuConfig method using the object ---> call MenuShow method using the object<br>
- Reasoning: This change provides a more interactive, user-friendly approach where the user is presented with a main menu before the game starts. It also gives you flexibility to build out the game with additional menu features or configurations in the future.Moreover, by separating the main menu from the game logic, you make the game flow more modular and structured. <br>
### 6.UserPlane.java
- Changes: Firstly, two new constants, X_UPPER_BOUND and X_LOWER_BOUND, were introduced to define the horizontal movement boundaries, ensuring that the user plane cannot move off-screen to the left or right. The constant HORIZONTAL_VELOCITY was also added to control the speed of horizontal movement, similar to how vertical speed is managed.The velocityMultiplier variable was renamed to VerticalvelocityMultiplier to specifically control vertical movement. In parallel, a new variable, HorizontalvelocityMultiplier, was introduced to control horizontal movement. This distinction ensures that the vertical and horizontal movement systems are handled separately, each with its own velocity multiplier.The updatePosition method was modified to account for both vertical and horizontal movements. It now includes logic to handle horizontal movement by checking the HorizontalMoving method and adjusting the position based on HorizontalvelocityMultiplier. Additionally, boundary checks were added for the horizontal axis to prevent the plane from moving beyond the left (X_LOWER_BOUND) and right (X_UPPER_BOUND) limits, similar to the vertical movement's constraints.To facilitate horizontal movement, new methods moveLeft and moveRight were added. These methods set the HorizontalvelocityMultiplier to -1 and 1, respectively, allowing the plane to move left and right. Correspondingly, the Horizontalstop method was introduced to stop horizontal movement when the keys are released.The vertical movement methods—moveUp, moveDown, and Verticalstop—were kept, but the velocityMultiplier was renamed to VerticalvelocityMultiplier for clarity and to differentiate it from the horizontal movement controls.No code was entirely removed, but the class was refactored to clearly separate the logic for vertical and horizontal movements. The updatePosition method now manages both axes individually, ensuring that both directions are handled efficiently while maintaining the screen boundaries.<br><br>
-Reasoning:The changes made to the UserPlane class were intended to enhance the plane's movement capabilities by allowing for both horizontal and vertical movement. Prior to these changes, the plane could only move vertically, which restricted the player's ability to navigate the game world effectively. This limitation was addressed by adding functionality for horizontal movement, enabling the player to control the plane in all four directions—up, down, left, and right.
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
