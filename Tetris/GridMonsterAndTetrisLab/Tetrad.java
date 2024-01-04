import java.awt.Color;

/**
 * Keeps track of an array of four Block, and the MyBoundedGrid<Block>
 * in which they live. The blocks have the ability to put themselves in
 * a grid or a specific location. It can also remove itself from the
 * grid, check if a specific location is empty and valid, and translate
 * across its existing grid.
 *
 * @author  Varun Bhupathi
 * @version Mar 15, 2023
 */
public class Tetrad
{
    private Block[] blocks;
    private MyBoundedGrid<Block> grid;
    private int rand;
    
    /**
     * Constructs a Tetrad object.
     * This object will have a random shape and color, and will appear
     * at the middle top of the parameter's grid, if possible.
     * 
     * @param grid  the grid in wich this Tetrad object will be
     */
    public Tetrad(MyBoundedGrid<Block> grid)
    {
        this.grid = grid;
        rand = (int)(Math.random()*7);
        int midCol = this.grid.getNumCols()/2;
        Location[] locs = new Location[4];
        blocks = new Block[4];
        Color colour = Color.RED;
        
        if(rand == 0)
        {
            locs[0] = new Location(1, midCol);
            locs[1] = new Location(0, midCol);
            locs[2] = new Location(2, midCol);
            locs[3] = new Location(3, midCol);
            colour = Color.RED;
        }
        else if(rand == 1)
        {
            locs[0] = new Location(0, midCol);
            locs[1] = new Location(0, midCol - 1);
            locs[2] = new Location(0, midCol + 1);
            locs[3] = new Location(1, midCol);
            colour = Color.GRAY;
        }
        else if(rand == 2)
        {
            locs[0] = new Location(0, midCol - 1);
            locs[1] = new Location(0, midCol);
            locs[2] = new Location(1, midCol - 1);
            locs[3] = new Location(1, midCol);
            colour = Color.CYAN;
        }
        else if(rand == 3)
        {
            locs[0] = new Location(1, midCol);
            locs[1] = new Location(0, midCol);
            locs[2] = new Location(2, midCol);
            locs[3] = new Location(2, midCol+1);
            colour = Color.YELLOW;
        }
        else if(rand == 4)
        {
            locs[0] = new Location(1, midCol);
            locs[1] = new Location(0, midCol);
            locs[2] = new Location(2, midCol);
            locs[3] = new Location(2, midCol-1);
            colour = Color.MAGENTA;
        }
        else if(rand == 5)
        {
            locs[0] = new Location(0, midCol);
            locs[1] = new Location(1, midCol);
            locs[2] = new Location(0, midCol+1);
            locs[3] = new Location(1, midCol-1);
            colour = Color.BLUE;
        }
        else if(rand == 6)
        {
            locs[0] = new Location(0, midCol);
            locs[1] = new Location(1, midCol);
            locs[2] = new Location(0, midCol-1);
            locs[3] = new Location(1, midCol+1);
            colour = Color.GREEN;
        }
        
        for(int i = 0; i < 4; i++)
        {
            blocks[i] = new Block();
            blocks[i].setColor(colour);
        }
        addToLocations(grid, locs);
    }

    /**
     * Puts the tetrad blocks at the locations given in the locs array.
     * 
     * @precondition    the tetrad blocks are not in any grid
     * 
     * @param grid  the grid in wich the blocks will be added
     * @param locs  the locations that these blocks will be put in the grid
     * 
     */
    private void addToLocations(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for(int i = 0; i < 4; i++)
        {
           blocks[i].putSelfInGrid(gr, locs[i]);
        }
    }
    
    /**
     * Remove the tetrad blocks from the grid 
     * while returning the locations where the blocks were.
     * 
     * @precondition the tetrad blocks are in the grid.
     *
     * @return locs  the locations of where the blocks previously were
     */
    private Location[] removeBlocks()
    {
        Location[] locs = new Location[4];
        for(int i = 0; i < 4; i++)
        {
            locs[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return locs;
    }
    
    /**
     * Answer the question whether or not all the locations in locs 
     * are valid and empty in the grid. It returns true only if all
     * the locations are valid and empty.
     * 
     * @param    grid    the grid in which these locations are checked
     * @param    locs    the locations in grid to be checked
     *
     * @return   true if all locations are empty and valid, otherwise;
     *          false
     */
    private boolean areEmpty(MyBoundedGrid<Block> gr, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            if(!gr.isValid(locs[i]) || gr.get(locs[i]) != null)
            {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Moves the tetrad deltaRow down and deltaCol columns to the right,
     * as long as the new positions are valid and empty.
     * The return value is true only if the translate is successful.
     * 
     * @param   deltaRow    the amount of rows this tetrad will move over
     * @param   deltaCol    the amount of columns this tetrad will move
     * 
     * @return  true if this tetrad succesfully translates, otherwise;
     *         false
     */
    public boolean translate(int deltaRow, int deltaCol)
    {
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        
        for(int i = 0; i < 4; i++)
        {
            newLocs[i] = new Location(oldLocs[i].getRow() + deltaRow,
                                      oldLocs[i].getCol() + deltaCol);
        }
        if(!areEmpty(grid, newLocs))
        {
            addToLocations(grid, oldLocs);
            return false;
        }
        addToLocations(grid, newLocs);
        return true;
    }
    
    /**
     * Causes the block to perform a 90-degree clockwise rotation about a 
     * point P0, wich is the first block listed in the blocks array. 
     * 
     * @return  true if the block was rotated, otherwise;
     *          false
     */
    public boolean rotate()
    {
        if(rand == 2)
            return true;
        
        Location[] oldLocs = removeBlocks();
        Location[] newLocs = new Location[4];
        int rowPiv = oldLocs[0].getRow();
        int colPiv = oldLocs[0].getCol();
        
        for(int i = 0; i < 4; i++)
        {
            newLocs[i] = new Location(rowPiv - colPiv + oldLocs[i].getCol(),
                                      rowPiv + colPiv - oldLocs[i].getRow());
        }
        if(!areEmpty(grid, newLocs))
        {
            addToLocations(grid, oldLocs);
            return false;
        }
        addToLocations(grid, newLocs);
        return true;
    }
}