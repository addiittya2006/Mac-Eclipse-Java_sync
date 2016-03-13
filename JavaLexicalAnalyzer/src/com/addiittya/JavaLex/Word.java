package com.addiittya.JavaLex;

public class Word {
	private int line;
	private int row;
	private int index;
	private int type;
	private String value;
	
	/**
	 * Here is all types that a Word object could be.
	 */
	public static final int UNDEFINED = 0;
	public static final int KEYWORD = 1;
	public static final int IDENTIFIER = 2;
	public static final int OPERATOR = 3;
	public static final int CONSTANT = 4;
	public static final int DELIMITER = 5;
	public static final int COMMENT = 6;
	
	public Word(int line, int row,int index, int type, String value){
		setLine(line);
		setRow(row);
		setIndex(index);
		setType(type);
		setValue(value);
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	public int getIndex() {
		return this.index;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param line the line to set
	 * @throws IllegalArgumentException 
	 */
	public void setLine(int line) throws IllegalArgumentException {
		if (line >= 0)
			this.line = line;
		else
			throw new IllegalArgumentException("Line can not be negative");
	}

	/**
	 * @param row the row to set
	 * @throws IllegalArgumentException 
	 */
	public void setRow(int row) throws IllegalArgumentException {
		if (row >= 0)
			this.row = row;
		else
			throw new IllegalArgumentException("Row can not be negative");
	}
	
	public void setIndex(int index) {
		if (index >= 0)
			this.index = index;
		else
			throw new IllegalArgumentException("Index can not be negative");
		
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		if (type >= KEYWORD && type <= COMMENT)
			this.type = type;
		else 
			throw new IllegalArgumentException("Illegal type");
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Word [line=" + line + ", row=" + row + ", index=" + index + ", type=" + type
				+ ", value=\"" + value + "\"]";
	}
	
	
	
}
