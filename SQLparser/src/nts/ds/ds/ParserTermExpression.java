package nts.ds.ds;

public class ParserTermExpression extends ParserExpression
{
    private ParserTermExpression()
    {
    }

    public ParserTermExpression(ParserTerm term)
    {
    	this.term = term;
    }

    public ParserTerm getTerm()
    {
        return this.term;
    }
    
    public void setTerm(ParserTerm term)
    {
        this.term = term;
    }

    @Override
    public boolean IsConstant(boolean allowNull)
    {
        return term.getAsTimeFunction() == null;//tutut
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserTermExpression dst = (obj instanceof ParserTermExpression) ? (ParserTermExpression) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.term, dst.term))
            return false;

        return super.equals(obj);
    }

    @Override
    public String toString()
    {
        return term.toString();
    }

    @Override
    public Object clone()
    {
        ParserTerm term = null;
        if (this.term != null)
            term = (ParserTerm)this.term.clone();

        ParserTermExpression res = new ParserTermExpression();
        res.term = term;
        return res;
    }

    private ParserTerm term;
}
