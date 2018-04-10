package nts.ds.ds;

public class ParserLimitClause
{
    private ParserLimitClause()
    {
    }

    public ParserLimitClause(ParserExpression limit)
    {
        this.limit = limit;
    }

    public ParserLimitClause(ParserExpression limit, ParserExpression offset)
    {
    	this.limit = limit;
    	this.offset = offset;
    }

    public ParserExpression getLimitExpression()
    {
        return this.limit;
    }
    
    public void setLimitExpression(ParserExpression limit)
    {
       this.limit = limit;
    }

    public ParserExpression getOffsetExpression()
    {
    	return this.offset;
    }
    
    public void setOffsetExpression(ParserExpression offset)
    {
    	this.offset = offset;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;

        ParserLimitClause dst = (obj instanceof ParserLimitClause) ? (ParserLimitClause) obj : null;
        if (dst == null)
            return false;

        if (!RefCompare.Compare(this.limit, dst.limit))
            return false;
        if (!RefCompare.Compare(this.offset, dst.offset))
            return false;

        return true;
    }

    @Override
    public String toString()
    {
        if (offset == null)
            return "LIMIT " + limit.toString();
        return "LIMIT " + limit.toString() + " OFFSET " + offset.toString();
    }

    @Override
    public Object clone()
    {
        ParserExpression limit = null;
        if (this.limit != null)
            limit = (ParserExpression)this.limit.clone();
        ParserExpression offset = null;
        if (this.offset != null)
            offset = (ParserExpression)this.offset.clone();

        ParserLimitClause res = new ParserLimitClause();
        res.limit = limit;
        res.offset = offset;
        return res;
    }

    private ParserExpression limit;
    private ParserExpression offset;
}
