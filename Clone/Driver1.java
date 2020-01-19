import java.awt.Point;
import java.util.Vector;
public class Driver1
{
	static public void main(String[] args)
	{
		try
		{
			// get the method from the command line
			String meth;
			if((args.length == 1) && 
					((args[0].equals("deep")) || (args[0].equals("shallow"))))
			{
				meth = args[0];
			}
			else
			{
				System.out.println("Usage: java Driver1 [deep, shallow]");
				return;

			}
			// create original object
			Vector<Point> v1 = new Vector<>();
			Point p1 = new Point(1,1);
			v1.addElement(p1);
			// see what it is
			System.out.println("Original = " + v1);
			Vector<?> vNew = null;
			if(meth.equals("deep"))
			{
				// deep copy
				vNew = (Vector<?>)(ObjectCloner.deepCopy(v1));  // A
			}
			else if(meth.equals("shallow"))
			{
				// shallow copy
				vNew = (Vector<?>)v1.clone();                  // B
			}
			// verify it is the same
			System.out.println("New      = " + vNew);
			// change the original object's contents
			p1.x = 2;
			p1.y = 2;
			// see what is in each one now
			System.out.println("Original = " + v1);
			System.out.println("New      = " + vNew);
		}

		catch(Exception e)
		{
			System.out.println("Exception in main = " +  e);
		}
	}
}