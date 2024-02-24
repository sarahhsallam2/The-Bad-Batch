package csen703.main.assignment2;
import java.util.*;



public class TheBadBatchPath {

	public static Integer TatooineToNabooDP(int [] fuel) {
	int n = fuel.length;
	ArrayList<Integer> fuelTemp = new ArrayList<>(n);
	for (int i = 0; i < n; i++) {
	   fuelTemp.add(0); // adding the integer 0 to arraylist n times
	}
		return DPhelper(fuel,0,fuelTemp);
	}
	
	public static int DPhelper(int[] fuel, int start, ArrayList<Integer> fuelTemp) {
	    int n = fuel.length;
	     
	    if (start >= n - 1) {       //lw el start b3d el length fa 0 kda kda
	        return 0;
	    }
	    if (fuelTemp.get(start) != 0) { //mmkn tekoon b 3 fa sa3tha i want value dahh 3ashan el recursive call 3ashan 0 means i did not calculate it :(((
	        return fuelTemp.get(start);
	    }
	    
	    int a = fuel[start];
	    if (a == 0) {
	        fuelTemp.set(start, -1);
	        return -1; //no possible jumps
	    }

	    int minOfJumps = -1;
	    for (int i = 1; i <= a && start + i < n; i++) {
	        int jumps = DPhelper(fuel, start + i, fuelTemp);  //find min jumps to next planett
	        if (jumps != -1) {
	            if (minOfJumps == -1 || jumps + 1 < minOfJumps) {
	                minOfJumps = jumps + 1;
	            }
	        }
	    }
	    fuelTemp.set(start, minOfJumps);

	    return minOfJumps;
	}


	
	public static boolean TatooineToNabooGreedy(int [] fuel) {
		int size = fuel.length;
		int counter=0;
		if(size==0 || size==1 )
			return true;
		for(int i= size-2 ;i>=0 ; i--) {
			int currentFuel=fuel[i];
			
			if(currentFuel!=0 && i==0 && counter<currentFuel) {
				return true ;
			}
			else
				if (currentFuel==0 ) {
		        counter++;
			      }
			    else {
				      if(!(currentFuel>counter) && i==0 ) {
					     return false ;
				        }
				      else if(!(currentFuel>counter) ) {
					     counter++;
				        }
				      else {
					    counter=0;
				           }
		             	}
			
	   }
	
		return false;
	}
	

	public static ArrayList<Integer> TatooineToNabooPath(int[] fuel){
		    int n = fuel.length;
		    int[] min = new int[n]; //min Number to be counted for jumps 
		    int[] jump = new int[n]; //total Jumps
		    return helperForPath(fuel, min,jump);
	}

	public static ArrayList<Integer> helperForPath(int[] fuel, int[] minNumberForJump, int[] TotalJumps) {
		  int n = fuel.length;
		  int minJumps=TatooineToNabooDP(fuel);
		  for(int i =0;i<minNumberForJump.length;i++) {
			  minNumberForJump[i]=214748364;  //aka the max valueee
		  }
	      minNumberForJump[0] = 0; 
	      for (int i = 0; i < fuel.length; i++) { 
	          int maxJump = fuel[i]; //number of jumps ely ba3melha men current beta3i
	          for (int j = 1; j <= maxJump && i + j < n; j++) {
	              int naboo = i + j; //lel planet elt gai
	              if (minNumberForJump[i] + 1 < minNumberForJump[naboo]) {
	            	  minNumberForJump[naboo] = minNumberForJump[i] + 1; //min number:)
	            	  TotalJumps[naboo] = i;
	              }
	          }
	      }
//	      for(int i =0;i<minNumberForJump.length;i++) {
//	    	  System.out.println("min Number of: "+ i + "  " +  minNumberForJump[i]);
//	      }

	    ArrayList<Integer> finalArrayList = new ArrayList<>();
	    for (int i = fuel.length - 1; i > 0; i = TotalJumps[i]) {
	    	finalArrayList.add(i);
	    }
	    finalArrayList.add(0);  //first one m3ana
	      

	    reverseIt(finalArrayList);
	    return finalArrayList;
	}
	public static void reverseIt(ArrayList<Integer> array) {
		int start = 0;
	    int end = array.size() - 1;
	    while (start < end) {
	        int temp = array.get(start);
	        array.set(start, array.get(end));
	        array.set(end, temp);
	        start++;
	        end--;
	    }
	}
    
	public static void main(String[] args) {
		int [] fuel= {2,3,1,1,4};
		ArrayList<Integer> finalSol = TatooineToNabooPath(fuel);
		int n =TatooineToNabooDP(fuel);
		for(int i=0;i<finalSol.size();i++) {
			System.out.println(finalSol.get(i));
			
		}
		System.out.println("First method sol " + n);

	}
	
}


