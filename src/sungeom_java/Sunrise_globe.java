package sungeom_java;

class date_julian{
    int year;
    int month;
    int day;
}

public class Sunrise_globe {

	public static void main(String[] args) {
	    int n,m,i,j;
	    double i_d, j_d;
	    n = 180;
	    m = 360;
	    double res = 0.05; /* 0.05; */
	    int nThreads = 3;
	    int lat_size, lon_size;
	    lat_size = (int) (n/res);
	    lon_size = (int) (m/res);
	    i = 0;
	    j = 0;
	    double loc_longitude = 0.0;
	    double loc_latitude = 0.0;
	    
	    double[][] Longitudes = new double[lat_size][lon_size];
	    double[][] Latitudes = new double[lat_size][lon_size];
	    double[][] Sun_rise_hours = new double[lat_size][lon_size];

	    /* given day */
	    int year = 2017;
	    int month = 12;
	    int day = 5;
	    date_julian date_julian_par = new date_julian();
	    date_julian_par.year = year;
	    date_julian_par.month = month;
	    date_julian_par.day = day;
	    double sunrise_new;

	    /* time measures */
	    // clock_t start, end;
	    // clock_t start_s, end_s;
	    double cpu_time_used_sing, cpu_time_used_multi ;
	    
	    System.out.format("lat_size: %d\n",lat_size);
	    System.out.format("lon_size: %d\n",lon_size);
	    
		for (i = 0; i < lat_size; i++) {
			i_d = (double) i;
			loc_latitude = i_d * res - 90.0;
			for (j = 0; j < lon_size; j++) {
				j_d = (double) j;
				loc_longitude = j * res - 180.0;
				Longitudes[i][j] = loc_longitude;
				Latitudes[i][j] = loc_latitude;
				/* Actually only here the heap memory was allocated for this array */
				Sun_rise_hours[i][j] = 0.0;
			}
		}
		
		/* Calculation of sunrise itself */
		/* lat_size - runs in latitudes, lon_size - runs in longitudes */
		long start = System.currentTimeMillis();
		for (i = 0; i < lat_size; i++) {
			for (j = 0; j < lon_size; j++) {
				// sunrise_new = calculateSunrise(year, month, day, Latitudes[i][j],
				// Longitudes[i][j], 0, 0);
				/* lets try to use heap instead of stack which looks to be consumed here */
				sunrise_new = sungeom.calculateSunrise(date_julian_par.year, 
						date_julian_par.month, 
						date_julian_par.day,
						Latitudes[i][j], 
						Longitudes[i][j], 
						0, 
						0);

				
				if(Longitudes[i][j] == 0){ 
					System.out.format("Lat: %.2f\n",Latitudes[i][j]);
					System.out.format("sunrise_new: %.3f\n",sunrise_new); 
				}
				
				/* Storing into the output memory */
				Sun_rise_hours[i][j] = sunrise_new;
			}
		}
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
	    System.out.println("Single elapsed time[sec]: "+ (float) elapsedTime/1000.0);

	}

}
