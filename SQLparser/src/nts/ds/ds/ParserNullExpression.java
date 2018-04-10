package nts.ds.ds;

public class ParserNullExpression extends ParserExpression
{
	@Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserNullExpression dst = (obj instanceof ParserNullExpression) ? (ParserNullExpression) obj : null;
        if (dst == null)
            return false;

        return true;
    }

	@Override
	public boolean IsConstant(boolean allowNull)
    {
        return allowNull;
    }

	@Override
    public String toString()
    {
        return "NULL";
    }

	@Override
    public Object clone()
    {
        ParserExpression res = new ParserNullExpression();
        return res;
    }
}
