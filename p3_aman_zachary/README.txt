IDIOSYNCRASIES:

Touchpress:
returning true instead of consumed to account for a touch_press outside of char0 but a release within it
Matthew and Vivek were of the opinion that a release within char0 should trigger its event even if the initial touch wasn't within its bounds
so even if the first event ACTION_DOWN is not consumed I always want the stream of events to fire

button1 animation:
- animated character sometimes will hang before animation, will animate later after waiting, not entirely sure what is going here
- changes to heart when it hits the passover icon and also changes that to a heart
	- throws an error here (trying to access character -1, which is out of bounds).  I've changed it so it checks but outputs an error message to LogCat

onTouchEvent:
- I noticed if you drag outside of the screen it still tries to follow and doesn't release drag -- but let's be real, that straight up isn't possible on a real phone so I'm leaving it
- bounds checking to not trigger on the button bar is hardcoded at 545px.  I realize this is bad and I'm sorry.
	
ACKNOWLEDGMENTS:
https://developer.android.com/training/gestures/movement.html
bounced some ideas off of Vivek, he pointed out that I had access to the original event when executing an action (I had been wondering how to get X,Y position from actions that didn't have them)

EXTENSION:
I haven't had time to work on a game this week but I fixed the animation bug.
It seems the issue was with the timer not stopping when exiting the application, leaving a separate thread open and slowing down the entire emulator.
I fixed the issue by declaring the GameEngineBase outside of onCreate() and then stopping the time in onStop().
The sources I used were: 
https://stackoverflow.com/questions/10311087/how-to-stop-timer-android
https://developer.android.com/training/basics/activity-lifecycle/stopping.html#Stop