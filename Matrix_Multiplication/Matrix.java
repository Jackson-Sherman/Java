
/**
 * Matrix interface
 *
 * @author Jackson Sherman
 * @version Apr 2, 2021
 */
public class Matrix
{
    public static class Integer extends Matrix
    {
        private int[][] arr;
        public final int rows;
        public final int cols;
        
        public Integer (int[][] matrix)
        {
            this.arr = matrix;
            this.rows = matrix.length;
            this.cols = matrix[0].length;
        }
        
        public Integer (int rows, int cols)
        {
            this.rows = rows;
            this.cols = cols;
            this.arr = new int[rows][cols];
            for (int r = 0; r < this.rows; r++)
            {
                for (int c = 0; c < this.cols; c++)
                {
                    if (r == c)
                    {
                        this.set(r, c, 1);
                    }
                    else
                    {
                        this.set(r, c, 0);
                    }
                }
            }
        }
        
        public Integer ()
        {
            this.arr = new int[0][0];
            this.rows = 0;
            this.cols = 0;
        }
        
        /**
         * Scalar multiplication
         * scales this Matrix instance
         *
         * @param  s  the value of the scalar
         */
        public void scale (int s)
        {
            for (int r = 0; r < this.rows; r++)
            {
                for (int c = 0; c < this.cols; c++)
                {
                    int val = s * this.get(r, c);
                    this.set(r, c, val);
                }
            }
        }
        
        public Matrix.Integer multiply (Matrix.Integer other)
        {
            Matrix.Integer output = new Integer(this.rows, other.cols);
            
            if (this.cols == other.rows)
            {
                for (int r = 0; r < output.rows; r++)
                {
                    for (int c = 0; c < output.cols; c++)
                    {
                        int val = 0;
                        for (int i = 0; i < this.cols; i++)
                        {
                            val += this.get(r, i) * other.get(i, c);
                        }
                        output.set(r, c, val);
                    }
                }
            }
            else if (this.rows == other.cols)
            {
                output = other.multiply(this);
            }
            else
            {
                output = new Integer();
            }
            return output;
        }
        
        public void set (int row, int col, int value)
        {
            this.arr[row][col] = value;
        }
        
        public int get (int row, int col)
        {
            return this.arr[row][col];
        }
        
        public String getString (int row, int col, int[] colWidths)
        {
            String output = String.valueOf(this.get(row, col));
            int width = colWidths[col];
            if (output.length() < width)
            {
                if (output.length() % 2 != width % 2)
                {
                    output = ' ' + output;
                }
                while (output.length() < width)
                {
                    output = ' ' + output + ' ';
                }
            }
            return output;
        }
        
        public int[] getColWidths ()
        {
            int[] output = new int[this.cols];
            for (int c = 0; c < this.cols; c++)
            {
                output[c] = 0;
                for (int r = 0; r < this.rows; r++)
                {
                    output[c] = Math.max(output[c], String.valueOf(this.get(r,c)).length());
                }
            }
            return output;
        }
        
        @Override
        public String toString ()
        {
            String output = "";
            int[] widths = this.getColWidths();
            for (int r = 0; r < this.rows; r++)
            {
                String line = "";
                for (int c = 0; c < this.cols; c++)
                {
                    line += ' ' + this.getString(r, c, widths);
                }
                line = line.substring(1);
                char[] brackets;
                if (this.rows == 1)
                {
                    brackets = new char[] {'[', ']'};
                }
                if (r == 0)
                {
                    brackets = new char[] {'\u250e', '\u2512'};
                }
                else if (r == this.rows - 1)
                {
                    brackets = new char[] {'\u2516', '\u251a'};
                }
                else
                {
                    brackets = new char[] {'\u2503', '\u2503'};
                }
                output += '\n';
                output += brackets[0] + line + brackets[1];
            }
            return output.substring(1);
        }
    }
    
    public static class Boolean extends Matrix
    {
        private boolean[][] arr;
        public final int rows;
        public final int cols;
        
        public Boolean (boolean[][] matrix)
        {
            this.arr = matrix;
            this.rows = matrix.length;
            this.cols = matrix[0].length;
        }
        
        public Boolean (int rows, int cols)
        {
            this.rows = rows;
            this.cols = cols;
            this.arr = new boolean[rows][cols];
            for (int r = 0; r < this.rows; r++)
            {
                for (int c = 0; c < this.cols; c++)
                {
                    if (r == c)
                    {
                        this.set(r, c, true);
                    }
                    else
                    {
                        this.set(r, c, false);
                    }
                }
            }
        }
        
        public Boolean (int[][] matrix)
        {
            this.rows = matrix.length;
            this.cols = matrix[0].length;
            this.arr = new boolean[rows][cols];
            for (int r = 0; r < this.rows; r++)
            {
                for (int c = 0; c < this.cols; c++)
                {
                    if (matrix[r][c] <= 0)
                    {
                        this.set(r, c, false);
                    }
                    else
                    {
                        this.set(r, c, true);
                    }
                }
            }
        }
        
        public Boolean ()
        {
            this.arr = new boolean[0][0];
            this.rows = 0;
            this.cols = 0;
        }
        
        /**
         * Scalar multiplication
         * scales this Matrix instance
         * does nothing to boolean
         *
         * @param  s  the value of the scalar
         */
        public void scale (int s)
        {
            
        }
        
        public Matrix.Boolean multiply (Matrix.Boolean other)
        {
            Matrix.Boolean output = new Boolean(this.rows, other.cols);
            
            if (this.cols == other.rows)
            {
                for (int r = 0; r < output.rows; r++)
                {
                    for (int c = 0; c < output.cols; c++)
                    {
                        boolean val = false;
                        for (int i = 0; i < this.cols && !val; i++)
                        {
                            val = val || this.get(r, i) && other.get(i, c);
                        }
                        output.set(r, c, val);
                    }
                }
            }
            else if (this.rows == other.cols)
            {
                output = other.multiply(this);
            }
            else
            {
                output = new Boolean();
            }
            return output;
        }
        
        public void set (int row, int col, boolean value)
        {
            this.arr[row][col] = value;
        }
        
        public boolean get (int row, int col)
        {
            return this.arr[row][col];
        }
        
        public String getString (int row, int col, int[] colWidths)
        {
            String output = this.get(row, col) ? "O" : "•";
            int width = colWidths[col];
            if (output.length() < width)
            {
                if (output.length() % 2 != width % 2)
                {
                    output = ' ' + output;
                }
                while (output.length() < width)
                {
                    output = ' ' + output + ' ';
                }
            }
            return output;
        }
        
        public int[] getColWidths ()
        {
            int[] output = new int[this.cols];
            for (int i = 0; i < this.cols; i++) output[i] = 1;
            return output;
        }
        
        @Override
        public String toString ()
        {
            String output = "";
            int[] widths = this.getColWidths();
            for (int r = 0; r < this.rows; r++)
            {
                String line = "";
                for (int c = 0; c < this.cols; c++)
                {
                    line += ' ' + this.getString(r, c, widths);
                }
                line = line.substring(1);
                char[] brackets;
                if (this.rows == 1)
                {
                    brackets = new char[] {'[', ']'};
                }
                if (r == 0)
                {
                    brackets = new char[] {'\u250e', '\u2512'};
                }
                else if (r == this.rows - 1)
                {
                    brackets = new char[] {'\u2516', '\u251a'};
                }
                else
                {
                    brackets = new char[] {'\u2503', '\u2503'};
                }
                output += '\n';
                output += brackets[0] + line + brackets[1];
            }
            return output.substring(1);
        }
    }
}