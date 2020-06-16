		
public class RnaAndProtein {
	String sequence;
	int length = 0;
	String trans = "";
	String protein = "";
	
	public RnaAndProtein(String sequence, int length) {
		this.sequence = sequence;
		this.length = length;
	}

	public void newRun() {
		this.trans = "";
		this.protein = "";
	}
	
	public void flip() {
		String temp = "";
		for(int i = 0; i < length; ++i) {
			char current = sequence.charAt(i);
			switch(current) {
			case 'A': temp = temp.concat("T"); break;
			case 'T': temp = temp.concat("A"); break;
			case 'G': temp = temp.concat("C"); break;
			case 'C': temp = temp.concat("G"); break;
			}
		}
		//System.out.println("temp is " + temp + " and sequence is " + sequence);
		this.sequence = temp;
		//System.out.println("now sequence is " + sequence);
	}

	public String transcribe() {
		for(int i = 0; i < length; ++i) {
			char current = sequence.charAt(i);
			switch(current) {
			case 'A': trans = trans.concat("U"); break;
			case 'T': trans = trans.concat("A"); break;
			case 'G': trans = trans.concat("C"); break;
			case 'C': trans = trans.concat("G"); break;
			}
		}
		return trans;
	}
	
	public String translation() {
		String start = "AUG";
		boolean isStop = false;
		for(int i = 0; i < length; i=i+3) {
			if(trans.indexOf(start) == -1) {
				//System.err.println("Loop terminated - no start codon");
				protein = "not going to be created, it has no start codon";
				break;
			}
			String mRNA = trans.substring(trans.indexOf(start));
			int rnaLen = mRNA.length();
			if(rnaLen - i < 3) {
				//System.err.println("Loop terminated - end of sequence");
				break;
			}
			if(isStop) {
				//System.err.println("Loop terminated - stop codon reached");
				break;
			}
			switch(mRNA.substring(i,i+3)) {
			case "UUU": case "UUC": 
				protein = protein.concat("Phe "); break;
			case "UUA": case "UUG": case "CUU": case "CUC": case "CUA": case "CUG":
				protein = protein.concat("Leu "); break;
			case "AUU": case "AUC": case "AUA":
				protein = protein.concat("Ile "); break;
			case "AUG":
				protein = protein.concat("Met "); break;
			case "GUU": case "GUC": case "GUA": case "GUG":
				protein = protein.concat("Val "); break;
			case "UCU": case "UCC": case "UCA": case "UCG": case "AGU": case "AGC":
				protein = protein.concat("Ser "); break;
			case "CCU": case "CCC": case "CCA": case "CCG":
				protein = protein.concat("Pro "); break;
			case "ACU": case "ACC": case "ACA": case "ACG":
				protein = protein.concat("Thr "); break;
			case "GCU": case "GCC": case "GCA": case "GCG":
				protein = protein.concat("Ala "); break;
			case "UAU": case "UAC":
				protein = protein.concat("Val "); break;
			case "UAA": case "UAG": case "UGA":
				isStop = true; break;
			case "CAU": case "CAC":
				protein = protein.concat("His "); break;
			case "CAA": case "CAG":
				protein = protein.concat("Gln "); break;
			case "AAU": case "AAC":
				protein = protein.concat("Asn "); break;
			case "AAA": case "AAG":
				protein = protein.concat("Lys "); break;
			case "GAU": case "GAC":
				protein = protein.concat("Asp "); break;
			case "GAA": case "GAG":
				protein = protein.concat("Glu "); break;
			case "UGU": case "UGC":
				protein = protein.concat("Cys "); break;
			case "UGG":
				protein = protein.concat("Trp "); break;
			case "CGU": case "CGC": case "CGA": case "CGG": case "AGA": case "AGG":
				protein = protein.concat("Arg "); break;
			case "GGU": case "GGC": case "GGA": case "GGG":
				protein = protein.concat("Gly "); break;
			}
		}
		return protein;
	}
}
