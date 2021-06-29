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
      
      // Test for matching names of Channels
      String chanName = "Test_TimeTreeLogImprTerminal";
      String patternChan = "Test_*";
      
      Pattern rCh = Pattern.compile(patternChan);
      Matcher mCh = rCh.matcher(chanName);
      if (mCh.find()) {
    	  System.out.println("Found channel: " + mCh.group(0));
      }
      
      RegexCouter.CountCats();
      testMetarTAFStrings();
      testRoadCastMetro();
      testNonCapturingGroups();
      TestNetCDF_IvansPattern.compileTestRegexp();
   }
   
   public static void testMetarTAFStrings() {
	   String metarIn = "METAR LZTT 081400Z 25012KT 9999 FEW010 OVC046 03/M01 Q1006 NOSIG=";
	   // String metarIn = "MET REPORT LZIB 141130Z AUTO WIND RWY 31 TDZ 290/8KT VRB BTN 260/ AND 320/ RWY 22 END 300/10KT VRB BTN 270/ AND 340/ VIS 10KM CLD NSC T23 DP12 QNH 1017HPA TREND NOSIG ";
	   String TAF_In = "SASQ LZTT 081400";
	   String Inserter_sample = "AWSSTAT,018,2018-03-11 00:02:26\\n018,2018-03-11 00:02:26,startTime=2018-03-11 00:02:23,connectTime=2018-03-11 00:02:23,l\r\n" + 
	   		"astReplyTime=2018-03-11 00:02:26,endTime=2018-03-11 00:02:26,tries=0,cq=9.999993,statusCode=OK,messages=Downloaded 1 new@histOrNew 'mlg' records.,err=null,connectDeta\r\n" + 
	   		"il=null";
	   
	   String dayPattern = "(0[1-9]|[12][0-9]|3[01])";
	   String hourPattern = "(0[0-9]|1[0-9]|2[0-9])";
	   String minuteSecondPattern = "(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0])";
	   String inserterPattern = "AWSSTAT";
	   
	   String metarPattern = String.format(" %s%s%s", dayPattern, hourPattern, minuteSecondPattern);
	   String tafPattern = String.format(" %s%s%s", dayPattern, hourPattern, minuteSecondPattern);
	   
	   System.out.println("Matching pattern for metar's DDhhmmZ:" + metarPattern);
	   Pattern rMetar = Pattern.compile(metarPattern);
	   Pattern rTaf = Pattern.compile(tafPattern);
	   Pattern rInserterPattern = Pattern.compile(inserterPattern);
	   
	   Matcher mMetar = rMetar.matcher(metarIn);
	   Matcher mTaf = rTaf.matcher(TAF_In);
	   Matcher mInserterPattern = rInserterPattern.matcher(Inserter_sample);
	   
	   // we replace with simple date formated datetime
	   Date now = new Date();
	   SimpleDateFormat ft = 
	    	      new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	   
	   
	   String mRet = mMetar.replaceFirst(String.format(" %s",ft.format(now)));
	   String tRet = mTaf.replaceFirst(String.format(" %s", ft.format(now)));
	   
	   System.out.println(mRet);
	   System.out.println(tRet);
	   
	   SimpleDateFormat ft2 = 
	    	      new SimpleDateFormat ("ddHHmm");
	   
	   mRet = mMetar.replaceFirst(String.format(" %s ",ft2.format(now)));
	   System.out.println(mRet);
	   
	   System.out.println("Replaced: " + mInserterPattern.replaceFirst("REPLACED"));
	   
	   
   }
   
   public static void testRoadCastMetro() throws IOException {
	   String readcastXMLstr = new String(Files.readAllBytes(Paths.get("src\\java_study\\OutputRoadcastRWY0TDZ.xml")));
	   //System.out.print(readcastXMLstr.substring(0, 1000));
	   
	   String yearPattern = "((19|20)\\d\\d)";
	   String monthPattern = "(0[1-9]|1[012])";
	   String dayPattern = "(0[1-9]|[12][0-9]|3[01])";
	   String minuteSecondPattern = "(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0])";
	   
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
   
   public static void testNonCapturingGroups() {
	   String SPSQ_example = "SPSQ LZTT 041024\r\n" + 
	   		"SPECI LZTT 041024Z 05002KT CAVOK M08/M14 Q1005=";
	   String SASQ_example = "SASQ LZTT 041530\r\n" + 
	   		"METAR LZTT 041530Z VRB02KT CAVOK M03/M12 Q1004 NOSIG=";
	   
	   String SNOWTAM_example = "SWLZ0304 LZTT 03040607\r\n" + 
	   		"(SNOWTAM 0304\r\n" + 
	   		"A) LZTT\r\n" + 
	   		"B) 03040607\r\n" + 
	   		"C) 09 F)NIL/NIL/NIL G)XX/XX/XX H)5/5/5\r\n" + 
	   		"N) NIL\r\n" + 
	   		"R) NIL\r\n" + 
	   		"T) DANGEROUS CONDITION CEASED)";
	   
	   System.out.println("");
	   System.out.format("SPSQ_example: %s", SPSQ_example);
	   System.out.println("");
	   System.out.format("SASQ_example: %s", SASQ_example);
	   System.out.println("");
	   System.out.format("SNOWTAM_example: %s", SNOWTAM_example);
	   
	   String monthPattern = "(0[1-9]|1[012])";
	   String dayPattern = "(0[1-9]|[12][0-9]|3[01])";
	   String hourPattern = "(0[0-9]|1[0-9]|2[0-9])";
	   String minuteSecondPattern = "(0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|6[0])";
	   
	   String SPSQ_pattern1 = String.format("(?<=^SPSQ LZTT )(%s%s%s)",dayPattern, hourPattern, minuteSecondPattern); //(^SPSQ LZTT )
	   System.out.println(SPSQ_pattern1);
	   Pattern SPSQ_pattern1Comp = Pattern.compile(SPSQ_pattern1);
	   Matcher SPSQ_pattern1Matcher = SPSQ_pattern1Comp.matcher(SPSQ_example);
	   
	   String SPSQ_pattern2 = String.format("(?<=LZTT )(%s%s%sZ)", dayPattern, hourPattern, minuteSecondPattern);
	   System.out.println(SPSQ_pattern2);
	   Pattern SPSQ_pattern2Comp = Pattern.compile(SPSQ_pattern2);
	   
	   String SNOWTAM_pattern1 = String.format("(?<=^SWLZ)(%s%s)", monthPattern, dayPattern);
	   String SNOWTAM_pattern2 = String.format("(?<=^SWLZ%s%s LZTT )(%s%s%s%s)", monthPattern, dayPattern, monthPattern, dayPattern,
			   hourPattern, minuteSecondPattern);
	   
	// we replace with simple date formated datetime
	   Date now = new Date();
	   SimpleDateFormat ft = 
	    	      new SimpleDateFormat ("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	   SimpleDateFormat ft2 = 
	    	      new SimpleDateFormat ("MMdd");
	   
	   String SPSQ_patternRet = SPSQ_pattern1Matcher.replaceAll(String.format("%s",ft.format(now)));
	   Matcher SPSQ_pattern2Matcher = SPSQ_pattern2Comp.matcher(SPSQ_patternRet);
	   SPSQ_patternRet = SPSQ_pattern2Matcher.replaceAll(String.format("%s",ft.format(now)));
	   
	   System.out.println("");
	   System.out.println(SPSQ_patternRet);
	   
	   System.out.println(SNOWTAM_pattern1);
	   System.out.println(SNOWTAM_pattern2);
	   Pattern SNOWTAM_pattern1Comp = Pattern.compile(SNOWTAM_pattern1);
	   Matcher SNOWTAM_pattern1Matcher = SNOWTAM_pattern1Comp.matcher(SNOWTAM_example);
	   String SNOWTAM_example_ret = SNOWTAM_pattern1Matcher.replaceAll(String.format("%s",ft2.format(now)));
	   
	   Pattern SNOWTAM_pattern2Comp = Pattern.compile(SNOWTAM_pattern2);
	   Matcher SNOWTAM_pattern2Matcher = SNOWTAM_pattern2Comp.matcher(SNOWTAM_example_ret);
	   SNOWTAM_example_ret = SNOWTAM_pattern2Matcher.replaceAll(String.format("%s",ft.format(now)));
	   
	   System.out.println("");
	   System.out.println(SNOWTAM_example_ret);
	   //System.out.println(SPSQ_pattern1Matcher.group());
	   // We continue on Monday / I want to study in the afternoon
	   
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

class TestNetCDF_IvansPattern{
	// Original pattern - not possible to compile with python 're' module - incompatible
	//private static final String REGEX = ".*[/\\\\](?<prodName>abha)[/\\\\](?<timestamp>\\d{8}[/\\\\]\\d{6})\\.nc.gz$";
	
	private static final String REGEX = ".*/(?<prodName>abha)/(?<timestamp>\\d{8}/\\d{6})\\.nc.gz$";
	// Linux version
	private static final String INPUT = ".*927/netCDF/Abha/20180313/120000.nc.gz";
	// Windows version
	//private static final String INPUT = "\\927\\netCDF\\Abha\\20180313\\120000.nc.gz";
	
	public static void compileTestRegexp() {
		Pattern p = Pattern.compile(REGEX);
		Matcher m = p.matcher(INPUT.toLowerCase());
		
		if (m.find( )) {
	         System.out.println("Found value: " + m.group(0) );
	         System.out.println("Found value: " + m.group(1) );
	         System.out.println("Found value: " + m.group(2) );
	         System.out.println("Found timestamp: " + m.group("timestamp"));
	      }else {
	         System.out.println("NO MATCH");
	      }
	}
}

