package snake.UI;

/**
 * @version 0.6 	8/8/2018 -BETA-
 * @version 0.6.1 	02/10/2018 -BETA-		Finished working Welcome screen
 * 
 * @category
 * A clone of the viral game snake
 * @v0.1 implemented a pause function, a count for the snake lenght, a simple not fully correct collision detection 
 * and a function for restarting the game
 * 
 * @v0.2 doesn't include any new functions the code was just made more readable and mantainable
 * the collision detection was made better
 * 
 * @v0.5 Added graphics to represent the snake and the apple and two background images but also a simple black background
 * The background can be chosen within the menu Bar - FOR FUTURE:--> more and better backgrounds 
 * Created a menu bar with the option to choose background and define the game speed between 3 diffrent types 
 * Also ption to control game loop out of the menu bar
 * 
 * @v0.6: -->
 * @v0.6.1 Newley created welcome screen with a more or less good looking backround image
 * In future it should be possible to chooese between different game modes out of it 
 * 
 * 
 * @control
 * The snake can be controlled with the arrow keys, 'Space' pauses the game and also continues it
 * After failing: by pressing 'R' the game is going to restart or pressing 'E' exits the game
 * 
 * @ToDoList
 * --> for finishing @v0.6 the display of the button must be improved radically also the size and position
 * adding more buttons is possible but implementing their function may be to much vor @v0.6
 * Maybe create sub menu of test button to start game right away with the desired game speed
 *  
 * 
 * @improvments v0.5 --> v0.6
 * <Currently> v0.6.1 Added a welcome screen with one test button starting the game 
 * 
 * 		
 * 
 * @ideas
 * 	1. implement a score system with randomly spawning double score coins
 *  2. export high scores into external files and read them to compare
 *  3. more different game modes 
 *  	--> decreasing of the delay (more game speed) by increasing the snake's lenght
 *  4. Two player mode --> Need of a second listener
 *  5. Implement a welcome screen with the opinion to choose the game Mode
 *  6. Maype a settings menu to choose the game speed
 *  7. Add system snakes to run against you 
 *  	--> need to check direction of them so they don't run out themselves
 *  		--> need to make prediction to their new head position depending on the direction in order to re-calculate 
 *  			--> some way of machine learning in a simple snake clone with java?? lol 
 * 
 * @author Julian Dauth 
 *
 */

public class SnakeGame {

	public static void main(String[] args) {
		
		new GameWindow();
	}

}
