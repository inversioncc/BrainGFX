package cc.inversion.braingfx.lang;


public class CharCode
{
	private final char value;
	private int pairedBracketIndex = -1;
  

	public CharCode(final char value)
	{
		this.value = value;
	}

	public char getValue()
	{
		return value;
	}

	public void setPairedBracket(final int index)
	{
		pairedBracketIndex = index;
	}

	public int getPairedBracket()
	{
		return pairedBracketIndex;
	}
}