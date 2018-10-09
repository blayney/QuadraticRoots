import java.util.Scanner;

public class mainMethod {
	
    private static class NonStandardN {
        double r1, r2;
 
        public NonStandardN(double r1, double r2) {
        	//declare / instant versions of roots
            this.r1 = r1;
            this.r2 = r2;
        }
        
        @Override
        public boolean equals(Object obj) {
        	
            if (obj == this) {return true;}
            //check consistency in object instances
            if (!(obj instanceof NonStandardN)) {return false;}
            NonStandardN other = (NonStandardN) obj;
            //handle nonstandard input
            
            return (r1 == other.r1) && (r2 == other.r2);
        }
 //convert plus/minus ops to strings in out method
        @Override
        public String toString() {
            if (r2 == 0.0) {return String.format("%g", r1);}
            if (r1 == 0.0) {return String.format("%gi", r2);}
            return String.format("%g %c %gi", r1,
                (r2 < 0.0 ? '-' : '+'), Math.abs(r2));
        }
    }
 //using eq to calculate root info, b^2 -4ac = conditional
    private static NonStandardN[] quadraticRoots(double a, double b, double c) {
        NonStandardN[] roots = new NonStandardN[2];
        double d = b * b - 4.0 * a * c;
        double aa = a + a;
 
        //if less than 0, there are 2 roots
        if (d < 0.0) {
            double re = -b / aa;
            double im = Math.sqrt(-d) / aa;
            roots[0] = new NonStandardN(re, im);
            roots[1] = new NonStandardN(re, -im);
        //if greater than 0, there are no REAL roots and r1,r2 are unreal
        } else if (b < 0.0) {
            double re = (-b + Math.sqrt(d)) / aa;
            roots[0] = new NonStandardN(re, 0.0);
            roots[1] = new NonStandardN(c / (a * re), 0.0);
        //if equal to 0, there is only 1 real root unless NonStandard vars are used
        } else {
            double re = (-b - Math.sqrt(d)) / aa;
            roots[1] = new NonStandardN(re, 0.0);
            roots[0] = new NonStandardN(c / (a * re), 0.0);
        }
        return roots;
    }
 //output results and calculate a, b and c values for multiple
    //instances of the calculation
    public static void main(String[] args) {
    	//init all vars
    	double a1 = 0,b1 = 0,c1 = 0,a2 = 0,b2 = 0,c2 = 0;
    	double a3 = 0,b3 = 0,c3 = 0,a4 = 0,b4 = 0,c4 = 0;
    	double a5 = 0,b5 = 0,c5 = 0,a6 = 0,b6 = 0,c6 = 0;
    	//assign bounds to generation of a, b and c
    	Double min = -10.0;
    	Double max = 10.0;
    	//generate random a, b and c
    	
/*    	a1 = (Math.random()*((max-min)+1))+min;
    	a2 = (Math.random()*((max-min)+1))+min;
    	a3 = (Math.random()*((max-min)+1))+min;
    	a4 = (Math.random()*((max-min)+1))+min;
    	a5 = (Math.random()*((max-min)+1))+min;
    	a6 = (Math.random()*((max-min)+1))+min;
    	
    	b1 = (Math.random()*((max-min)+1))+min;
    	b2 = (Math.random()*((max-min)+1))+min;
    	b3 = (Math.random()*((max-min)+1))+min;
    	b4 = (Math.random()*((max-min)+1))+min;
    	b5 = (Math.random()*((max-min)+1))+min;
    	b6 = (Math.random()*((max-min)+1))+min;
    	
    	c1 = (Math.random()*((max-min)+1))+min;
    	c2 = (Math.random()*((max-min)+1))+min;
    	c3 = (Math.random()*((max-min)+1))+min;
    	c4 = (Math.random()*((max-min)+1))+min;
    	c5 = (Math.random()*((max-min)+1))+min;
    	c6 = (Math.random()*((max-min)+1))+min;*/
    	Scanner input = new Scanner(System.in);
    	System.out.println("Please enter an a b and c value");
    	a1 = input.nextDouble();
    	b1 = input.nextDouble();
    	c1 = input.nextDouble();
    	//output 6 versions of these
        double[][] equations = {
            {a1, b1, c1},   
       
        };
        //format these results in a readable way
        for (int i = 0; i < equations.length; i++) {
            NonStandardN[] roots = quadraticRoots(
                equations[i][0], equations[i][1], equations[i][2]);
            System.out.format("%na = %g   b = %g   c = %g%n",
                equations[i][0], equations[i][1], equations[i][2]);
            if (roots[0].equals(roots[1])) {
                System.out.format("X1,2 = %s%n", roots[0]);
            } else {
                System.out.format("X1 = %s%n", roots[0]);
                System.out.format("X2 = %s%n", roots[1]);
            }
        }
    }
}