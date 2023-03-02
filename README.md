Overview:
In order to make the game function the way it is intended to, we had to create a total of 8 classes other than the ones
that we were given (which were the App and Constant class). These included our PaneOrganizer, Game, and Doodle class, along
with the Platform superclass and its subclasses Standard, Disappearing, Moving, and ExtraBouncy. The PaneOrganizer class
was used to create the basic visual/graphical foundation of our game, like a button and a label. The Game class was
responsible for the logical components, like having a timeline that updates and allows the doodle to move, as well as
constantly checking for intersects, scrolling the platform when the doodle goes past the midpoint, generating new platforms,
allowing the doodle to wrap, stopping itself (the timeline) if the doodle falls below the screen. It was also responsible
for updating the score everytime the screen scrolls up, and allowing the player to move the doodle left or right.
For the doodle class, this is where the doodle was created and given basic methods that allow it to move left or right
(that are called within the KeyEvent in Game), return and change X and Y values, calculate the new position by using
constants within a pair of equations that yield the updated velocity and updated position. Lastly, the Platform superclass
was used to grant basic traits to the subclasses, like an X value, Y value, color, pane, and doodle. It also includes a
movePlatform method that is overridden in the Moving subclass (which uses a timeline to move itself when generated on screen).
The disappearing and extra bouncy subclasses only inherited the constructors parameters.


Design Choice:
In our program we decided to use a superclass of Platform. This is because we had 4 different platforms, but they each had
 the same structure and process to be added on the screen. In the Moving Class we also decided to use another timeline because
 we wanted to have our moving Platform keep on moving until the character falls off the screen. Furthermore we delegate the
 methods describing the movement of the doodle on the screen and also its location in the doodle class. We decided in our
 Game Class to have checkExtraBouncy and checkIntersect as different methods because if the platform was blue we could set
 the rebound velocity as an extraRebound. In the Game class we also used a switch statement in order to randomize the platform generated.
 However we added multiple standard platforms in order to make the game more playable. Additionally we used an arraylist
 because we don't know the number of Platforms and therefore need an Arraylist which can manipulate its size
 Lastly in the Game Class we created our timeline because the Game class is responsible for the game logic. Therefore it
 has the task to keep the game running until our Player falls off the screen.


