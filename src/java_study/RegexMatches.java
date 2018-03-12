package java_study;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {

   public static void main( String args[] ) throws IOException {
      // String to be scanned to find the pattern.
      String line = "This order was placed for QT3000! OK?";
      String pattern = "(.*)(\\d+)(.*)";
      // (.*) - any single character
      // (\\d+) - matches one or more difits

      // Create a Pattern object
      Pattern r = Pattern.compile(pattern);

      // Now create matcher object.
      Matcher m = r.matcher(line);
      if (m.find( )) {
         System.out.println("Found value: " + m.group(0) );
         System.out.println("Found value: " + m.group(1) );
         System.out.println("Found value: " + m.group(2) );
      }else {
         System.out.println("NO MATCH");
      }
      
      RegexCouter.CountCats();
      testMetarTAFStrings();
      testRoadCastMetro();
   }
   
   public static void testMetarTAFStrings() {
	   String metarIn = "METAR LZTT 081400Z 25012KT 9999 FEW010 OVC046 03/M01 Q1006 NOSIG=";
	   String TAF_In = "SASQ LZTT 081400";
	   
	   String dayPattern = "(0?[1-9]|[12][0-9]|3[01])";
	   String minuteSecondPattern = "(0?[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0])";
	   
	   String metarPattern = String.format(" %s%s%sZ ", dayPattern, minuteSecondPattern, minuteSecondPattern);
	   String tafPattern = String.format(" %s%s%s", dayPattern, minuteSecondPattern, minuteSecondPattern);
	   
	   System.out.println("Matching pattern for metar's DDhhmmZ:" + metarPattern);
	   Pattern rMetar = Pattern.compile(metarPattern);
	   Pattern rTaf = Pattern.compile(tafPattern);
	   
	   Matcher mMetar = rMetar.matcher(metarIn);
	   Matcher mTaf = rTaf.matcher(TAF_In);
	   
	   // we replace with simple date formated datetime
	   Date now = new Date();
	   SimpleDateFormat ft = 
	    	      new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	   
	   
	   String mRet = mMetar.replaceFirst(String.format(" %s ",ft.format(now)));
	   String tRet = mTaf.replaceFirst(String.format(" %s", ft.format(now)));
	   
	   System.out.println(mRet);
	   System.out.println(tRet);
	   
   }
   
   public static void testRoadCastMetro() throws IOException {
	   String readcastXMLstr = new String(Files.readAllBytes(Paths.get("src\\java_study\\OutputRoadcastRWY0TDZ.xml")));
	   //System.out.print(readcastXMLstr.substring(0, 1000));
	   
	   String yearPattern = "((19|20)\\d\\d)";
	   String monthPattern = "(0?[1-9]|1[012])";
	   String dayPattern = "(0?[1-9]|[12][0-9]|3[01])";
	   String minuteSecondPattern = "(0?[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0])";
	   
	   // ISO_INSTANT: 2014-11-01T11:43:48.870Z regular expression to match the date
	   String smallStringToTestPattern = "<first-roadcast>2018-03-08T13:15Z</first-roadcast>";
	   String isoInstantDateTimePattern = String.format("%s-%s-%sT%s:%sZ",yearPattern, 
			   monthPattern, dayPattern, minuteSecondPattern, minuteSecondPattern);
	   
	   Pattern rRoadCast = Pattern.compile(isoInstantDateTimePattern);
	   
	   Matcher mRoadCastSmallString = rRoadCast.matcher(smallStringToTestPattern);
	   Matcher mRoadCast = rRoadCast.matcher(readcastXMLstr);
	   
	   // we replace with simple date formated datetime
	   Date now = new Date();
	   SimpleDateFormat ft = 
	    	      new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	   
	   
	   String roadcastRet = mRoadCastSmallString.replaceFirst(String.format("%s",ft.format(now)));
	   System.out.println(roadcastRet);
	   
	   String roadcastRetFull = mRoadCast.replaceAll(String.format("%s",ft.format(now)));
	   System.out.print(roadcastRetFull.substring(0, 1000));
   }
   
}


class RegexCouter{

	// Following is the example that counts the number of
	// times the word "cat" appears in the input string
	
	private static final String REGEX = "\\bcat\\b";
	private static final String INPUT = "cat cat cat cattie cat";

	public static void CountCats() {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(INPUT); // get a matcher object
		int count = 0;

		while (m.find()) {
			count++;
			System.out.println("Match number " + count);
			System.out.println("start(): " + m.start());
			System.out.println("end(): " + m.end());
		}
	}
}

