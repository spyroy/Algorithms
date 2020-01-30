package AliceAndBob;

import java.util.Random;

/**
 * this class represents tha Alice and Bob problem. the problem is that Alice
 * and Bob are flipping a coin in a different room. Alice need to guess what Bob
 * got, and Bob need to guess what Alice got. befor the game begins they can
 * plane strategy. they win if one of them at least guessed right.
 * 
 * solution: Alice says what she got, and Bob says the opposite of what he got.
 * 
 * *AliceGot*||*BobGot*||*Alice and Bob says what they got (50%)*||*both says head (75%)*||*Alice says what she got and Bob says opposite (100%)*||
 *   tails	     tails		   		   won :)								  lost :(								    won :)
 *   tails	     head				   lost :(								  won :)								    won :)
 *   head		 tails				   lost :(								  won :)								    won :)
 *   head		 head				   won :)								  won :)								    won :)
 * 
 * @author spyro
 *
 */
public class CoinGame {

	// 100% chance wining
	public static void CoinGame100() {
		// coin < 0.5 = head, coin > 0.5 = tails
		double Acoin = Math.random();
		double Bcoin = Math.random();
		String Alicegot = "";
		String Bobgot = "";
		String AliceSay = "";
		String BobSay = "";
		if(Acoin < 0.5) {
			Alicegot = "head";
			AliceSay = "head";
		}
		else {
			Alicegot = "tails";
			AliceSay = "tails";
		}
		if(Bcoin < 0.5) {
			Bobgot = "tails";
			BobSay = "head";
		}
		else {
			Bobgot = "head";
			BobSay = "tails";
		}
		
		System.out.println("Alice got:" + Alicegot + " and Bob got:" + Bobgot);
		System.out.println("Alice says:" + AliceSay + " and Bob says:" + BobSay);
		if((AliceSay == Bobgot) || (BobSay == Alicegot))
			System.out.println("they won! :)");
		else
			System.out.println("they lost :(");

	}
	
	// 75% chance wining
	public static void CoinGame75() {
		// coin < 0.5 = head, coin > 0.5 = tails
		double Acoin = Math.random();
		double Bcoin = Math.random();
		String Alicegot = "";
		String Bobgot = "";
		String AliceSay = "";
		String BobSay = "";
		if(Acoin < 0.5) {
			Alicegot = "head";
			AliceSay = "head";
		}
		else {
			Alicegot = "tails";
			AliceSay = "head";
		}
		if(Bcoin < 0.5) {
			Bobgot = "tails";
			BobSay = "head";
		}
		else {
			Bobgot = "head";
			BobSay = "head";
		}
		
		System.out.println("Alice got:" + Alicegot + " and Bob got:" + Bobgot);
		System.out.println("Alice says:" + AliceSay + " and Bob says:" + BobSay);
		if((AliceSay == Bobgot) || (BobSay == Alicegot))
			System.out.println("they won! :)");
		else
			System.out.println("they lost :(");

	}
	
	// 50% chance wining
	public static void CoinGame50() {
		// coin < 0.5 = head, coin > 0.5 = tails
		double Acoin = Math.random();
		double Bcoin = Math.random();
		String Alicegot = "";
		String Bobgot = "";
		String AliceSay = "";
		String BobSay = "";
		if(Acoin < 0.5) {
			Alicegot = "head";
			AliceSay = "head";
		}
		else {
			Alicegot = "tails";
			AliceSay = "tails";
		}
		if(Bcoin < 0.5) {
			Bobgot = "tails";
			BobSay = "tails";
		}
		else {
			Bobgot = "head";
			BobSay = "head";
		}
		
		System.out.println("Alice got:" + Alicegot + " and Bob got:" + Bobgot);
		System.out.println("Alice says:" + AliceSay + " and Bob says:" + BobSay);
		if((AliceSay == Bobgot) || (BobSay == Alicegot))
			System.out.println("they won! :)");
		else
			System.out.println("they lost :(");

	}
	
	public static void main(String[] args) {
		CoinGame100();
		CoinGame75();
		CoinGame50();
	}
	
}