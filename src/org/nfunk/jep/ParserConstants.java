/* Generated By:JJTree&JavaCC: Do not edit this line. ParserConstants.java */
package org.nfunk.jep;

/**
 * 
 * @author matog
 * $Id: ParserConstants.java,v 1.2 2012/01/17 12:06:34 matog Exp $
 */
public interface ParserConstants {

  int EOF = 0;
  int INTEGER_LITERAL = 7;
  int DECIMAL_LITERAL = 8;
  int FLOATING_POINT_LITERAL = 9;
  int EXPONENT = 10;
  int STRING_LITERAL = 11;
  int INDENTIFIER1 = 12;
  int LETTER1 = 13;
  int DIGIT1 = 14;
  int INDENTIFIER2 = 15;
  int LETTER2 = 16;
  int DIGIT2 = 17;
  int ASSIGN = 18;
  int SEMI = 19;
  int COMMA = 20;
  int GT = 21;
  int LT = 22;
  int EQ = 23;
  int LE = 24;
  int GE = 25;
  int NE = 26;
  int AND = 27;
  int OR = 28;
  int PLUS = 29;
  int MINUS = 30;
  int MUL = 31;
  int DOT = 32;
  int DIV = 33;
  int MOD = 34;
  int NOT = 35;
  int POWER = 36;
  int CROSS = 37;
  int LSQ = 38;
  int RSQ = 39;
  int LRND = 40;
  int RRND = 41;

  int NO_DOT_IN_IDENTIFIERS = 0;
  int DEFAULT = 1;

  String[] tokenImage = {	// NOSONAR Malicious code vulnerability - Field should be moved out of an interface and made package protected  neriesime
    "<EOF>",
    "\" \"",
    "\"\\t\"",
    "\"\\n\"",
    "\"\\r\"",
    "<token of kind 5>",
    "<token of kind 6>",
    "<INTEGER_LITERAL>",
    "<DECIMAL_LITERAL>",
    "<FLOATING_POINT_LITERAL>",
    "<EXPONENT>",
    "<STRING_LITERAL>",
    "<INDENTIFIER1>",
    "<LETTER1>",
    "<DIGIT1>",
    "<INDENTIFIER2>",
    "<LETTER2>",
    "<DIGIT2>",
    "\"=\"",
    "\";\"",
    "\",\"",
    "\">\"",
    "\"<\"",
    "\"==\"",
    "\"<=\"",
    "\">=\"",
    "\"!=\"",
    "\"&&\"",
    "\"||\"",
    "\"+\"",
    "\"-\"",
    "\"*\"",
    "\".\"",
    "\"/\"",
    "\"%\"",
    "\"!\"",
    "\"^\"",
    "\"^^\"",
    "\"[\"",
    "\"]\"",
    "\"(\"",
    "\")\"",
  };

}
