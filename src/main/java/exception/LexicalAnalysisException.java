package exception;

@SuppressWarnings("serial")
public class LexicalAnalysisException extends Exception {
	private int errorPosition; // 오류가 발생한 위치 (line)
	private String errorMessage; // 오류 메세지

	public LexicalAnalysisException(int errorPosition) {
		this.errorPosition = errorPosition;
	}

	public LexicalAnalysisException(int errorPosition, String errorMessage) {
		this.errorPosition = errorPosition;
		this.errorMessage = errorMessage;
	}

	public int getErrorPosition() {
		return errorPosition;
	}

	@Override
	public String getMessage() {
		return "\nexception.LexicalAnalysisException:" + errorMessage;
	}
}
