package nts.ds.ds;

public class ParserStatement {
    
	@Override
	public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserStatement dst = (obj instanceof ParserStatement) ? (ParserStatement) obj : null;

        if (dst == null)
            return false;

        return true;
    }

	@Override
	public Object clone()
    {
        return new ParserStatement();
    }
}
