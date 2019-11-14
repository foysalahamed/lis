
public class InBetweenDates {
	public int getNoOfDays(String fromDate,String toDate){
		int month[]=new int[]{0,31,28,31,30,31,30,31,31,30,31,30,31};
		int leap[]=new int[]{0,31,29,31,30,31,30,31,31,30,31,30,31};
		int totalDef=0;

		String fday=fromDate.substring(0, 2);
		String fmon=fromDate.substring(3, 6);
		String fyr=fromDate.substring(9, 11);
		int fMonth=montNumbering(fmon);
		int fda=Integer.parseInt(fday);
		int fyear=Integer.parseInt(fyr);
		
		///System.out.println("No month:"+nMonth);
		
		String tday=toDate.substring(0, 2);
		String tmon=toDate.substring(3, 6);
		String tyr=toDate.substring(9, 11);
		int tMonth=montNumbering(tmon);
		int tda=Integer.parseInt(tday);
		int tyear=Integer.parseInt(tyr);
		
		int defYear=tyear-fyear;
		System.out.println("year def:"+defYear);

		int fTotal=0;
		int tTotal=0;

		if(defYear==0){
			for(int i=1;i<=fMonth-1;i++){
				 fTotal=fTotal+month[i];
			}
			fTotal=fTotal+fda;
			System.out.println("FoTotalDay:"+fTotal+"Month:"+fMonth);
			
				for(int i=1;i<=tMonth-1;i++){
					 tTotal=tTotal+month[i];
				}
				tTotal=tTotal+tda;
				System.out.println("ToTotalDay:"+tTotal+"Month:"+tMonth);

		
			 totalDef=tTotal-fTotal;
			System.out.println("Total Def:"+ totalDef);
		}
			if(defYear>0){
				for(int i=1;i<=fMonth-1;i++){
					 fTotal=fTotal+month[i];
				}
				fTotal=fTotal+fda;
				int ffda=defYear*365-fTotal;
				System.out.println("one year def:"+ffda);

				for(int i=1;i<=tMonth-1;i++){
					 tTotal=tTotal+month[i];
				}
				tTotal=tTotal+tda;
				totalDef=tTotal-ffda;
			

				System.out.println("Second Option Toatl def:"+totalDef);

			}

		
		
		return totalDef;
	}

	private int montNumbering(String mon) {
		if(mon.equals("Jan")){
			return 1;
		}
		else if(mon.equals("Feb")){
			return 2;
		}
		else if(mon.equals("Mar")){
			return 3;
		}
		else if(mon.equals("Apr")){
			return 4;
		}
		else if(mon.equals("May")){
			return 5;
		}
		else if(mon.equals("Jun")){
			return 6;
		}
		else if(mon.equals("Jul")){
			return 7;
		}
		else if(mon.equals("Aug")){
			return 8;
		}
		else if(mon.equals("Sep")){
			return 9;
		}
		else if(mon.equals("Oct")){
			return 10;
		}
		else if(mon.equals("Nov")){
			return 11;
		}
		else if(mon.equals("Dec")){
			return 12;
		}
		else
		
		return 0;
	}

}
