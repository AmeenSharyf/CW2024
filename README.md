
# COURSEWORK 2024 DMS
## Github:
## Compilation Instructions:
## Implemented and Working Properly:
## Implemented but Not Working Properly:
## Features not implemented:
## New Java Classes:
## Modified Java Classes:
### 1.Controller.java(1)
- Changes: Within the public void update method replaced alert error handling method with e.printstacktrace<br><br>
- Reasoning: The original code used an Alert to show the error to the user. However, it only displayed the class name of the exception (e.g., java.lang.NullPointerException), which was not very informative for debugging, nor did it provide enough detail to help developers understand what went wrong. The change to e.printStackTrace() provides a more detailed view of the error. When an exception is caught, the stack trace includes:The exception type (e.g., NullPointerException).
The method calls that led to the exception.The line numbers in the source code where each method was invoked.This is more useful for developers during debugging as it helps them trace the exact sequence of events that caused the error.Which in turn helped me to fix the Switch level Crash <br><br>

### 2.LevelParent.java
- Changes: Within public void goToNextLevel added timeline.stop method.
- Reasoning:Helped stop CheckGameOver method from level one being run several times during the time frame of transitioning to another level (Refer Unexpected Problems 2 for more information)
### 3.ShieldImage.java
- Changes: Within public ShieldImage replaces the directory within the chain of methods in this.image with the variable IMAGE_NAME and changed the variable to the proper directory
- Reasoning: To achieve:<br>
  --Maintainability such as easier to update <br>
  --Readability such as meaningful variable making it easier to understand the code and adds clarity<br>
 --Reusability such as having the image path stored in a constant makes it easier to reuse as the same varible can be used preventing the same string being manually written<br>
  --Error Prevention such as when working with files or resources, the paths are prone to errors. Using a constant ensures that the path is correct in all instances where it's used. It can also be easier to handle the path centrally if needed (e.g.for validation or debugging).<br>
  ### 4.Boss.java
  -Changes: Created  private ShieldImage.java object called shieldImage ---> Within Constructor initialized the aforementioned object --> Created ShieldPosition method within sets the X and Y axis of the image in line with the boss enemy's Y axis and X axis ---> Added the ShieldPosition method to the UpdateActor methold so it would sync with boss movement when movement is updated ---> Added shieldimage.showShield method  to ActivateSheild method so the shield would be visible when ActivateShield is called ---> Added shieldimage.hideshield method to DeactivateShield method so the shield would not be visible when DeactivateShield is called ---> Created getShieldImage method that returns instance(shieldImage) of class ShieldImage
  Reasoning: These changes ensure that the boss can have a shield that moves with it, activates and deactivates correctly, and is visible at the appropriate times. The shieldImage object encapsulates the shield's functionality, while the various methods allow for synchronization with the boss and management of the shield's visibility. The getShieldImage method makes the shield accessible to other parts of the game, enabling its inclusion in the scene.
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
-Reasoning behind the error:When method CheckIfGameOver(LevelOne.java) is called right after using meets the condition to transition what happens is the next level is loaded several times within the short span of 50ms several times resulting in heap memory being consumed since no guard mechanism exit for the transition when the gamelogic is being updated.<br><br>

### 3.Shield Not Displaying & not parallel with Enemy Boss
- Meeting the error: When transitioning to level two the shield image isnt displayed but the shield works
- Pinpointing the error: From the knowledge regarding stage,scene,root the shield wasnt properly added to the root relating to the scene (Logical Reasoning).
- Fixing the error: Created ShieldImage object called shieldImage(IMPORTANT) in boss.class ---> Created ShieldPosition method syncing shield image with the boss ---> added ShieldPosition to updateActor so the shieldposition would be updated with the boss poition ---> added shieldImage.showShield method to activateShield method so shield would be visible when activateShield is called ---> added shieldImage.HideShield method to deactivateshield so the shield wont be visible when gone ---> then (IMPORTANT)  created method that returns ShieldImage class object hence returning shieldImage instance ---> In LevelTwo in SpawnEnemyUnits method added the object shieldImage instance to the root using the aforementioned method.
