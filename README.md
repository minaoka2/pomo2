# Introducing: The Pomodoro App

## Overview

The Pomodoro Technique is a useful technique for studying that allows an individual to get things done more efficiently. It consists of working on a task for a specified length of time, taking a short break, and then resuming the task, repeating this cycle until the task is finished and/or the individual decides that enough progresss on the task has been made in a given time period.

## Features of the app

- Pomodoro Timer, which is a timer pre-set to count down from 30 minutes, with options for the user to reset and resume the timer any time they wish by clicking on a button
- Task list, which enables an user to add a task to a list
- Notification message, which is displayed when the timer has counted down the full 30 minutes and is displayed even if the user has exited out of the Pomodoro App

## Struggles I encountered while coding the app

- Since this was the app I created for my CS 125 final project, I considered simply converting the code for that app from Java into Kotlin, but because I was wary of the errors that would pop up from doing that, and because I wanted to attempt making an app in Kotlin from scratch based on what I learned in class, I decided to start from scratch (hence the branch2).
  - I played around with fragments instead of activities for storing different features of the app; even after lots of research, I am not sure what the differences between the two are and when it is best to use one over the other, but it was interesting to observe some differences between how each work to create a functional app.
  - I attempted to create a timer with a circular progress ring around it, but because it was not working out well and I could not figure out how to make the ring get smaller with each passing second, I decided to focus on the bigger picture more, such as the specific features I wanted to have in this app.
  - I created layouts (home screen, timer, and task) for each fragment with additional features compared to the original app, such as a clear button for the task list and a home button on each of the timer and task fragments that would direct the user back to the home screen.
  - However, when it came to figuring out how to set the buttons up so that the user would be directed from one fragment to another, even after research, I could not figure it out. I am still learning how to use navGraphs and NavHostFragments so that a proper destination/movement can be set for each button.
- After that didn't work out so well, I decided to try give a go at converting the Java code for my original CS 125 final project app into Kotlin, and miraculously, it worked with very few errors!
  - There was only one major error that I had to fix, which was Android Studio saying that for two of my buttons (resume and restart buttons), smart cast was impossible because the button "may have been changed by this time."
  - I fixed this issue by making a local version of the button variables within the onCreate function in pomoActivity and using a utlity function that Kotlin provides.
  - I was able to figure out a way to set the timer to 30 minutes, the normal time for a Pomodoro technique study time. I also added a logo to the app home screen to make it more interesting.
  
## Future goals

- I would like to figure out how to create a circular progress bar that moves in response to the ticking time, because not only is that more aesthetically pleasing compared to mere numbers ticking dowin in the timer, it is easier for the user to see approximately how much time is left based on the percentage of the circular progress bar that is still remaining.
- I would like to be able to create functional buttons that direct the user back to the home screen if they wish while they are on the timer or task list page.
- I plan to do more research so that I can have the fragments become functional as well, allowing users to navigate between them. Then, I will be able to understand what the major differences between using activities versus fragments in an app are.

## Concluding thoughts

Although converting the Java code in my CS 125 final project app to Kotlin code to begin with would have been much smoother, easier, not to mention time-saving, I am glad that I chose to challenge myself because along the way, I was able to learn a great deal of new tasks I hadn't known how to accomplish before. I now understand how the different components of an app work together and some useful ways to do things that save time and make the whole process more efficient. I am definitely looking forward to improving upon this app even after this class ends so that I can do some more experimenting! 
