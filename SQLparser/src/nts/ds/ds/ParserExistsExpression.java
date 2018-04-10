package nts.ds.ds;

public class ParserExistsExpression extends ParserExpression
{
    private ParserExistsExpression()
    {
    }

    public ParserExistsExpression(ParserSelectStatement select)
    {
    	this.select = select;
    }

    public ParserSelectStatement getSelectStatement()
    {
        return this.select;
    }
    
    public void setSelectStatement(ParserSelectStatement select)
    {
        this.select = select;
    }

    @Override
    public boolean IsConstant(boolean allowNull)
    {
        return false;
    } 

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserExistsExpression dst = (obj instanceof ParserExistsExpression) ? (ParserExistsExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.select, dst.select))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        String res = "EXISTS (" + select.toString() + ")";
        return res;
    }

    @Override
    public Object clone()
    {
        ParserSelectStatement select = null;
        if (this.select != null)
            select = (ParserSelectStatement)this.select.clone();

        ParserExistsExpression res = new ParserExistsExpression();
        res.select = select;
        return res;
    }

    private ParserSelectStatement select;
}
