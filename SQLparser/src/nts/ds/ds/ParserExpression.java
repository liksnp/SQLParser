package nts.ds.ds;

public class ParserExpression
{
	
	@Override 
	public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserExpression dst = (obj instanceof ParserExpression) ? (ParserExpression) obj : null;
        if (dst == null)
            return false;
        return true;
    }

	@Override
	public String toString()
    {
        throw new IllegalStateException("SQL statement rendering has failed");
    }

	@Override
	public Object clone()
    {
        throw new IllegalStateException("SQL statement cloning has failed");
    }

    public boolean IsConstant(boolean allowNull)
    {
        throw new UnsupportedOperationException("IsConstant is not supported in the base class");
    }
}
