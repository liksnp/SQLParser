package nts.ds.ds;

public class ParserSelectExpression extends ParserExpression
{
    private ParserSelectExpression()
    {
    }

    public ParserSelectExpression(ParserSelectStatement select)
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

        ParserSelectExpression dst = (obj instanceof ParserSelectExpression) ? (ParserSelectExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.select, dst.select))
            return false;

        return super.equals(obj);
    }

    @Override
    public Object clone()
    {
        ParserSelectStatement select = null;
        if (this.select != null)
            select = (ParserSelectStatement)this.select.clone();

        ParserSelectExpression res = new ParserSelectExpression();
        res.select = select;
        return res;
    }

    @Override
    public String toString()
    {
        return "("+this.select.toString()+")";
    }

    private ParserSelectStatement select;
}
