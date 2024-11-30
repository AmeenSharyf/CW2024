
# COURSEWORK 2024 DMS
## Github:
## Compilation Instructions:
## Implemented and Working Properly:
## Implemented but Not Working Properly:
## Features not implemented:
## New Java Classes:
## Modified Java Classes:
### Controller.java(1)
-Changes:Within the public void update method replaced alert error handling method with e.printstacktrace<br><br>
-Reasoning:The original code used an Alert to show the error to the user.However, it only displayed the class name of the exception (e.g., java.lang.NullPointerException), which was not very informative for debugging, nor did it provide enough detail to help developers understand what went wrong.The change to e.printStackTrace() provides a more detailed view of the error. When an exception is caught, the stack trace includes:The exception type (e.g., NullPointerException).
The method calls that led to the exception.The line numbers in the source code where each method was invoked.This is more useful for developers during debugging as it helps them trace the exact sequence of events that caused the error.Which in turn helped me to fix the Switch level Crash <br><br>
## Unexpected Problems:
### -Switch level Crash
-Meeting the error:When level one is completed it causes immediate crash<br><br>
-Pinpointing the error:In the Modified controller class e.printstacktrace(); method was used instead of alert enabling me to find the exact cause and location of the error<br><br>
-Fixing the error:Within Shield image class changed the directory shield.jpg to shield.png (Refer to heading Modified Java Class in subheading (1) for technicality)<br><br>
-Reasoning behind the error:(/com/example/demo/images/shield.png) is incorrect or the file does not exist, the new Image() constructor will likely throw an exception. This happens because the method getResource() or toExternalForm() fails to locate or properly reference the file.The exception I got is NullpointerException because the specified file cannot be found hence causing any subsequent method call (toExternalForm()) to throw a NullPointerException since it returns null


### -Exceeding Heap Memory Resulting in Crash 
- Meeting the error:When first level is finished resulting in the loading of second level caused high ram usage.
- Pinpointing the error:
- Fixing the error:
