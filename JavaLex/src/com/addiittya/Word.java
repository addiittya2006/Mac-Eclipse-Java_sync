package com.addiittya;

public class Word {
	private int line;
	private int row;
	private int index;
	private int type;
	private String value;

	//ALL Constants Defined
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

	public int getLine() {
		return line;
	}

	public int getRow() {
		return row;
	}
	
	public int getIndex() {
		return this.index;
	}

	public int getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setLine(int line) throws IllegalArgumentException {
		if (line >= 0)
			this.line = line;
		else
			throw new IllegalArgumentException("Line can not be negative");
	}

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

	public void setType(int type) {
		if (type >= KEYWORD && type <= COMMENT)
			this.type = type;
		else 
			throw new IllegalArgumentException("Illegal type");
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Word [line=" + line + ", row=" + row + ", index=" + index + ", type=" + type
				+ ", value=\"" + value + "\"]";
	}
	
	
	
}
