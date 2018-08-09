package cc.inversion.braingfx.lang;

import java.util.List;
import java.util.Deque;
import java.util.ArrayList;
import java.util.ArrayDeque;


public class Parser
{
	public List<CharCode> parse(final String rawCode)
	{
		final List<CharCode> parsedCode = new ArrayList<>(rawCode.length());
    separateCode(rawCode, parsedCode);
		pairBrackets(parsedCode);
		return parsedCode;
	}

	private void separateCode(final String rawCode, final List<CharCode> codeAlloc)
	{
		for(int i = 0; i < rawCode.length(); i++)
		{
			final char c = rawCode.charAt(i);
			switch(c)
			{
				case '+':
				case '-':
				case '.':
				case '<':
				case '>':
				case '[':
				case ']':
				case '/':
				case '\\':
					codeAlloc.add(new CharCode(c));
					break;
			}
		}
	}

	private void pairBrackets(final List<CharCode> codeAlloc)
	{
		final Deque<Integer> stack = new ArrayDeque<Integer>();
    
		for(int i = 0; i < codeAlloc.size(); i++)
		{
			switch(codeAlloc.get(i).getValue())
			{
				case '[':
					stack.addFirst(i);
					break;
				case ']':
					if(stack.isEmpty())
					{
						throw new RuntimeException("Please match all [ with ]");
					}
					else
					{
						final int openingBracketIndex = stack.removeFirst();
						codeAlloc.get(openingBracketIndex).setPairedBracket(i);
						codeAlloc.get(i).setPairedBracket(openingBracketIndex);
					}
					break;
			}
		}

		if(!stack.isEmpty())
		{
			throw new RuntimeException("Please match all [ with ]");
		}
	}
}