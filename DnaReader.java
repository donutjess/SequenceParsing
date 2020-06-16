import java.util.*;
// random DNA generator: https://faculty.ucr.edu/~mmaduro/random.htm
// good sequence - CAACCGGGGGGTGGGAATCCGTCACATATGAGAAGGTATTTGCTCGATAATCAATACTCCAGGCATCTAACTTTTCCCACTGCCTTAAGCCGGCTTGCCCTTTCTGCCTGTAGATCCATAGGACTCGTGCCAACGCGCAGGCATAGTTCGAGGAGAAATATCCGGGGCCAAAGACAACCAGCATCTCGGGTCTTGCCCAA

public class DnaReader {
	public static void main(String[] args) {
		System.out.println("Is your DNA sequence of the template or coding strand?"
			+ " Type \"template\", \"coding\", or \"not sure\"");
			//template = antisense, coding = sense strand. antisense or template strand is transcribed
		Scanner scan = new Scanner(System.in);
		String seqSource = scan.nextLine();
		System.out.println("Enter your DNA sequence here");
		String preseq = scan.nextLine();
		scan.close();
		String sequence = preseq.toUpperCase();
		int length = sequence.length();
		
		RnaAndProtein run = new RnaAndProtein(sequence, length);
		
	//transcribe
		if (seqSource.equalsIgnoreCase("template")){
			System.out.println("\nYour RNA sequence is " + run.transcribe()); 
		}
		else if (seqSource.equalsIgnoreCase("coding")) {
			run.flip();
			System.out.println("\nYour RNA sequence is " + run.transcribe());
		}
		
	//translate	
		if (seqSource.equalsIgnoreCase("not sure")) {
			System.out.println("\nIf your sequence is the template strand...");
			System.out.println("Your RNA sequence is "+ run.transcribe()); 
			System.out.println("And your protein sequence is " + run.translation());
			run.newRun();
			run.flip();
			System.out.println("\nOr if your sequence is the coding strand...");
			System.out.println("Your RNA sequence is "+ run.transcribe()); 
			System.out.println("And your protein sequence is " + run.translation());
		}
		else {
			System.out.println("Your protein sequence is " + run.translation());
		}
	}
}
