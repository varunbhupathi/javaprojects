import java.util.*;

/**
 * Creates the game of Alexey Pajitnov's Tetris.
 * A puzzle game in wich 4 blocks in different orrientations
 * fall from above and using the arrow and space keys, the player must
 * guide the block to complete a row. When this happens the completed
 * row's blocks will disappear. A player loses when there is no more
 * room for an additional block.
 *
 * @author  Varun Bhupathi
 * @version Mar 15, 2023
 */
public class Tetris implements ArrowListener
{
    private MyBoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad rad;
    private int score;
    
    /**
     * Constructs a Tetris Object.
     */
    public Tetris()
    {
        grid = new MyBoundedGrid(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        score = 0;
        display.showBlocks();
        rad = new Tetrad(grid);
        display.showBlocks();
        display.setArrowListener(this);
    }
    
    /**
     * When the up key is pressed the curent tetrad
     * roates clockwise.
     */
    @Override
    public void upPressed()
    {
        rad.rotate();
        display.showBlocks();
    }
    
    /**
     * When the down key is pressed the curent tetrad
     * moves down one row.
     */
    @Override
    public void downPressed()
    {
        rad.translate(1, 0);
        display.showBlocks();
    }
    
    /**
     * When the left key is pressed the curent tetrad
     * moves left one column.
     */
    @Override
    public void leftPressed()
    {
        rad.translate(0, -1);
        display.showBlocks();
    }
    
    /**
     * When the right key is pressed the curent tetrad
     * moves right one column.
     */
    @Override
    public void rightPressed()
    {
        rad.translate(0, 1);
        display.showBlocks();
    }
    
    /**
     * When a space bar is pressed, a “hard drop” is triggered.
     * The current tetrad drops straight down as far as possible.
     */
    @Override
    public void spacePressed()
    {
        while(rad.translate(1, 0))
        {
        }
        display.showBlocks();
    }
    
    /**
     * Repeatedly pauses the tetrad for 1 second then
     * moves the active tetrad down one row, and redraws the display.
     * Once the Tetrad can't move anymore it is stopped and a new
     * Tetrad is dropped from above.
     * Will restart the game if there is no more room for another Tetrad.
     */
    public void play()
    {
        while(true)
        {
            try 
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException e)
            {
                
            }
            if(!rad.translate(1, 0))
            {
                clearCompletedRows();
                for(Location loc : grid.getOccupiedLocations())
                {
                    if(loc.getRow() == 0)
                    {
                        System.out.println("GAME OVER \nRestarting Game \n");
                        clearBoard();
                        score = 0;
                        display.setTitle("Tetris");
                    }
                }
                rad = new Tetrad(grid);
            }
            display.showBlocks();
        }
    }
    
    /**
     * Clears the board completly of all blocks.
     */
    private void clearBoard()
    {
        for(int c = 0; c < grid.getNumCols(); c++)
        {
            for(int r = 0; r < grid.getNumRows(); r++)
            {
                Block bloc = grid.get(new Location(r, c));
                if(bloc != null)
                    bloc.removeSelfFromGrid();
            }
        }
    }
    
    /**
     * Returns true if and only if every cell in a given row is occupied.
     * 
     * @precondition row is in the range of [0, number of rows)
     * 
     * @param row   the row that's checked if every space is empty
     * 
     * @return true if and only if every cell in a given row is occupied; otherwise
     *         false
     */
    private boolean isCompletedRow(int row)
    {
        for(int c = 0; c < grid.getNumCols(); c++)
        {
            if(grid.get(new Location(row, c)) == null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Removes every block in a given row and 
     * every block above the row moves down one row.
     * 
     * @precondition row is filled with blocks
     * @precondition row is in the range of [0, number of rows)
     * 
     * @param row   the row that's cleared
     */
    private void clearRow(int row)
    {
        for(int c = 0; c < grid.getNumCols(); c++)
        {
            Block bloc = grid.get(new Location(row, c));
            bloc.removeSelfFromGrid();
            for(int r = row - 1; r >= 0; r--)
            {
                Block bloc2 = grid.get(new Location(r, c));
                if(bloc2 != null)
                {
                    bloc2.moveTo(new Location(r+1, c));
                }
            }
        }
    }
    
    /**
     * Clears all completed rows before exiting, even rows that 
     * are completed due to falling blocks
     */
    private void clearCompletedRows()
    {
        for(int r = 0; r < grid.getNumRows(); r++)
        {
            if(isCompletedRow(r))
            {
                clearRow(r);
                score += 1000;
                display.setTitle("Tetris" + " Score: " + score);
            }
        }
    }
    
    /**
     * Manges and runs the game of TETRIS.
     * 
     * @param  args         array with information that may be passed
     *                      at start of processing
     */
    public static void main(String[] args)
    {
        Tetris tet = new Tetris();
        tet.play();
    }
}