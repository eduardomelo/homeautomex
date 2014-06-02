package br.com.homeautomex.parse;

import org.json.JSONException;



@SuppressWarnings("serial")
public class ParserException extends JSONException {

	public static enum Error {
		/**/
		UNKNOWN(3001),
		/**/
		INTERNAL_ERROR(3002);

		private int errorValue;

		Error(int value) {
			this.errorValue = value;
		}

		public int getErrorValue() {
			return errorValue;
		}
	}

	private Error error;

	public ParserException(String arg0) {
		super(arg0);

		this.error = Error.UNKNOWN;
	}

	public ParserException(Error error) {
		super("");

		this.error = error;
	}

	public Error getError() {
		return error;
	}
}
