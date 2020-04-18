package data;

public enum States {
	Q0(false), Q1(false), Q2(true), Q3(true), Q4(true), Q5(false), Q6(true), Q7(false), Q8(false), 
	Q9(true), Q10(true), Q11(false), Q12(false), Q13(false), Q14(true), Q15(true), Q16(true), Q17(true);
	
	States plus, minus, underbar, id_char, zeroTo9, 
	assign, or, and, double_quotes, E, dot, 
	special_letter, brackets_whitesapce, 
	new_line, divide;
	
	static {
		Q0.plus = Q3; Q0.minus = Q10; Q0.underbar = Q1; Q0.id_char = Q2; Q0.zeroTo9 = Q4; Q0.assign = Q14; Q0.or = Q12; Q0.and = Q13;
		Q0.double_quotes = Q11; Q0.E = Q2; Q0.special_letter = Q14; Q0.brackets_whitesapce = Q17; Q0.new_line = Q17; Q0.divide = Q15;
		
		Q1.underbar = Q2; Q1.id_char = Q2; Q1.zeroTo9 = Q2;
	    Q2.underbar = Q2; Q2.id_char = Q2; Q2.zeroTo9 = Q2;
	    Q3.plus = Q17; Q3.zeroTo9 = Q4; Q3.assign = Q17;
	    Q4.zeroTo9 = Q4; Q4.E = Q7; Q4.dot = Q5;
	    Q5.zeroTo9 = Q6;
	    Q6.zeroTo9 = Q6; Q6.E = Q7;
	    Q7.plus = Q8; Q7.minus = Q8; Q7.zeroTo9 = Q9;
	    Q8.zeroTo9 = Q9;
	    Q9.zeroTo9 = Q9;
	    Q10.minus = Q17; Q10.zeroTo9 = Q4; Q10.assign = Q17;
	    
	    Q11.plus = Q11; Q11.minus = Q11; Q11.underbar = Q11; Q11.id_char = Q11; Q11.zeroTo9 = Q11; Q11.assign = Q11; Q11.or = Q11; Q11.and = Q11;
		Q11.double_quotes = Q17; Q11.E = Q11; Q11.dot = Q11; Q11.special_letter = Q11; Q11.brackets_whitesapce = Q11; Q11.new_line = Q11; Q11.divide = Q11;
		
		Q12.or = Q17;
		Q13.and = Q17;
		Q14.assign = Q17;
		Q15.assign = Q17; Q9.divide = Q16;
		
		Q16.plus = Q16; Q16.minus = Q16; Q16.underbar = Q16; Q16.id_char = Q16; Q16.zeroTo9 = Q16; Q16.assign = Q16; Q16.or = Q16; Q16.and = Q16;
		Q16.double_quotes = Q16; Q16.E = Q16; Q16.dot = Q16; Q16.special_letter = Q16; Q16.brackets_whitesapce = Q16; Q16.divide = Q16;
	}
	
	private boolean accept;
	
	States(boolean accept) {
		this.accept = accept;
	}
	
	public boolean isAccept() {
		return accept;
	}
}