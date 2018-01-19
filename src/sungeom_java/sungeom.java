package sungeom_java;

import java.lang.Math;

public final class sungeom {
	public static final double ZENITH = -0.83;
	
	public static double calculateSunrise(int year,int month,int day, double lat, double lng,int localOffset, int daylightSavings) {
		double sunrise_hours = 0.0;
	    /*
	    localOffset will be <0 for western hemisphere and >0 for eastern
	    hemisphere 

	    daylightSavings should be 1 if it is in effect during the summer
	    otherwise it should be 0
	    */
	    /* 1. first calculate the day of the year */
	    double N1 = Math.floor(275 * month / 9);
	    double N2 = Math.floor((month + 9) / 12);
	    double N3 = (1 + Math.floor((year - 4 * Math.floor(year / 4) + 2) / 3));
	    double N = N1 - (N2 * N3) + day - 30;

	    /* 2. convert the longitude to hour value and calculate an
	     * approximate time */
	    double lngHour = lng / 15.0;      
	    double t = N + ((6 - lngHour) / 24);   /* if rising time is desired: */
	    /* float t = N + ((18 - lngHour) / 24)  if setting time is desired: */

	    /* 3. calculate the Sun's mean anomaly */
	    double M = (0.9856 * t) - 3.289;

	    /* 4. calculate the Sun's true longitude */
	    // difference between C and Java - Java holds modulus of doubles by %, no fmod is needed
	    double L = (M + (1.916 * Math.sin((Math.PI/180)*M)) + (0.020 * Math.sin(2 *(Math.PI/180) * M)) + 282.634) % 360.0;

	    /* 5a. calculate the Sun's right ascension */
	    double RA = (180/Math.PI*Math.atan(0.91764 * Math.tan((Math.PI/180)*L))) % 360.0;

	    /* 5b. right ascension value needs to be in the same quadrant as L */
	    double Lquadrant  = Math.floor( L/90) * 90;
	    double RAquadrant = Math.floor(RA/90) * 90;
	    RA = RA + (Lquadrant - RAquadrant);

	    /* 5c. right ascension value needs to be converted into hours   */
	    RA = RA / 15;

	    /* 6. calculate the Sun's declination */
	    double sinDec = 0.39782 * Math.sin((Math.PI/180)*L);
	    double cosDec = Math.cos(Math.asin(sinDec));

	    /* 7a. calculate the Sun's local hour angle */
	    double cosH = (Math.sin((Math.PI/180)* ZENITH) - (sinDec * Math.sin((Math.PI/180)*lat))) / (cosDec * Math.cos((Math.PI/180)*lat));
	    /* this equations looks like it is valid just for northern hemisphere */
	    /*   
	    if (cosH >  1) 
	    the sun never rises on this location (on the specified date)
	    if (cosH < -1)
	    the sun never sets on this location (on the specified date)
	    */

	    /* 7b. finish calculating H and convert into hours */
	    double H = 360 - (180/Math.PI)*Math.acos(cosH);   //   if if rising time is desired:

	    /* float H = acos(cosH) if setting time is desired: */
	    H = H / 15;

	    /* 8. calculate local mean time of rising/setting   */
	    double T = H + RA - (0.06571 * t) - 6.622;

	    /* 9. adjust back to UTC */
	    double UT = (T - lngHour) % 24.0;

	    /* 10. convert UT value to local time zone of latitude/longitude */
	    sunrise_hours =  UT + localOffset + daylightSavings;		
			
		
		return sunrise_hours;
		
	}

}
